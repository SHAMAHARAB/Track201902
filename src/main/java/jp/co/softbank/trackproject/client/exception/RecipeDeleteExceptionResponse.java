package jp.co.softbank.trackproject.client.exception;

import jp.co.softbank.trackproject.client.response.MessageResponse;
import lombok.Data;

/**
 * レシピの削除失敗を知らせるレスポンスクラスです。
 * @author H.Hamahara
 *
 */
@Data
public class RecipeDeleteExceptionResponse extends MessageResponse {
  
  private static final String NOT_FOUND_EXCEPTION_MESSAGE = "No Recipe found";

  /**
   * コンストラクタです。
   * 
   */
  public RecipeDeleteExceptionResponse() {
    super(NOT_FOUND_EXCEPTION_MESSAGE);
  }
  
  /**
   * コンストラクタです。
   * 
   * @param message 表示したいメッセージ
   */
  public RecipeDeleteExceptionResponse(String message) {
    super(message);
  }
  
}
