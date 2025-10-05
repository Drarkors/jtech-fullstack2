package br.com.jtech.tasklist.adapters.rest.controllers.tasklist;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.adapters.database.repositories.TaskRepository;
import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.tasklist.FetchUserTaskListsResponse;
import br.com.jtech.tasklist.config.infra.utils.Jsons;
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

import java.util.Collections;
import java.util.Objects;

import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FetchUserTaskListsControllerTest {

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
    this.taskListFactory = new TaskListFactory(this.taskListRepository);
  }

  @Test
  @DisplayName("Should be able to fetch an user's task lists")
  void shouldFetchUserTaskList() throws Exception {
    var user = userFactory.makeUser();

    var taskList = this.taskListFactory.makeTaskList(user);

    mvc.perform(
        MockMvcRequestBuilders.get("/api/v1/task-list")
          .contentType(MediaType.APPLICATION_JSON)
          .header("Authorization", jwtUtils.generateToken(user.getId()))
      ).andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().json(
        Objects.requireNonNull(Jsons.toJsonString(Collections.singleton(FetchUserTaskListsResponse.of(taskList)))))
      );
  }

}
