package jp.co.softbank.trackproject.service;

import static org.mockito.Mockito.doNothing;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jp.co.softbank.trackproject.model.Recipe;
import jp.co.softbank.trackproject.repository.RecipeRepository;

public class RecipeServiceImplTest {
  
  @InjectMocks
  private RecipeServiceImpl target;
  
  @Mock
  private RecipeRepository mockRepository;
  

  @Test
  public void test_create() {
    MockitoAnnotations.initMocks(this);
    Recipe recipe = new Recipe();
    doNothing().when(mockRepository).insert(recipe);
    target.create(recipe);
  }

}
