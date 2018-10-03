package com.project.collathon.naverlogin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.project.collathon.repository.users.Users;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
public class NaverLoginBO {

    private final static String CLIENT_ID = "RWxZ72ZPH8UeIatPK3JZ";
    private final static String CLIENT_SECRET = "oop3AOSOtI";
    private final static String REDIRECT_URL = "http://localhost:8080/callback";
    private final static String SESSION_STATE = "oauth_state";
    private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
    /* 네이버 로그인 인증 URL 생성 Method */
    public String getAuthorizationUrl(HttpSession session){

        /* 세션 유효성 검증을 위한 난수 생성 */
        String state = generateRandomString();
        setSession(session, state);

        OAuth20Service oAuth = new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URL)
                .state(state)
                .build(NaverLoginApi.instance());
        return oAuth.getAuthorizationUrl();
    }

    public OAuth2AccessToken getAccessToken (HttpSession session, String code, String state) throws IOException {
        String sessionState = getSession(session);
        if(StringUtils.equals(sessionState, state)){
            OAuth20Service oAuth = new ServiceBuilder()
                    .apiKey(CLIENT_ID)
                    .apiSecret(CLIENT_SECRET)
                    .callback(REDIRECT_URL)
                    .state(state)
                    .build(NaverLoginApi.instance());

            OAuth2AccessToken token = oAuth.getAccessToken(code);
            return token;
        }
        return null;
    }

    public Users getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        OAuth20Service oAuthService = new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URL)
                .build(NaverLoginApi.instance());
        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oAuthService);
        oAuthService.signRequest(oauthToken, request);
        String result = request.send().getBody();
        Map<String, Object> map = mapper.readValue(result, new TypeReference<Map<String, Object>>(){});
        Users user = mapper.readValue(mapper.writeValueAsString(map.get("response")),Users.class);
        return user;
    }

    private String generateRandomString(){
        return UUID.randomUUID().toString();
    }

    private void setSession(HttpSession session, String state){
        session.setAttribute(SESSION_STATE, state);
    }

    private String getSession(HttpSession session){
        return (String) session.getAttribute(SESSION_STATE);
    }
}
