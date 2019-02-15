package jp.co.softbank.trackproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeControllerTest.class)
public class RecipeControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void test_create() throws Exception {
    mockMvc.perform(post("/recipes"));
  }

}
