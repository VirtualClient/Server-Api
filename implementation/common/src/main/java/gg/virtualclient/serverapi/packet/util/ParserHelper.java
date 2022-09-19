package gg.virtualclient.serverapi.packet.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ParserHelper {

    @SuppressWarnings("deprecation")
    //Older Spigot versions have a really old gson lib in some versions, so we have to use this :/
    private static final JsonParser PARSER = new JsonParser();

    @SuppressWarnings("deprecation")
    public static JsonElement parseJson(String string) {
        return PARSER.parse(string);
    }

}
