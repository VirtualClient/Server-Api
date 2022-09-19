package gg.virtualclient.serverapi.indicators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import gg.virtualclient.serverapi.misc.BungeecordChatUtil;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.List;

public class BungeecordInfoIndicator implements InfoIndicator<BaseComponent[]> {
    private final List<InfoIcon> icons;

    private final BaseComponent[] title;

    public BungeecordInfoIndicator(List<InfoIcon> icons, BaseComponent[] title) {
        this.icons = icons;
        this.title = title;
    }

    @Override
    public List<InfoIcon> icons() {
        return icons;
    }

    @Override
    public BaseComponent[] title() {
        return title;
    }

    @Override
    public JsonObject serialize() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("title", BungeecordChatUtil.toJsonTree(title));

        JsonArray icons = new JsonArray();
        for (InfoIcon icon : this.icons) {
            icons.add(icon.serialize());
        }
        jsonObject.add("icons", icons);

        return jsonObject;
    }


}
