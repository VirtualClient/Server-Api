package gg.virtualclient.serverapi.indicators;


import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.List;

public interface InfoIndicator<Title> {

    List<InfoIcon> icons();

    Title title();

    JsonObject serialize();

    static InfoIndicator<Component> indicator(Component title, List<InfoIcon> icons) {
        return new AdventureInfoIndicator(icons, title);
    }

    static InfoIndicator<BaseComponent[]> indicator(BaseComponent[] title, List<InfoIcon> icons) {
        return new BungeecordInfoIndicator(icons, title);
    }

    static InfoIndicator<String> indicator(String title, List<InfoIcon> icons) {
        return new StringInfoIndicator(icons, title);
    }
}
