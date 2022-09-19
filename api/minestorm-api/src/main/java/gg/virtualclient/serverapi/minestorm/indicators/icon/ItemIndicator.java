package gg.virtualclient.serverapi.minestorm.indicators.icon;

import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import net.minestom.server.item.Material;

public class ItemIndicator implements InfoIcon {

    private final Material material;

    public ItemIndicator(Material material) {
        this.material = material;
    }

    public Material material() {
        return material;
    }

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "item");
        jsonObject.addProperty("material", material.name());
        return jsonObject;
    }
}
