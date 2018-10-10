package com.project.collathon.controller;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.project.collathon.naverlogin.NaverLoginBO;
import com.project.collathon.repository.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private NaverLoginBO naverLoginBO;

    @GetMapping("/login")
    public String login(HttpSession session){
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        return "redirect:" + naverAuthUrl;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
        OAuth2AccessToken oAuthToken = naverLoginBO.getAccessToken(session, code, state);
        Users user = naverLoginBO.getUserProfile(oAuthToken);
        session.setAttribute("user", user);
        return "redirect:/";
    }
}
