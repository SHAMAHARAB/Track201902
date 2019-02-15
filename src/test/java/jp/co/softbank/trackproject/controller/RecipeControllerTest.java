package jp.co.softbank.trackproject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import jp.co.softbank.trackproject.client.dto.RecipeWebDto;
import jp.co.softbank.trackproject.model.Recipe;
import jp.co.softbank.trackproject.service.RecipeService;
import jp.co.softbank.trackproject.tool.ResourceLoadHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private RecipeService recipeService;
  
  private ResourceLoadHelper resource = new ResourceLoadHelper(getClass());

  @Test
  public void test_create() throws Exception {
    // prepare
    Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    RecipeWebDto requstForm = new RecipeWebDto(recipe);
    when(recipeService.create(recipe)).thenReturn(recipe);
    
    // test & verify
    mockMvc.perform(post("/recipes")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(requstForm))
        )
        .andExpect(status().isCreated())
        .andExpect(content().json(
          resource.content("post_recipe-res.json"), true));
  }
  
  @Test
  public void test_exception() throws JsonProcessingException, IOException, Exception {
    // test & verify
    mockMvc.perform(post("/recipes")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(new RecipeWebDto()))
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().json(
          resource.content("post_recipe_exception-res.json"), true));
  }
}
