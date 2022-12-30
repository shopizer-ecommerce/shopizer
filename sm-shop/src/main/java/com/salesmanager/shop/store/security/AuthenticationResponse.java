package com.salesmanager.shop.store.security;

import java.io.Serializable;
import com.salesmanager.shop.model.entity.Entity;

public class AuthenticationResponse extends Entity implements Serializable {
  public AuthenticationResponse() {}

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String token;

  public AuthenticationResponse(Long userId, String token) {
    this.token = token;
    super.setId(userId);
  }

  public String getToken() {
    return token;
  }

}
