package com.enigma.service;

import com.enigma.data.TestData;
import com.enigma.enums.RotorType;
import com.enigma.model.Plugboard;
import com.enigma.model.PlugboardPair;
import com.enigma.model.RequestDto;
import com.enigma.model.RotorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CipherServiceTest {

    @Autowired
    private CipherService cipherService;

    @Test
    void shouldReturnDifferentCharsThanInput() {
        RequestDto requestDto = createRequest(new TestData().getTestString());
        ResponseEntity<?> encrypt = cipherService.encrypt(requestDto);
        long count = Objects.requireNonNull(encrypt.getBody()).toString().chars().filter(ch -> ch == 'A').distinct().count();
        assertEquals(0, count);
    }

    @Test
    void shouldDecryptEncryptedMessage() {
        var orginalMessage = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        RequestDto requestDto1 = createRequest(orginalMessage);
        ResponseEntity<?> encrypt = cipherService.encrypt(requestDto1);
        var encryptedMessage = Objects.requireNonNull(encrypt.getBody()).toString();

        RequestDto requestDto2 = createRequest(encryptedMessage);
        ResponseEntity<?> decrypt = cipherService.encrypt(requestDto2);
        var decryptedMessage = Objects.requireNonNull(decrypt.getBody()).toString();

        assertEquals(orginalMessage, decryptedMessage);
    }

    @Test
    void shouldAddSpacebarInsteadNumbers() {
        RequestDto requestDto = createRequest("1234567890Azxcvbnm");
        ResponseEntity<?> encrypt = cipherService.encrypt(requestDto);

        String expected = "          B       ";
        assertEquals(Objects.requireNonNull(encrypt.getBody()).toString(), expected);
    }

    private RequestDto createRequest(String message) {
        RequestDto requestDto = new RequestDto();
        requestDto.setLeftRotor(createRotor(1, RotorType.TYPE_I));
        requestDto.setMiddleRotor(createRotor(1, RotorType.TYPE_II));
        requestDto.setRightRotor(createRotor(1, RotorType.TYPE_III));
        requestDto.setMessage(message);
        requestDto.setPlugboard(createPlugbord());

        return requestDto;
    }

    private RotorDto createRotor(int value, RotorType rotorType) {
        RotorDto dto = new RotorDto();
        dto.setValue(value);
        dto.setRotorType(rotorType);
        return dto;
    }

    private Plugboard createPlugbord() {
        Plugboard plugboard = new Plugboard();
        plugboard.setPlugboardValues(createPlugbordValues());
        return plugboard;
    }

    private List<PlugboardPair> createPlugbordValues() {
        List<PlugboardPair> plugboardList = new ArrayList<>();
        var switchValue = 65;
        for (var i = 0; i < 10; i++) {
            PlugboardPair plugboardPair = new PlugboardPair();
            plugboardPair.setFirstLetter((char) switchValue);
            switchValue++;
            plugboardPair.setSecondLetter((char) switchValue);
            switchValue++;
            plugboardList.add(plugboardPair);
        }
        return plugboardList;
    }
}