package jp.co.softbank.trackproject.client.response;

import java.util.List;
import java.util.stream.Collectors;

import jp.co.softbank.trackproject.client.dto.RecipeWebDto;
import jp.co.softbank.trackproject.model.Recipe;
import lombok.Data;

/**
 * レシピの一覧が記載されたレスポンスクラスです。
 * @author H.Hamahara
 *
 */
@Data
public class AllRecipeResponse {
  private List<RecipeWebDto> recipes;
  
  /**
   * コンストラクタです。
   * 
   * @param recipes レシピの一覧
   */
  public AllRecipeResponse(List<Recipe> recipes) {
    this.recipes = recipes.stream()
        .map(RecipeWebDto::new)
        .collect(Collectors.toList());
  }
}
