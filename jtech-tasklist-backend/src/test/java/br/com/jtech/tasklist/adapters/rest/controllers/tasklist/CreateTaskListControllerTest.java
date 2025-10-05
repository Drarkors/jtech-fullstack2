package br.com.jtech.tasklist.adapters.rest.controllers.tasklist;

import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.requests.CreateTaskListRequest;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.infra.utils.Jsons;
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

import java.util.Objects;

import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateTaskListControllerTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private UserRepository userRepository;

  private UserFactory userFactory;

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
  }

  @Test
  @DisplayName("Should be able to create a new task list")
  void shouldCreateTaskList() throws Exception {
    var user = userFactory.makeUser();

    var payload = CreateTaskListRequest.builder()
      .name("Task List")
      .order(0)
      .description("Description for task list")
      .build();

    mvc.perform(
      MockMvcRequestBuilders.post("/api/v1/task-lists")
        .contentType(MediaType.APPLICATION_JSON)
        .content(Objects.requireNonNull(Jsons.toJsonString(payload)))
        .header("Authorization", jwtUtils.generateToken(user.getId()))
    ).andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  @Test
  @DisplayName("Should not be able to create a new task list if userId is invalid")
  void shouldThrowTaskListUserNotFoundException() throws Exception {
    var payload = CreateTaskListRequest.builder()
      .name("Task List")
      .order(0)
      .description("Description for task list")
      .build();

    mvc.perform(
      MockMvcRequestBuilders.post("/api/v1/task-lists")
        .contentType(MediaType.APPLICATION_JSON)
        .content(Objects.requireNonNull(Jsons.toJsonString(payload)))
        .header("Authorization", jwtUtils.generateToken(GenId.newId()))
    ).andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
  }

}
