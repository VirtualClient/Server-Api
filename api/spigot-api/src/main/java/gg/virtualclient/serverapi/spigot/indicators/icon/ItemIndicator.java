package gg.virtualclient.serverapi.spigot.indicators.icon;

import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import org.bukkit.Material;

public class ItemIndicator implements InfoIcon {

    private final Material material;

    public ItemIndicator(Material material) {
        this.material = material;
    }

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "item");
        jsonObject.addProperty("item", material.getKey().asString());
        return jsonObject;
    }

}
