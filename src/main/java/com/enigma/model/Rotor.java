package com.enigma.model;

import com.enigma.enums.RotorType;
import lombok.Data;

@Data
public class Rotor {
    private char[] rotorInputValues = new char[26];
    private char[] rotorOutputValues = new char[26];
    private int initialValue;
    private RotorType rotorType;
    private boolean rotated;

    public Rotor(RotorDto dto) {
        this.initialValue = dto.getValue();
        this.rotorType = dto.getRotorType();
        initRotorMaps(rotorType);
        rotorInputValues = setInitialValue(initialValue -1, rotorInputValues);
        rotorOutputValues = setInitialValue(initialValue -1, rotorOutputValues);
    }

    public Rotor rotateRotor() {
        rotated = false;
        char[] newInputArray = new char[26];
        char[] newOutputArray = new char[26];
        for (int i = 0; i < rotorInputValues.length; i++) {
            if (i - 1 < 0) {
                newInputArray[25] = rotorInputValues[i];
                newOutputArray[25] = rotorOutputValues[i];
            } else {
                newInputArray[i - 1] = rotorInputValues[i];
                newOutputArray[i - 1] = rotorOutputValues[i];
            }
        }

        if (ifIsRotated(newOutputArray[25])) {
            rotated = true;
        }

        rotorInputValues = newInputArray;
        rotorOutputValues = newOutputArray;
        return this;
    }

    private boolean ifIsRotated(char lastChar) {
        return rotorType.turnover.stream().anyMatch( r -> r.equals(String.valueOf(lastChar)));
    }

    private void initRotorMaps(RotorType rotorType) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] typeIvalues = "EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray();
        char[] typeIIvalues = "AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray();
        char[] typeIIIvalues = "BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray();
        char[] typeIVvalues = "ESOVPZJAYQUIRHXLNFTGKDCMWB".toCharArray();
        char[] typeVvalues = "VZBRGITYUPSDNHLXAWMJQOFECK".toCharArray();
        char[] typeVIvalues = "JPGVOUMFYQBENHZRDKASXLICTW".toCharArray();
        char[] typeVIIvalues = "NZJHGRCXMYSWBOUFAIVLPEKQDT".toCharArray();
        char[] typeVIIIvalues = "FKQHTLXOCBJSPDZRAMEWNIUYGV".toCharArray();

        rotorOutputValues = alphabet;
        switch (rotorType) {
            case TYPE_I:
                rotorInputValues = typeIvalues;
                break;
            case TYPE_II:
                rotorInputValues = typeIIvalues;
                break;
            case TYPE_III:
                rotorInputValues = typeIIIvalues;
                break;
            case TYPE_IV:
                rotorInputValues = typeIVvalues;
                break;
            case TYPE_V:
                rotorInputValues = typeVvalues;
                break;
            case TYPE_VI:
                rotorInputValues = typeVIvalues;
                break;
            case TYPE_VII:
                rotorInputValues = typeVIIvalues;
                break;
            default:
                rotorInputValues = typeVIIIvalues;
                break;
        }
    }

    private char[] setInitialValue(int value, char[] array) {
        char[] newArray = new char[26];
        for (int i = 0; i < array.length; i++) {
            if (i - value < 0) {
                var shiftValue = i - value + 26;
                newArray[shiftValue] = array[i];
            } else {
                newArray[i - value] = array[i];
            }
        }
        return newArray;
    }
}
