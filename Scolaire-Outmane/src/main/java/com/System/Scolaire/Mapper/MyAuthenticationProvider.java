package com.System.Scolaire.Mapper;

import java.util.Arrays;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.System.Scolaire.service.UserService;

// @Component
// public class MyAuthenticationProvider implements AuthenticationProvider {

// @Override
// public @Nullable Authentication authenticate(Authentication authentication)
// throws AuthenticationException {

// String Username = authentication.getName();
// String Password = authentication.getCredentials().toString();

// if ("bate5a".equals(Username) && "1234".equals(Password)) {
// return new UsernamePasswordAuthenticationToken(Username, Password,
// Arrays.asList());
// } else {

// return new BadCredentialsException("Invalid UserName Or
// Password").getAuthenticationRequest();
// }
// }

// @Override
// public boolean supports(Class<?> authentication) {

// return authentication.equals(UsernamePasswordAuthenticationToken.class);
// }

// }
