package objects.other;

import utils.ObjectUtil;

import java.util.ArrayList;

public class Name {

    public String[][] name;
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final ArrayList<String> nameFormat = new ArrayList<String>() {
        {
            add(Name.FIRST_NAME);
            add(Name.LAST_NAME);
        }
    };

    public Name(String nameStr) {
        this.name = this.parseName(nameStr);
    }

    private String[][] parseName(String input) {
        String values[] = ObjectUtil.splitToTokens(input);
        String[][] nameArray = new String[2][2];
        nameArray[0] = new String[] {Name.LAST_NAME, values[0]};
        nameArray[1] = new String[] {Name.FIRST_NAME, values[1].trim()};
        return nameArray;
    }

    public static ArrayList<String> getNameFormat() {
        return nameFormat;
    }
}
