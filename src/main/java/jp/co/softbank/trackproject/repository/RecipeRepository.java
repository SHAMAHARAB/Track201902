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
  
  /**
   * 指定したレシピ一つを返します。
   * 
   * @param id 主キー
   * @return 指定したレシピ
   */
  Recipe selectById(int id);
  
  /**
   * 全てのレシピを返します。
   * 
   * @return 全てのレシピ
   */
  List<Recipe> selectAll();

  void updateById(int id, Recipe recipe);

  void deleteById(int id);
  
}
