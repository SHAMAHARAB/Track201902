package jp.co.softbank.trackproject.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.softbank.trackproject.model.Recipe;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceImplTest {
  
  @Autowired
  private RecipeServiceImpl target;

  @Test
  public void test_create() {
    Recipe recipe = new Recipe();
    target.create(recipe);
  }

}
