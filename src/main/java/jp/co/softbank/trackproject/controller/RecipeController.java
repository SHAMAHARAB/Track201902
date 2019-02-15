package jp.co.softbank.trackproject.controller;

import jp.co.softbank.trackproject.client.dto.RecipeWebDto;
import jp.co.softbank.trackproject.client.exception.CreateExceptionResponse;
import jp.co.softbank.trackproject.client.response.RecipeResponse;
import jp.co.softbank.trackproject.service.RecipeService;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * レシピの登録・照会・更新・削除を行うControllerクラスです。
 * @author H.Hamahara
 *
 */
@RestController
@RequestMapping("/recipes")
public class RecipeController {
  
  private RecipeService recipeService;

  /**
   * コンストラクタです。
   * 
   * @param recipeService RecipeService
   */
  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  /**
   * レシピを作成します。
   * 
   * @param recipeWebDto RecipeWebDto
   * @return 登録成功時に返却するRecipeResponseクラス
   */
  @PostMapping
  public RecipeResponse create(@RequestBody @Validated RecipeWebDto recipeWebDto) {
    return new RecipeResponse(
        recipeService.create(recipeWebDto.transferRecipe()), 
        "Recipe successfully created!");
  }
  
  @GetMapping("/{id}")
  public RecipeResponse findById(@PathVariable int id) {
    return new RecipeResponse(recipeService.findById(id),
        "Recipe details by id");
  }
  
  /**
   * レシピの登録に失敗した時のハンドリングを行います。
   * 
   * @return 登録失敗時に返却するCreateExceptionResponseクラス
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public CreateExceptionResponse badRequest() {
    return new CreateExceptionResponse("title, making_time, serves, ingredients, cost");
  }

}
