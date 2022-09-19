package gg.virtualclient.serverapi;

import gg.virtualclient.serverapi.indicators.InfoIndicator;

import java.util.List;

public interface VirtualPlayer {

    String getClientVersion();

    void setIndicators(List<InfoIndicator<?>> indicators);

}
