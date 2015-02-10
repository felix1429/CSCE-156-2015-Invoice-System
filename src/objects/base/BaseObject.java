package objects.base;


import org.json.JSONArray;
import utils.ObjectUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;asdf

abstract public class BaseObject {

    protected String[] lineTokenArray;
    protected int numberOfRecords;
    protected String JSONname;
    protected String finalJSONString;
    protected JSONArray finalJSON;
    protected String value;
    protected String outerJSONObject;
    protected JSONArray JSONArrayList = new JSONArray();
    protected ArrayList<String[]> fileArray = new ArrayList<>();

    public BaseObject(String filePath) throws IOException {
        this.readFileToArray(filePath);
        this.numberOfRecords = this.getNumberOfRecords(fileArray.get(0));
    }

    public ArrayList<String[]> readFileToArray(String path) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = null;
            while((line = br.readLine()) != null) {
                lineTokenArray = ObjectUtil.parseLineToTokens(line);
                fileArray.add(lineTokenArray);
            }
            return fileArray;
        }
    }

    private int getNumberOfRecords(String arr[]) throws IOException {
        return Integer.parseInt(arr[0]);
    }

    public String getJSONShell() {
        return this.outerJSONObject;
    }

    public String createJSONShell(String JSONName, String finalString) {
        return "{\n\"" + JSONName + "\": " + finalString + "}";
    }
}
