package jp.co.softbank.trackproject.repository;

import java.util.List;

import jp.co.softbank.trackproject.model.Recipe;

/**
 * レシピの登録・照会・更新・削除を行うRepositoryインターフェースです。
 * @author H.Hamahara
 *
 */
public interface RecipeRepository {
  
  /**
   * レシピを作成します。
   * 
   * @param recipe Recipe
   */
  void insert(Recipe recipe);
  
  Recipe selectById(int id);
  
  List<Recipe> selectAll();

  void updateById(int id, Recipe recipe);

  void deleteById(int id);
  
}
