package jp.co.softbank.trackproject.client.response;

import lombok.Data;

@Data
public class MessageResponse {
  private String message;
  
  public MessageResponse(String mesage) {
    this.message = mesage;
  }
}
