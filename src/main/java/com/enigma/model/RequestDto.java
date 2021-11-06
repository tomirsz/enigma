package com.enigma.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestDto {

    @NotNull
    private RotorDto leftRotor;
    @NotNull
    private RotorDto middleRotor;
    @NotNull
    private RotorDto rightRotor;
    @NotBlank
    private String message;
    @NotNull
    private Plugboard plugboard;
}
