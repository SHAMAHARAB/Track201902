package jp.co.softbank.trackproject.client.response;

import lombok.Data;

/**
 * メッセージが記載されたレスポンスクラスです。
 * @author H.Hamahara
 *
 */
@Data
public class MessageResponse {
  private String message;
  
  /**
   * コンストラクタです。
   * 
   * @param mesage 返却するメッセージ
   */
  public MessageResponse(String mesage) {
    this.message = mesage;
  }
}
