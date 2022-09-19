package gg.virtualclient.serverapi.packet;

import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.packet.util.ParserHelper;

public class ClientPacket {

    private final String identifier;
    private final JsonObject payload;

    public ClientPacket(String identifier, JsonObject payload) {
        this.identifier = identifier;
        this.payload = payload;
    }

    public String getIdentifier() {
        return identifier;
    }

    public JsonObject getPayload() {
        return payload;
    }

    public static ClientPacket fromString(String data) {
        JsonObject object = ParserHelper.parseJson(data).getAsJsonObject();
        return new ClientPacket(object.get("identifier").getAsString(), object.get("payload").getAsJsonObject());
    }

    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("identifier", identifier);
        object.add("payload", payload);
        return object;
    }

}
