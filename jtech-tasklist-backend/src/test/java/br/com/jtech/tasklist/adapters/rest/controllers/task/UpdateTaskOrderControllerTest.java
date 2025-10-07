package br.com.jtech.tasklist.adapters.rest.controllers.task;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.task.UpdateTasksOrderResponse;
import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskOrderInputDTO;
import br.com.jtech.tasklist.config.infra.utils.Jsons;
import br.com.jtech.tasklist.factories.TaskFactory;
import br.com.jtech.tasklist.factories.TaskListFactory;
import br.com.jtech.tasklist.factories.UserFactory;
import br.com.jtech.tasklist.utils.JWTUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UpdateTaskOrderControllerTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TaskListRepository taskListRepository;

  @Autowired
  private TaskRepository taskRepository;

  private UserFactory userFactory;
  private TaskFactory taskFactory;
  private TaskListFactory taskListFactory;

  @Autowired
  private JWTUtils jwtUtils;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @AfterEach
  void tearDown() {
    deleteFromTables(jdbcTemplate, "task", "task_list", "task_list_user");
  }

  @BeforeEach
  void setup() {
    mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(SecurityMockMvcConfigurers.springSecurity())
      .build();

    this.userFactory = new UserFactory(this.userRepository);
    this.taskFactory = new TaskFactory(this.taskRepository, this.taskListRepository);
    this.taskListFactory = new TaskListFactory(this.taskListRepository);
  }

  @Test
  @DisplayName("Should be able to update tasks order in a task list")
  void shouldUpdateTasksOrder() throws Exception {
    var user = userFactory.makeUser();

    var taskList = this.taskListFactory.makeTaskList(user);
    var tasks = new ArrayList<Task>();
    tasks.add(this.taskFactory.makeTask(taskList.getId(), 0));
    tasks.add(this.taskFactory.makeTask(taskList.getId(), 1));
    tasks.add(this.taskFactory.makeTask(taskList.getId(), 2));

    tasks.get(0).setOrder(2);
    tasks.get(0).setOrder(0);
    tasks.get(0).setOrder(1);

    var tasksToUpdate = new HashSet<UpdateTaskOrderInputDTO>();
    tasksToUpdate.add(new UpdateTaskOrderInputDTO(tasks.get(0).getId(), tasks.get(0).getOrder()));
    tasksToUpdate.add(new UpdateTaskOrderInputDTO(tasks.get(1).getId(), tasks.get(1).getOrder()));
    tasksToUpdate.add(new UpdateTaskOrderInputDTO(tasks.get(2).getId(), tasks.get(2).getOrder()));

    mvc.perform(
        MockMvcRequestBuilders.patch("/api/v1/task-lists/" + taskList.getId() + "/tasks/order")
          .contentType(MediaType.APPLICATION_JSON)
          .header("Authorization", jwtUtils.generateToken(user.getId()))
          .content(Objects.requireNonNull(Jsons.toJsonString(tasksToUpdate)))
      ).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().json(
        Objects.requireNonNull(Jsons.toJsonString(UpdateTasksOrderResponse.of(new HashSet<>(tasks))))
      ));
  }

}
