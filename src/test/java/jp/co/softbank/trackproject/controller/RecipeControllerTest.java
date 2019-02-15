package jp.co.softbank.trackproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jp.co.softbank.trackproject.tool.ResourceLoadHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  private ResourceLoadHelper resource = new ResourceLoadHelper(getClass());

  @Test
  public void test_create() throws Exception {
    mockMvc.perform(post("/recipes"))
        .andExpect(status().isCreated())
        .andExpect(content().json(
          resource.content("post_recipe-res.json"), true));
  }
}
