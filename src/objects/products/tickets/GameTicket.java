package objects.products.tickets;

import objects.venue.Venue;
import utils.ObjectUtil;

import org.json.*;
import java.util.ArrayList;

public class GameTicket {

    public JSONObject gameTicket = new JSONObject();
    public JSONObject productsJsonObject = new JSONObject();
    public static final String DATE_TIME = "gameDateTime";
    public static final String TEAM_ONE = "team1";
    public static final String TEAM_TWO = "team2";
    public static final String PRICE = "price";
    public static ArrayList<Object> venue = Venue.getVenueFormat();
    private static final ArrayList<Object> gameTicketFormat = new ArrayList<Object>() {
        {
            add(venue);
            add(GameTicket.DATE_TIME);
            add(GameTicket.TEAM_ONE);
            add(GameTicket.TEAM_TWO);
            add(GameTicket.PRICE);
        }
    };

    public GameTicket(String[] gameTicketArray, JSONObject productsJsonObject) throws JSONException {
        this.productsJsonObject = productsJsonObject;
        this.gameTicket = this.parseGameTicket(gameTicketArray);
    }

    private JSONObject parseGameTicket(String[] input) throws JSONException {
        for(int i = 0; i < input.length - 2; i++) {
            Object object = gameTicketFormat.get(i);
            String token = input[i + 2];
            if(object instanceof ArrayList) {
                this.productsJsonObject.put(Venue.VENUE_STRING, ObjectUtil.getVenueDataFromCode(token));
            } else {
                this.productsJsonObject.put(object.toString(), token);
            }
        }
        ObjectUtil.addToTicketCodeMap(input[0], this.productsJsonObject);
        return this.productsJsonObject;
    }
}
