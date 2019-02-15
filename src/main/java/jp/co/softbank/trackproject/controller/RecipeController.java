package jp.co.softbank.trackproject.controller;

import jp.co.softbank.trackproject.client.response.RecipeResponse;
import jp.co.softbank.trackproject.model.Recipe;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

  @PostMapping
  public RecipeResponse create() {
    return new RecipeResponse(new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
  }

}
