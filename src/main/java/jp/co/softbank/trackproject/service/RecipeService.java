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

  /**
   * 指定したレシピを更新します。
   * 
   * @param id 主キー
   * @param recipe 更新レシピ
   * @return 更新されたレシピ
   */
  Recipe updateById(int id, Recipe recipe);

  /**
   * 指定したレシピを削除します。
   * 
   * @param id 主キー
   */
  void deleteById(int id);
}
