package objects.products.tickets;

import objects.venue.Venue;
import org.json.*;
import utils.ObjectUtil;

import java.util.ArrayList;

public class GameTicket {

    public JSONObject gameTicket = new JSONObject();
    public JSONObject productsJsonObject;
    public static final String TICKET = "ticket";
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
        this.gameTicket = this.parseGameTicket(gameTicketArray);
        this.productsJsonObject = productsJsonObject;
    }

    public JSONObject parseGameTicket(String[] input) throws JSONException {
        JSONObject ticket = new JSONObject();
        for(int i = 0; i < input.length; i++) {
            Object object = gameTicketFormat.get(i);
            String token = input[i + 2];
            if(object instanceof ArrayList) {
                ticket.put(Venue.VENUE_STRING, ObjectUtil.getVenueDataFromCode(token));
            } else {
                ticket.put(gameTicketFormat.get(i).toString(), token);
            }
        }
        ObjectUtil.addToTicketCodeMap(input[0], ticket);
        this.productsJsonObject.put(GameTicket.TICKET, ticket);
        return ticket;
    }
}
