package jp.co.softbank.trackproject.service;

import java.util.List;

import jp.co.softbank.trackproject.model.Recipe;

public interface RecipeService {
  Recipe create(Recipe recipe);
  
  Recipe findById(int id);
  
  List<Recipe> findAll();

  Recipe updateById(int id, Recipe recipe);

  void deleteById(int id);
}
