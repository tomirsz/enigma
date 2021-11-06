package com.enigma.validation;

import com.enigma.model.Plugboard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlugboardValidator {

    private List<Character> validationList = new ArrayList<>();

    public PlugboardValidator() {
        this.validationList = initValidationList();
    }

    public boolean isPlugboardValid(Plugboard plugboard) {
        List<Character> validationList = this.validationList;
        var plugbordConnections = plugboard.getPlugboardValues().size();
        try {
            plugboard.getPlugboardValues().forEach(plugboardPair -> {
                validationList.remove(Character.valueOf(plugboardPair.getFirstLetter()));
                validationList.remove(Character.valueOf(plugboardPair.getSecondLetter()));
            });
            return validationList.size() == 26 - (plugbordConnections * 2);
        } catch (Exception e) {
            return false;
        }
    }

    private List<Character> initValidationList() {
        int firstCharNumber = 65;
        for (int i = 0; i < 26; i++) {
            validationList.add((char) firstCharNumber);
            firstCharNumber++;
        }
        return validationList;
    }
}
