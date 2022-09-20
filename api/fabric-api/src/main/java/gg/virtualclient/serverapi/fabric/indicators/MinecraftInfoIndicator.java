package gg.virtualclient.serverapi.fabric.indicators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import net.minecraft.text.Text;

import java.util.List;

public class MinecraftInfoIndicator implements InfoIndicator<Text> {
    private Text text;
    private List<InfoIcon> icons;

    public MinecraftInfoIndicator(Text text, List<InfoIcon> icons) {
        this.text = text;
        this.icons = icons;
    }

    @Override
    public List<InfoIcon> icons() {
        return icons;
    }

    @Override
    public Text title() {
        return text;
    }

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("title", Text.Serializer.toJsonTree(text));

        JsonArray icons = new JsonArray();
        for (InfoIcon icon : this.icons) {
            icons.add(icon.serialize());
        }
        jsonObject.add("icons", icons);

        return jsonObject;
    }
}
