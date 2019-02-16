package jp.co.softbank.trackproject.service;

import java.util.List;

import jp.co.softbank.trackproject.model.Recipe;
import jp.co.softbank.trackproject.repository.RecipeRepository;

import org.springframework.stereotype.Service;

/**
 * レシピの登録・照会・更新・削除を行うServiceクラスです。
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
   * {@inheritDoc}
   */
  @Override
  public Recipe create(Recipe recipe) {
    recipeRepository.insert(recipe);
    return recipe;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Recipe findById(int id) {
    return recipeRepository.selectById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Recipe> findAll() {
    return recipeRepository.selectAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Recipe updateById(int id, Recipe recipe) {
    recipeRepository.updateById(id, recipe);
    return recipe;
  }

  @Override
  public void deleteById(int id) {
  }

}
