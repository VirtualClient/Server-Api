package gg.virtualclient.serverapi.indicators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

import java.util.List;

public class AdventureInfoIndicator implements InfoIndicator<Component> {
    private final List<InfoIcon> icons;

    private final Component title;

    public AdventureInfoIndicator(List<InfoIcon> icons, Component title) {
        this.icons = icons;
        this.title = title;
    }

    @Override
    public List<InfoIcon> icons() {
        return icons;
    }

    @Override
    public Component title() {
        return title;
    }

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("title", GsonComponentSerializer.gson().serializeToTree(title));

        JsonArray icons = new JsonArray();
        for (InfoIcon icon : this.icons) {
            icons.add(icon.serialize());
        }
        jsonObject.add("icons", icons);

        return jsonObject;
    }

}
