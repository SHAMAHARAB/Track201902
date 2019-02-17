package jp.co.softbank.trackproject.client.dto;

import jp.co.softbank.trackproject.model.Recipe;
import lombok.Data;

/**
 * クライアントへID付きのレシピをレスポンスするためのDtoクラスです。
 * @author H.Hamahara
 *
 */
@Data
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
