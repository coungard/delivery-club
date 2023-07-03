package com.coungard.model.response;

import com.coungard.security.UserPrincipal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrincipalResponse {

  UserPrincipal principal;
}
