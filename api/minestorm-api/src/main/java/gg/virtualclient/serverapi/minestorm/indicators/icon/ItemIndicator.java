package gg.virtualclient.serverapi.minestorm.indicators.icon;

import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import net.minestom.server.item.Material;

public record ItemIndicator(Material material) implements InfoIcon {

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "item");
        jsonObject.addProperty("item", material.name());
        return jsonObject;
    }
}
