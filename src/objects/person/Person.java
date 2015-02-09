package objects.person;


import objects.base.BaseObject;
import objects.other.Address;
import objects.other.Email;
import objects.other.Name;
import org.json.*;
import utils.ObjectUtil;

import java.io.IOException;
import java.util.ArrayList;

public class Person extends BaseObject {


    private String personCode;
    private static ArrayList<String> name = Name.getNameFormat();
    private static ArrayList<String> address = Address.getAddressFormat();
    private static ArrayList<String> emailAddresses = Email.getEmailFormat();
    private static final ArrayList<Object> personFormat = new ArrayList<Object>() {
        {
            add("personCode");
            add(name);
            add(address);
            add(emailAddresses);
        }
    };
    private static ArrayList<Object> person = personFormat;

    public Person(String filePath) throws IOException, JSONException {
        super(filePath);
        this.JSONname = "persons";
        this.finalJSON = this.convertToJSON(fileArray);
        this.finalJSONString = this.finalJSON.toString(2);
        this.outerJSONObject = createJSONShell(this.JSONname, this.finalJSONString);
    }

    private JSONArray convertToJSON(ArrayList<String[]> fileArray) throws JSONException {
        for(int counter = 1; counter <= this.numberOfRecords; counter ++) {
            lineTokenArray = fileArray.get(counter);
            JSONObject jsonObject = new JSONObject();
            for(int count = 0; count < lineTokenArray.length; count ++) {
                Object object = person.get(count);
                value = lineTokenArray[count];
                if(!(object instanceof ArrayList)) {
                    personCode = value;
                    jsonObject.put(object.toString(), personCode);
                } else {
                    if(object == Email.getEmailFormat()) {
                        Email emails = new Email(value);
                        jsonObject.put(Email.EMAILS_STRING, emails.emailAddresses);
                    } else if(object == Address.getAddressFormat()) {
                        Address address = new Address(value);
                        jsonObject.put(Address.ADDRESS_STRING, address.address);
                    } else if(object == Name.getNameFormat()) {
                        Name name = new Name(value);
                        jsonObject.put(name.name[0][0], name.name[0][1]);
                        jsonObject.put(name.name[1][0], name.name[1][1]);
                    }
                }
            }
            ObjectUtil.addToPersonCodeMap(personCode, jsonObject);
            JSONArrayList.put(jsonObject);
        }
        return JSONArrayList;
    }
}
