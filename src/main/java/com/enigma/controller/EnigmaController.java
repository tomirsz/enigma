package com.enigma.controller;

import com.enigma.model.RequestDto;
import com.enigma.service.CipherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class EnigmaController {

    private final CipherService cipherService;

    @PostMapping(value = "/process")
    public ResponseEntity<?> process(@Valid @RequestBody RequestDto request) {
            return cipherService.encrypt(request);
    }

}