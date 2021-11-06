package com.enigma.model;

import com.enigma.enums.RotorType;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class RotorDto {
    @Min(1)
    @Max(26)
    private int value;
    @NotNull
    private RotorType rotorType;
}
