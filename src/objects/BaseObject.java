package objects;

abstract public class BaseObject {

    //convert an element line of a data file into an array
    protected String[] splitToTokens(String list) {
        return list.split(",");
    }
}
