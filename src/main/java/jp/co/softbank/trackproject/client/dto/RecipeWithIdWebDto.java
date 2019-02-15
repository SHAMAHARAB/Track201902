package jp.co.softbank.trackproject.client.dto;

import jp.co.softbank.trackproject.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * クライアントからのリクエスト、レスポンスを受けつけるDtoクラスです。
 * @author H.Hamahara
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeWithIdWebDto {

  private int id;
  
  private String title;
  
  private String making_time;
  
  private String serves;
  
  private String ingredients;
  
  private String cost;
  
  /**
   * コンストラクタです。
   * @param recipe Recipe
   */
  public RecipeWithIdWebDto(Recipe recipe) {
    this.id = recipe.getId();
    this.title = recipe.getTitle();
    this.making_time = recipe.getMakingTime();
    this.serves = recipe.getServes();
    this.ingredients = recipe.getIngredients();
    this.cost = String.valueOf(recipe.getCost());
  }
}
