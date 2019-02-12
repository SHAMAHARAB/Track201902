package jp.co.softbank.trackproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.softbank.trackproject.model.Recipe;
import jp.co.softbank.trackproject.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
  
  private RecipeRepository recipeRepository;
  
  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public Recipe create(Recipe recipe) {
    recipeRepository.insert(recipe);
    return null;
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
