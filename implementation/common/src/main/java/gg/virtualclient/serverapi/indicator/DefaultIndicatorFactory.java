package gg.virtualclient.serverapi.indicator;

import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.indicators.icon.InfoIcon;
import net.kyori.adventure.text.Component;

import java.util.List;

public class DefaultIndicatorFactory implements InfoIndicator.Factory {
    @Override
    public InfoIndicator<String> indicator(String title, List<InfoIcon> icons) {
        return null;
    }

    @Override
    public InfoIndicator<Component> indicator(Component title, List<InfoIcon> icons) {
        return null;
    }
}
