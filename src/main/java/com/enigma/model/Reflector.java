package com.enigma.model;

import lombok.Data;

@Data
public class Reflector {

    private char[] reflectorInputValues = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private char[] reflectorOutputValues = "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray();
//    private char[] reflectorOutputValues = "FVPJIAOYEDRZXWGCTKUQSBNMHL".toCharArray();
}
