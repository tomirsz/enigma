package com.enigma.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlugboardPair {

    @NotNull
    private char firstLetter;
    @NotNull
    private char secondLetter;
}
