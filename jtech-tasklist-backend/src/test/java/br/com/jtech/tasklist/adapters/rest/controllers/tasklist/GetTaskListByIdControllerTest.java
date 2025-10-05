package br.com.jtech.tasklist.adapters.rest.controllers.tasklist;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.tasklist.GetTaskListByIdResponse;
import br.com.jtech.tasklist.config.infra.utils.Jsons;
import br.com.jtech.tasklist.factories.TaskFactory;
import br.com.jtech.tasklist.factories.TaskListFactory;
import br.com.jtech.tasklist.factories.UserFactory;
import br.com.jtech.tasklist.utils.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class GetTaskListByIdControllerTest {

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
  @DisplayName("Should be able to get a task list by it's id")
  void shouldCreateTaskList() throws Exception {
    var user = userFactory.makeUser();

    var taskList = this.taskListFactory.makeTaskList(user);
    var task = this.taskFactory.makeTask(taskList.getId());

    taskList.setTasks(Collections.singleton(task));

    mvc.perform(
        MockMvcRequestBuilders.get("/api/v1/task-list/" + taskList.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .header("Authorization", jwtUtils.generateToken(user.getId()))
      ).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().json(
        Objects.requireNonNull(Jsons.toJsonString(GetTaskListByIdResponse.of(taskList))))
      );
  }

}
