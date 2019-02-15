package jp.co.softbank.trackproject.client.exception;

import jp.co.softbank.trackproject.client.response.MessageResponse;
import lombok.Data;

/**
 * レシピの登録失敗を知らせるレスポンスクラスです。
 * @author H.Hamahara
 *
 */
@Data
public class CreateExceptionResponse extends MessageResponse {
  
  private String required;
  
  /**
   * コンストラクタです。
   * 
   * @param required 必須項目を知らせるメッセージ
   */
  public CreateExceptionResponse(String required) {
    super("Recipe creation failed!");
    this.required = required;
  }
}
