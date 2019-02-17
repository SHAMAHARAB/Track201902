package jp.co.softbank.trackproject.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.softbank.trackproject.client.dto.RecipeWebDto;
import jp.co.softbank.trackproject.client.exception.RecipeCreateExceptionResponse;
import jp.co.softbank.trackproject.client.exception.RecipeDeleteExceptionResponse;
import jp.co.softbank.trackproject.client.response.MessageResponse;
import jp.co.softbank.trackproject.client.response.RecipeListResponse;
import jp.co.softbank.trackproject.client.response.RecipeResponse;
import jp.co.softbank.trackproject.exception.RecipeDeleteException;
import jp.co.softbank.trackproject.service.RecipeService;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
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
   * @return 登録したレシピ
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
   * @return 指定したレシピ
   */
  @GetMapping("/{id}")
  public RecipeResponse findById(@PathVariable int id) {
    return new RecipeResponse(recipeService.findById(id),
        GET_MESSAGE);
  }
  
  /**
   * 全てのレシピを返します。
   * 
   * @return 全てのレシピ
   */
  @GetMapping
  public RecipeListResponse findAll() {
    return new RecipeListResponse(recipeService.findAll());
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
  
  /**
   * 指定したレシピを削除します。
   * 
   * @param id 主キー
   * @return 削除成功のメッセージ
   */
  @DeleteMapping("/{id}")
  public MessageResponse deleteById(@PathVariable int id) {
    recipeService.deleteById(id);
    return new MessageResponse(DELETE_MESSAGE);
  }
  
  /**
   * レシピの登録に失敗した時のハンドリングを行います。
   * 
   * @return 登録の失敗を知らせるレスポンス
   * @throws MethodArgumentNotValidException MethodArgumentNotValidException
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public RecipeCreateExceptionResponse badRequest(MethodArgumentNotValidException e)
      throws MethodArgumentNotValidException {
    if (e.getParameter().getMethod().getName().equals("create")) {
      // 発生したバリデーションフィールドのリスト
      List<String> validateFields = e.getBindingResult().getFieldErrors()
          .stream()
          .map(FieldError::getField)
          .collect(Collectors.toList());
      
      // Responseのメッセージに渡すための文字列を作成
      String validMessage = 
          Arrays.asList("title", "making_time", "serves", "ingredients", "cost")
          .stream()
          .filter(s -> validateFields.contains(s))
          .collect(Collectors.joining(", "));
      
      return new RecipeCreateExceptionResponse(validMessage);
    }
    throw e;
  }
  
  /**
   * レシピの削除に失敗した時のハンドリングを行います。
   * 
   * @param e RecipeDeleteException
   * @return 削除の失敗を知らせるレスポンス
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({RecipeDeleteException.class})
  public RecipeDeleteExceptionResponse notFoundDeleteRecipe(RecipeDeleteException e) {
    return new RecipeDeleteExceptionResponse(e.getMessage());
  }

}
