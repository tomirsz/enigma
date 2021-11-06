package com.enigma.service;

import com.enigma.enums.Direction;
import com.enigma.model.*;
import com.enigma.validation.PlugboardValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CipherService {

    public static final String PLUGBORD_IS_WRONG_CONNECTED = "Plugbord is wrong connected";
    private final PlugboardValidator validator;
    private final Reflector reflector = new Reflector();
    private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public CipherService(PlugboardValidator validator) {
        this.validator = validator;
    }

    public ResponseEntity<?> encrypt(RequestDto request) {
        if (validateRequest(request)) {
            return ResponseEntity.ok(process(request));
        } else {
            return ResponseEntity.badRequest().body(PLUGBORD_IS_WRONG_CONNECTED);
        }
    }

    private boolean validateRequest(RequestDto requestDto) {
        return validator.isPlugboardValid(requestDto.getPlugboard());
    }

    private String process(RequestDto dto) {
        var rightRotor = new Rotor(dto.getRightRotor());
        var middleRotor = new Rotor(dto.getMiddleRotor());
        var leftRotor = new Rotor(dto.getLeftRotor());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < dto.getMessage().length(); i++) {
            var plugbord = dto.getPlugboard();
            var character = plugboardProcess(dto.getMessage().charAt(i), plugbord);

            if (character < 65 || character > 91) {
                sb.append(' ');
            } else {
                rightRotor = rightRotor.rotateRotor();
                if (rightRotor.isRotated()) {
                    middleRotor = middleRotor.rotateRotor();
                    if (middleRotor.isRotated()) {
                        leftRotor = leftRotor.rotateRotor();
                    }
                }


                var index = findIndex(character, alphabet);
                index = rotorProcess(index, rightRotor, Direction.RIGHT_TO_LEFT);
                index = rotorProcess(index, middleRotor, Direction.RIGHT_TO_LEFT);
                index = rotorProcess(index, leftRotor, Direction.RIGHT_TO_LEFT);
                index = reflectorProcess(index);
                index = rotorProcess(index, leftRotor, Direction.LEFT_TO_RIGHT);
                index = rotorProcess(index, middleRotor, Direction.LEFT_TO_RIGHT);
                index = rotorProcess(index, rightRotor, Direction.LEFT_TO_RIGHT);

                character = plugboardProcess(alphabet[index], plugbord);
                sb.append(character);
            }
        }
        log.info("Final string: {}", sb);
        return sb.toString();
    }

    private char plugboardProcess(char character, Plugboard plugboard) {
        List<PlugboardPair> plugboardValues = plugboard.getPlugboardValues();

        for (PlugboardPair pair : plugboardValues) {
            if (pair.getFirstLetter() == character) {
                character = pair.getSecondLetter();
                return character;
            } else if (pair.getSecondLetter() == character) {
                character = pair.getFirstLetter();
                return character;
            } else {
                return character;
            }
        }
        return character;
    }

    private int rotorProcess(int idx, Rotor rotor, Direction direction) {
        char[] inputValues;
        char[] outputValues;
        if (direction == Direction.RIGHT_TO_LEFT) {
            inputValues = rotor.getRotorInputValues();
            outputValues = rotor.getRotorOutputValues();
        } else {
            inputValues = rotor.getRotorOutputValues();
            outputValues = rotor.getRotorInputValues();
        }
        var inputValue = inputValues[idx];

        return findIndex(inputValue, outputValues);
    }

    private int findIndex(char character, char[] array) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == character) {
                index = i;
                return index;
            }
        }
        return index;
    }

    private int reflectorProcess(int index) {
        var reflectorInputValues = reflector.getReflectorInputValues();
        var reflectorOutputValues = reflector.getReflectorOutputValues();
        var character = reflectorInputValues[index];

        for (int i = 0; i < reflectorOutputValues.length; i++) {
            if (reflectorOutputValues[i] == character && i != index) {
                index = i;
                return index;
            }
        }
        return index;
    }

}
