package com.coungard.exception;

public class ApplicationError extends RuntimeException {

  public ApplicationError(String message) {
    super(message);
  }
}
