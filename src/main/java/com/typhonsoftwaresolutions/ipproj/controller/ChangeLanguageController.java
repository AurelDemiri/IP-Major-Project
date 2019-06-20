package com.typhonsoftwaresolutions.ipproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangeLanguageController {

    @PostMapping("/changelanguage")
    public String changeLanguage(HttpServletRequest request) {
        // This is a horrible solution because Referer is not reliable
        // This could open us up to XSS but I am running ideas..
        return "redirect:" + request.getHeader("Referer");
    }
}
