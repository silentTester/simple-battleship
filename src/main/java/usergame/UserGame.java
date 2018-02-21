package usergame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserGame {

    public List<String> checkYourself(String userInput) {
        List<String> listUserInput = new ArrayList<String>();

        for (String eachUserInput : userInput.split(",")) {
            listUserInput.add(eachUserInput);
        }

        return listUserInput;
    }
}
