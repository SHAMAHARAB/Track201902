package jp.co.softbank.trackproject.repository;

import java.util.List;

import jp.co.softbank.trackproject.model.Recipe;

public interface RecipeRepository {
  void insert(Recipe recipe);
  
  Recipe selectById(int id);
  
  List<Recipe> selectAll();

  void updateById(int id, Recipe recipe);

  void deleteById(int id);
  
}
