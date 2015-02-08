package objects.other;

import objects.BaseObject;

import java.util.ArrayList;
import java.util.Collections;

public class Email extends BaseObject{

    private static final ArrayList<String> emailFormat = new ArrayList<String>();

    public Email(String emails) {
        ArrayList<String> emailAddresses = this.parseEmail(emails);
    }

    private ArrayList<String> parseEmail(String input) {
        String values[] = splitToTokens(input);
        ArrayList<String> emails = new ArrayList<String>();
        Collections.addAll(emails, values);
        return emails;
    }

    public static ArrayList<String> getEmailFormat() {
        return emailFormat;
    }
}
