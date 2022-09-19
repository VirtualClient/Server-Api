package gg.virtualclient.serverapi.indicators;


import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.AbstractServerApi;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import net.kyori.adventure.text.Component;

import java.util.List;

public interface InfoIndicator<Title> {

    List<InfoIcon> icons();

    Title getTitle();

    JsonObject serialize();

    static InfoIndicator<Component> indicator(Component title, List<InfoIcon> icons) {
        return AbstractServerApi.instance().getInfoIndicatorFactory().indicator(title, icons);
    }

    static InfoIndicator<String> indicator(String title, List<InfoIcon> icons) {
        return AbstractServerApi.instance().getInfoIndicatorFactory().indicator(title, icons);
    }

    interface Factory {

        InfoIndicator<String> indicator(String title, List<InfoIcon> icons);

        InfoIndicator<Component> indicator(Component title, List<InfoIcon> icons);

    }
}
