package gg.virtualclient.serverapi.indicator;

import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;

import java.util.List;

public class StringInfoIndicator implements InfoIndicator<String> {

    @Override
    public List<InfoIcon> icons() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public JsonObject serialize() {
        return null;
    }
}
