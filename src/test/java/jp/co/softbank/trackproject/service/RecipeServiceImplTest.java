package jp.co.softbank.trackproject.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;

import jp.co.softbank.trackproject.model.Recipe;
import jp.co.softbank.trackproject.repository.RecipeRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RecipeServiceImplTest {
  
  @InjectMocks
  private RecipeServiceImpl target;
  
  @Mock
  private RecipeRepository mockRepository;
  
  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void test_create() {
    Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    doNothing().when(mockRepository).insert(recipe);
    Recipe actual = target.create(recipe);
    assertThat(actual, is(recipe));
  }

}
