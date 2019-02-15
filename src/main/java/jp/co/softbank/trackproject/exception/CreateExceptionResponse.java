package jp.co.softbank.trackproject.exception;

import jp.co.softbank.trackproject.client.response.MessageResponse;
import lombok.Data;

@Data
public class CreateExceptionResponse extends MessageResponse {
  
  private String required;
  
  public CreateExceptionResponse(String required) {
    super("Recipe creation failed!");
    this.required = required;
  }
}
