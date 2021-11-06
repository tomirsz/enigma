package com.enigma.enums;


import java.util.List;

public enum RotorType {

    TYPE_I(List.of("Q")),
    TYPE_II(List.of("E")),
    TYPE_III(List.of("V")),
    TYPE_IV(List.of("J")),
    TYPE_V(List.of("Z")),
    TYPE_VI(List.of("Z", "M")),
    TYPE_VII(List.of("Z", "M")),
    TYPE_VIII(List.of("Z", "M"));

    public final List<String> turnover;

    RotorType(List<String> turnover) {
        this.turnover = turnover;
    }
}
