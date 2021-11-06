package com.enigma.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class Plugboard {

    @NotEmpty
    private List<PlugboardPair> plugboardValues;

}
