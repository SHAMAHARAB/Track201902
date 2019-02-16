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

  /**
   * 指定したレシピを更新します。
   * 
   * @param id 主キー
   * @param recipe 更新レシピ
   */
  void updateById(int id, Recipe recipe);

  /**
   * 指定したレシピを削除します。
   * 
   * @param id 主キー
   * @return テーブルに１レコード以上の反映があったかの真偽値
   */
  boolean deleteById(int id);
  
}
