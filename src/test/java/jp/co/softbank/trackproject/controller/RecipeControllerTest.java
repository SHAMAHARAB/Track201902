package jp.co.softbank.trackproject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        .andExpect(status().isOk())
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
        .andExpect(status().isOk())
        .andExpect(content().json(
          resource.content("post_recipe_exception-res.json"), true));
  }
  
  @Test
  public void test_findById() throws Exception {
    // prepare
    Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    when(recipeService.findById(1)).thenReturn(recipe);
    
    // test & verify
    mockMvc.perform(get("/recipes/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(
          resource.content("get_recipe-res.json"), true));
  }
  
  @Test
  public void test_findAll() throws IOException, Exception {
    // prepare
    List<Recipe> recipes = Arrays.asList(
        new Recipe(1, "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000),
        new Recipe(2, "オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700),
        new Recipe(3, "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
    when(recipeService.findAll()).thenReturn(recipes);
    
    // test & verify
    mockMvc.perform(get("/recipes/"))
        .andExpect(status().isOk())
        .andExpect(content().json(
          resource.content("get_all_recipe-res.json"), true));
  }
  
  @Test
  public void test_findAll_empty() throws IOException, Exception {
    // prepare
    when(recipeService.findAll()).thenReturn(Collections.emptyList());
    
    // test & verify
    mockMvc.perform(get("/recipes/"))
        .andExpect(status().isOk())
        .andExpect(content().json("{}"));
  }
  
  @Test
  public void test_updateById() throws IOException, Exception {
    // prepare
    Recipe recipe = new Recipe("トマトスープレシピ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    RecipeWebDto requstForm = new RecipeWebDto(recipe);
    when(recipeService.updateById(1, recipe)).thenReturn(recipe);
    
    // test & verify
    mockMvc.perform(patch("/recipes/1").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(new ObjectMapper().writeValueAsString(requstForm))
        )
        .andExpect(status().isOk())
        .andExpect(content().json(
            resource.content("put_recipe-res.json"), true));
  }
  
  @Test
  public void test_deleteById() throws Exception {
    // test & verify
    mockMvc.perform(delete("/recipes/1"))
        .andExpect(status().isNoContent())
        .andExpect(content().json(
            resource.content("delete_recipe-res.json"), true));
  }
}
