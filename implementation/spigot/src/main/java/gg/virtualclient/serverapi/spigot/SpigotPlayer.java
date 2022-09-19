package gg.virtualclient.serverapi.spigot;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import org.bukkit.entity.Player;

import java.util.List;

public class SpigotPlayer implements VirtualPlayer {

    private final Player player;
    private final String clientVersion;

    public SpigotPlayer(Player player, String clientVersion) {
        this.player = player;
        this.clientVersion = clientVersion;
    }

    @Override
    public void setIndicators(List<InfoIndicator<?>> indicators) {
        SpigotApiImpl.instance().getIndicatorTransmitter().setIndicators(player, indicators);
    }

    @Override
    public String getClientVersion() {
        return clientVersion;
    }
}
