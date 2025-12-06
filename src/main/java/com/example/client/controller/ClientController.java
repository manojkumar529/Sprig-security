
package com.example.client.controller;

import com.example.client.service.ExternalApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ExternalApiService apiService;

    public ClientController(ExternalApiService apiService) {
        this.apiService = apiService;
    }
    @GetMapping("/call")
    public ResponseEntity<String> callApi() {
        return ResponseEntity.ok(apiService.callExternalApi());
    }
}
