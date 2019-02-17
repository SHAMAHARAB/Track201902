package jp.co.softbank.trackproject.client.exception;

import jp.co.softbank.trackproject.client.response.MessageResponse;
import lombok.Data;

/**
 * レシピの登録失敗を知らせるレスポンスクラスです。
 * @author H.Hamahara
 *
 */
@Data
public class RecipeCreateExceptionResponse extends MessageResponse {
  
  private static final String DAFAULT_MESSAGE = "Recipe creation failed!";
  
  private String required;
  
  /**
   * コンストラクタです。
   * 
   * @param required 必須項目を知らせるメッセージ
   */
  public RecipeCreateExceptionResponse(String required) {
    super(DAFAULT_MESSAGE);
    this.required = required;
  }
}
