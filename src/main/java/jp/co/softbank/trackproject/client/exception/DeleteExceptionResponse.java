package jp.co.softbank.trackproject.client.exception;

import jp.co.softbank.trackproject.client.response.MessageResponse;
import lombok.Data;

@Data
public class DeleteExceptionResponse extends MessageResponse {
  
  private static final String NOT_FOUND_EXCEPTION_MESSAGE = "No Recipe found";

  public DeleteExceptionResponse() {
    super(NOT_FOUND_EXCEPTION_MESSAGE);
  }
  
}
