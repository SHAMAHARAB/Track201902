package jp.co.softbank.trackproject.client.response;

import java.util.Arrays;
import java.util.List;

import jp.co.softbank.trackproject.client.dto.RecipeWebDto;
import jp.co.softbank.trackproject.model.Recipe;
import lombok.Data;

@Data
public class RecipeResponse extends MessageResponse {
  private List<RecipeWebDto> recipe;
  
  public RecipeResponse(Recipe recipe) {
    super("Recipe successfully created!");
    this.recipe = Arrays.asList(new RecipeWebDto(recipe));
  }
}
