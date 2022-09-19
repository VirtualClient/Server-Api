package gg.virtualclient.serverapi.packet;

import com.google.gson.JsonObject;

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

}
