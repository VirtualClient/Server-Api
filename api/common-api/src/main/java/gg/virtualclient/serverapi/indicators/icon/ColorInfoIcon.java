package gg.virtualclient.serverapi.indicators.icon;

import com.google.gson.JsonObject;

import java.awt.*;

public class ColorInfoIcon implements InfoIcon {

    private final Color color;

    public ColorInfoIcon(Color color) {
        this.color = color;
    }

    public Color color() {
        return color;
    }

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "color");
        jsonObject.addProperty("color", color.getRGB());
        return jsonObject;
    }
}
