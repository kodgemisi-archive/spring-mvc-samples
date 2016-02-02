package com.mvc.spring.sample.config;

import com.mvc.spring.sample.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public static String CURRENT_ACCOUNT_SESSION_KEY = "CURRENT_ACCOUNT_SESSION_KEY";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User currentUser = (User) authentication.getPrincipal();
        HttpSession session = request.getSession();

        session.setAttribute(CURRENT_ACCOUNT_SESSION_KEY, currentUser);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}