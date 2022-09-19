package gg.virtualclient.serverapi.minestom;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import net.minestom.server.entity.Player;

import java.util.List;

public class MineVirtualPlayer implements VirtualPlayer {

    private final Player player;
    private final String clientVersion;

    public MineVirtualPlayer(Player player, String clientVersion) {
        this.player = player;
        this.clientVersion = clientVersion;
    }

    @Override
    public void setIndicators(List<InfoIndicator<?>> indicators) {
        MineStomApiImpl.instance().getIndicatorTransmitter().setIndicators(player, indicators);
    }

    @Override
    public String getClientVersion() {
        return clientVersion;
    }
}
