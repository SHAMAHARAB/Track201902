package jp.co.softbank.trackproject.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    // prepare
    Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    doNothing().when(mockRepository).insert(recipe);
    
    // test
    Recipe actual = target.create(recipe);
    
    // verify
    assertThat(actual, is(recipe));
  }
  
  @Test
  public void test_findById() {
    // prepare
    Recipe expected = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    when(mockRepository.selectById(1)).thenReturn(expected);
    
    // test
    Recipe actual = target.findById(1);
    
    // verify
    assertThat(actual, is(expected));
  }
  
  @Test
  public void test_findAll() {
    // prepare
    List<Recipe> expected = Arrays.asList(
        new Recipe("チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000),
        new Recipe("オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700),
        new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
        
    when(mockRepository.selectAll()).thenReturn(expected);
    
    // test
    List<Recipe> actual = target.findAll();
    
    // verify
    assertThat(actual.size(), is(3));
    assertThat(actual, is(expected));
    assertThat(actual, is(samePropertyValuesAs(expected)));
  }

  @Test
  public void test_findAll_empty() {
    // prepare        
    when(mockRepository.selectAll()).thenReturn(Collections.emptyList());
    
    // test
    List<Recipe> actual = target.findAll();
    
    // verify
    assertThat(actual.size(), is(0));
    assertThat(actual, is(Collections.emptyList()));
  }
  
  @Test
  public void test_update() {
    // prepare
    int id = 1;
    Recipe expected = new Recipe("トマトスープレシピ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    doNothing().when(mockRepository).updateById(id, expected);
    
    // test
    Recipe actual = target.updateById(id, expected);
    
    // verify
    assertThat(actual, is(expected));
  }
}
