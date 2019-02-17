package jp.co.softbank.trackproject.client.response;

import java.util.List;
import java.util.stream.Collectors;

import jp.co.softbank.trackproject.client.dto.RecipeWithIdWebDto;
import jp.co.softbank.trackproject.model.Recipe;
import lombok.Data;

/**
 * レシピの一覧が記載されたレスポンスクラスです。
 * @author H.Hamahara
 *
 */
@Data
public class RecipeListResponse {
  private List<RecipeWithIdWebDto> recipes;
  
  /**
   * コンストラクタです。
   * 
   * @param recipes レシピの一覧
   */
  public RecipeListResponse(List<Recipe> recipes) {
    this.recipes = recipes.stream()
        .map(RecipeWithIdWebDto::new)
        .collect(Collectors.toList());
  }
}
