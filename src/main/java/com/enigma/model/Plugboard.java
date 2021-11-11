package com.enigma.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Plugboard {

    @NotEmpty
    @Size(min = 1, max = 10)
    private List<@Valid PlugboardPair> plugboardValues;

}
