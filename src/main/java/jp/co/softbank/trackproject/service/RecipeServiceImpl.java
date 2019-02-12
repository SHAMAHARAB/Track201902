package jp.co.softbank.trackproject.service;

import java.util.List;

import jp.co.softbank.trackproject.model.Recipe;
import jp.co.softbank.trackproject.repository.RecipeRepository;

import org.springframework.stereotype.Service;

/**
 * Recipeの登録・照会・更新・削除を行うServiceクラスです。
 * @author H.Hamahara
 */
@Service
public class RecipeServiceImpl implements RecipeService {
  
  private RecipeRepository recipeRepository;
  
  /**
   * コンストラクタです。
   * 
   * @param recipeRepository RecipeRepository
   */
  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  /**
   * レシピを作成します。
   * @param recipe Recipe
   * @return 登録対象のレシピ
   */
  @Override
  public Recipe create(Recipe recipe) {
    recipeRepository.insert(recipe);
    return recipe;
  }

  @Override
  public Recipe findById(int id) {
    return null;
  }

  @Override
  public List<Recipe> findAll() {
    return null;
  }

  @Override
  public Recipe updateById(int id, Recipe recipe) {
    return null;
  }

  @Override
  public void deleteById(int id) {
  }

}
