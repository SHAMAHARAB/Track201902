package jp.co.softbank.trackproject.controller;

import jp.co.softbank.trackproject.client.dto.RecipeWebDto;
import jp.co.softbank.trackproject.client.response.RecipeResponse;
import jp.co.softbank.trackproject.service.RecipeService;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
  
  private RecipeService recipeService;
  
  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RecipeResponse create(@RequestBody @Validated RecipeWebDto recipeWebDto) {
    return new RecipeResponse(recipeService.create(recipeWebDto.transferRecipe()));
  }

}
