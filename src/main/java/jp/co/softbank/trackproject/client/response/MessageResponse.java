package jp.co.softbank.trackproject.client.response;

import lombok.Data;

@Data
public class MessageResponse {
  private String mesage;
  
  public MessageResponse(String mesage) {
    this.mesage = mesage;
  }
}
