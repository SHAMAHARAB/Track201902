package jp.co.softbank.trackproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * レシピ削除に失敗した時のExceptionクラスです。
 * @author H.Hamahara
 *
 */
@Data
@AllArgsConstructor
public class RecipeDeleteException extends RuntimeException {

  /**
   * add generated serial version ID.
   */
  private static final long serialVersionUID = -2161853261335299929L;
  
  private String message;
}
