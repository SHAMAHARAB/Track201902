package jp.co.softbank.trackproject.service;

import java.util.List;

import jp.co.softbank.trackproject.model.Recipe;

/**
 * レシピの登録・照会・更新・削除を行うServiceインターフェースです。
 * @author H.Hamahara
 *
 */
public interface RecipeService {
  
  /**
   * レシピを作成します。
   * 
   * @param recipe Recipe
   * @return 登録対象のレシピ
   */
  Recipe create(Recipe recipe);
  
  /**
   * 指定したレシピ一つを返します。
   * 
   * @param id 主キー
   * @return 指定したレシピ
   */
  Recipe findById(int id);
  
  /**
   * 全てのレシピを返します。
   * 
   * @return 全てのレシピ
   */
  List<Recipe> findAll();

  Recipe updateById(int id, Recipe recipe);

  void deleteById(int id);
}
