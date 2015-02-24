package objects.other;

import utils.ObjectUtil;

import java.util.*;

public class Email {

    public static final String EMAILS_STRING = "emails";
    private static final ArrayList<String> emailFormat = new ArrayList<>();
    public ArrayList<String> emailAddresses = new ArrayList<>();

    public Email(String emails) {
        this.emailAddresses = this.parseEmail(emails);
    }

    private ArrayList<String> parseEmail(String input) {
        String values[] = ObjectUtil.splitToTokens(input);
        ArrayList<String> emails = new ArrayList<>();
        Collections.addAll(emails, values);
        return emails;
    }

    public static ArrayList<String> getEmailFormat() {
        return emailFormat;
    }
}
