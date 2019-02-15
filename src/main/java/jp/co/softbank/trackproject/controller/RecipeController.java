package jp.co.softbank.trackproject.controller;

import jp.co.softbank.trackproject.client.response.RecipeResponse;
import jp.co.softbank.trackproject.model.Recipe;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

  public RecipeResponse create() {
    return new RecipeResponse(new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
  }

}
