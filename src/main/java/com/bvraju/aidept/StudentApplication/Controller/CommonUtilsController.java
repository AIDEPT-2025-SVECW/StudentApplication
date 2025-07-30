package com.bvraju.aidept.StudentApplication.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CommonUtilsController {

    @GetMapping("/api/csrf-token")
    public Map<String, String> getCsrfToken(HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", csrfToken.getToken());
        tokenMap.put("parameterName", csrfToken.getParameterName());
        tokenMap.put("headerName", csrfToken.getHeaderName());

        return tokenMap;
    }

}
