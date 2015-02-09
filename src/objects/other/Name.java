package objects.other;

import utils.ObjectUtil;

import java.util.ArrayList;

public class Name {

    public String[][] name;
    private static final ArrayList<String> nameFormat = new ArrayList<String>() {
        {
            add("firstName");
            add("lastName");
        }
    };

    public Name(String nameStr) {
        this.name = this.parseName(nameStr);
    }

    private String[][] parseName(String input) {
        String values[] = ObjectUtil.splitToTokens(input);
        String[][] nameArray = new String[2][2];
        nameArray[0] = new String[] {"lastName", values[0]};
        nameArray[1] = new String[] {"firstName", values[1].trim()};
        return nameArray;
    }

    public static ArrayList<String> getNameFormat() {
        return nameFormat;
    }
}
