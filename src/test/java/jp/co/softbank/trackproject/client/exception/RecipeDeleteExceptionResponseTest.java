package jp.co.softbank.trackproject.client.exception;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class RecipeDeleteExceptionResponseTest {

  @Test
  public void test() {
    // test
    RecipeDeleteExceptionResponse target = new RecipeDeleteExceptionResponse();
    
    // verify
    assertThat(target.getMessage(), is("No Recipe found"));
  }

}
