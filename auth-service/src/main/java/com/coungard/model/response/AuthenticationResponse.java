package com.coungard.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

  private String token;
}
