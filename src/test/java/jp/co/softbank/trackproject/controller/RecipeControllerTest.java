package jp.co.softbank.trackproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.softbank.trackproject.client.dto.RecipeWebDto;
import jp.co.softbank.trackproject.client.response.RecipeResponse;
import jp.co.softbank.trackproject.model.Recipe;
import jp.co.softbank.trackproject.tool.ResourceLoadHelper;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  private ResourceLoadHelper resource = new ResourceLoadHelper(getClass());

  @Test
  public void test_create() throws Exception {
    RecipeWebDto requstForm = 
        new RecipeWebDto(new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
    
    mockMvc.perform(post("/recipes")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(requstForm))
        )
        .andExpect(status().isCreated())
        .andExpect(content().json(
          resource.content("post_recipe-res.json"), true));
  }
}
