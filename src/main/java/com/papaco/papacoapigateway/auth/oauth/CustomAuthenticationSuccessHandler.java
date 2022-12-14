package com.papaco.papacoapigateway.auth.oauth;

import com.papaco.papacoapigateway.auth.jwt.JwtFilter;
import com.papaco.papacoapigateway.auth.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String jwt = tokenProvider.createToken(authentication);

        response.setHeader(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
    }
}
