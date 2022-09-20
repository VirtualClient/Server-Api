package gg.virtualclient.serverapi.fabric.icon;

import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public final class ItemIndicator implements InfoIcon {
    private final Item item;

    public ItemIndicator(Item item) {
        this.item = item;
    }

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "item");
        jsonObject.addProperty("item", Registry.ITEM.getId(item).toString());
        return jsonObject;
    }

}
