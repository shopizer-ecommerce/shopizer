package com.salesmanager.shop.store.security.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import com.salesmanager.shop.store.security.JWTTokenUtil;
import com.salesmanager.shop.store.security.common.CustomAuthenticationException;
import com.salesmanager.shop.store.security.common.CustomAuthenticationManager;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;
import io.jsonwebtoken.ExpiredJwtException;

@Component("jwtCustomAdminAuthenticationManager")
public class JWTAdminAuthenticationManager extends CustomAuthenticationManager {

  protected final Log logger = LogFactory.getLog(getClass());
  private static final String BEARER = "Bearer";

  @Inject
  private JWTTokenUtil jwtTokenUtil;

  @Inject
  private UserDetailsService jwtAdminDetailsService;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {

    final String requestHeader = request.getHeader(super.getTokenHeader());// token
    String username = null;
    final String authToken;

    authToken = ofNullable(requestHeader).map(value -> removeStart(value, BEARER)).map(String::trim)
        .orElseThrow(() -> new CustomAuthenticationException("Missing Authentication Token"));

    try {
      username = jwtTokenUtil.getUsernameFromToken(authToken);
    } catch (IllegalArgumentException e) {
      logger.error("an error occured during getting username from token", e);
    } catch (ExpiredJwtException e) {
      logger.warn("the token is expired and not valid anymore", e);
    }


    UsernamePasswordAuthenticationToken authentication = null;

    logger.info("checking authentication for user " + username);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      // It is not compelling necessary to load the use details from the database. You could also
      // store the information
      // in the token and read it from it. It's up to you ;)
      UserDetails userDetails = this.jwtAdminDetailsService.loadUserByUsername(username);

      // For simple validation it is completely sufficient to just check the token integrity. You
      // don't have to call
      // the database compellingly. Again it's up to you ;)
      if (userDetails != null && jwtTokenUtil.validateToken(authToken, userDetails)) {
        authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        logger.info("authenticated user " + username + ", setting security context");
        // SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    return authentication;
  }

  @Override
  public void successfullAuthentication(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws AuthenticationException {
    // TODO Auto-generated method stub

  }

  @Override
  public void unSuccessfullAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    // TODO Auto-generated method stub

  }

}
