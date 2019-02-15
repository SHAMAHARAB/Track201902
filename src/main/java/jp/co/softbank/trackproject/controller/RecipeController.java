package jp.co.softbank.trackproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.softbank.trackproject.client.response.RecipeResponse;
import jp.co.softbank.trackproject.model.Recipe;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RecipeResponse create() {
    return new RecipeResponse(new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
  }

}
