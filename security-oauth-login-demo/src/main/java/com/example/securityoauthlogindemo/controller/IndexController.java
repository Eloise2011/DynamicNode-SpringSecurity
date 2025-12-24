package com.example.securityoauthlogindemo.controller;

import com.example.securityoauthlogindemo.MyCommonOAuth2Provider;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2025-12-24 10:49
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User) {
        model.addAttribute("userName", oauth2User.getName());
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        model.addAttribute("userAttributes", oauth2User.getAttributes());
        System.out.println(authorizedClient.getAccessToken().getScopes());
        System.out.println(authorizedClient.getAccessToken().getTokenType().getValue());
        System.out.println(authorizedClient.getAccessToken().getTokenValue());
        System.out.println(authorizedClient.getAccessToken().getIssuedAt());
        System.out.println(authorizedClient.getAccessToken().getExpiresAt());


        return "index";
    }
}
