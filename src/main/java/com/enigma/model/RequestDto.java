package com.enigma.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestDto {

    @NotNull
    @Valid
    private RotorDto leftRotor;
    @NotNull
    @Valid
    private RotorDto middleRotor;
    @NotNull
    @Valid
    private RotorDto rightRotor;
    @NotBlank
    private String message;
    @NotNull
    @Valid
    private Plugboard plugboard;
}
