package jp.co.softbank.trackproject.controller;

import jp.co.softbank.trackproject.client.dto.RecipeWebDto;
import jp.co.softbank.trackproject.client.exception.CreateExceptionResponse;
import jp.co.softbank.trackproject.client.exception.DeleteExceptionResponse;
import jp.co.softbank.trackproject.client.response.AllRecipeResponse;
import jp.co.softbank.trackproject.client.response.MessageResponse;
import jp.co.softbank.trackproject.client.response.RecipeResponse;
import jp.co.softbank.trackproject.exception.RecipeDeleteException;
import jp.co.softbank.trackproject.service.RecipeService;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
  
  private static final String POST_MESSAGE = "Recipe successfully created!";

  private static final String GET_MESSAGE = "Recipe details by id";
  
  private static final String PUT_MESSAGE = "Recipe successfully updated!";
  
  private static final String DELETE_MESSAGE = "Recipe successfully removed!";
  
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
        POST_MESSAGE);
  }
  
  /**
   * 指定したレシピ一つを返します。
   * 
   * @param id 主キー
   * @return idで指定したレシピ
   */
  @GetMapping("/{id}")
  public RecipeResponse findById(@PathVariable int id) {
    return new RecipeResponse(recipeService.findById(id),
        GET_MESSAGE);
  }
  
  /**
   * 全てのレシピ一つを返します。
   * 
   * @return 全てのレシピ
   */
  @GetMapping
  public AllRecipeResponse findAll() {
    return new AllRecipeResponse(recipeService.findAll());
  }
  
  /**
   * 指定したレシピを更新します。
   * 
   * @param id 主キー
   * @param recipeWebDto RecipeWebDto
   * @return 更新したレシピ
   */
  @PatchMapping("/{id}")
  public RecipeResponse updateById(@PathVariable int id, @RequestBody RecipeWebDto recipeWebDto) {
    return new RecipeResponse(recipeService.updateById(id, recipeWebDto.transferRecipe()),
        PUT_MESSAGE);
  }
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public MessageResponse deleteById(@PathVariable int id) {
    recipeService.deleteById(id);
    return new MessageResponse(DELETE_MESSAGE);
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
  
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({RecipeDeleteException.class})
  public DeleteExceptionResponse notFoundDeleteRecipe(RecipeDeleteException e) {
    return new DeleteExceptionResponse(e.getMessage());
  }

}
