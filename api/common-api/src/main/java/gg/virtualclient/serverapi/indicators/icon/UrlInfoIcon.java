package gg.virtualclient.serverapi.indicators.icon;

import com.google.gson.JsonObject;

public class UrlInfoIcon implements InfoIcon {

    private final String url;

    public UrlInfoIcon(String url) {
        this.url = url;
    }

    public String url() {
        return url;
    }

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "url");
        jsonObject.addProperty("url", url);
        return jsonObject;
    }
}
