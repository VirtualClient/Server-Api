package gg.virtualclient.serverapi.indicators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;

import java.util.List;

public class StringInfoIndicator implements InfoIndicator<String> {
    private final List<InfoIcon> icons;
    private final String title;

    public StringInfoIndicator(List<InfoIcon> icons, String title) {
        this.icons = icons;
        this.title = title;
    }

    @Override
    public List<InfoIcon> icons() {
        return icons;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", title);

        JsonArray icons = new JsonArray();
        for (InfoIcon icon : this.icons) {
            icons.add(icon.serialize());
        }
        jsonObject.add("icons", icons);

        return jsonObject;
    }
}
