
package com.example.client.service;

import java.util.Map;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.client.config.OAuthProperties;

@Service
public class TokenService {

    private final OAuthProperties props;
    private final RestTemplate restTemplate;

    public TokenService(OAuthProperties props, RestTemplate restTemplate) {
        this.props = props;
        this.restTemplate = restTemplate;
    }

    public String getAccessToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(props.getClientId(), props.getClientSecret());

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", props.getGrantType());
        body.add("scope", props.getScope());

        HttpEntity<?> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(props.getTokenUrl(), request, Map.class);

        return response.getBody().get("access_token").toString();
    }
}
