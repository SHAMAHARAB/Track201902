package jp.co.softbank.trackproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レシピのドメインクラスです。
 * @author H.Hamahara
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
  
  private int id;
  
  private String title;
  
  private String makingTime;
  
  private String serves;
  
  private String ingredients;
  
  private int cost;
  
  /**
   * コンストラクタです。
   * 
   * @param title title
   * @param makingTime makingTime
   * @param serves serves
   * @param ingredients ingredients
   * @param cost cost
   */
  public Recipe(
      String title, String makingTime, String serves, String ingredients, int cost) {
    this.title = title;
    this.makingTime = makingTime;
    this.serves = serves;
    this.ingredients = ingredients;
    this.cost = cost;
  }
}
