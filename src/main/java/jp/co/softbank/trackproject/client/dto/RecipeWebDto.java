package jp.co.softbank.trackproject.client.dto;

import javax.validation.constraints.NotEmpty;

import jp.co.softbank.trackproject.model.Recipe;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * クライアントからのリクエスト、レスポンスを受けつけるDtoクラスです。
 * @author H.Hamahara
 *
 */
@Data
@NoArgsConstructor
public class RecipeWebDto {
  
  @NotEmpty
  private String title;
  
  @NotEmpty
  private String making_time;
  
  @NotEmpty
  private String serves;
  
  @NotEmpty
  private String ingredients;
  
  @NotEmpty
  private String cost;
  
  /**
   * コンストラクタです。
   * @param recipe Recipe
   */
  public RecipeWebDto(Recipe recipe) {
    this.title = recipe.getTitle();
    this.making_time = recipe.getMakingTime();
    this.serves = recipe.getServes();
    this.ingredients = recipe.getIngredients();
    this.cost = String.valueOf(recipe.getCost());
  }

  /**
   * RecipeWebDtoの情報をRecipeクラスに変換するクラスです。
   * @return Recipe
   */
  public Recipe transferRecipe() {
    return new Recipe(
      this.title,
      this.making_time,
      this.serves,
      this.ingredients,
      Integer.parseInt(this.cost)
    );
  }
}
