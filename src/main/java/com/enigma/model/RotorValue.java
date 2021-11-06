package com.enigma.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RotorValue {
    
    @NotBlank
    private char inputChar;
    @NotBlank
    private char outputChar;
}
