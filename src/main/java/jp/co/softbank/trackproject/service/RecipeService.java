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
  
  Recipe findById(int id);
  
  List<Recipe> findAll();

  Recipe updateById(int id, Recipe recipe);

  void deleteById(int id);
}
