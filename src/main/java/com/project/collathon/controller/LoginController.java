package com.project.collathon.controller;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.project.collathon.naverlogin.NaverLoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private NaverLoginBO naverLoginBO;

    @GetMapping("/login")
    public ModelAndView login(HttpSession session){
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        return new ModelAndView("login", "url", naverAuthUrl);
    }

    @GetMapping("/callback")
    public ModelAndView callback(@RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
        OAuth2AccessToken oAuthToken = naverLoginBO.getAccessToken(session, code, state);
        String apiResult = naverLoginBO.getUserProfile(oAuthToken);
        return new ModelAndView("callback", "result", apiResult);
    }
}
