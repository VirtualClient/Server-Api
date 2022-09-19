package gg.virtualclient.serverapi.spigot;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.spigot.indicator.IndicatorTransmitterImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class SpigotApiImpl extends VirtualServerApi {

    private final VirtualApiPlugin plugin;
    private final IndicatorTransmitter<Player> indicatorTransmitter;

    public SpigotApiImpl(VirtualApiPlugin plugin) {
        this.plugin = plugin;
        VirtualServerApi.instance = this;

        this.indicatorTransmitter = new IndicatorTransmitterImpl(plugin.getPacketTransmitter());
    }

    @Override
    public @Nullable VirtualPlayer getPlayer(UUID uuid) {
        return getPlayer(Bukkit.getPlayer(uuid));
    }

    @Override
    public IndicatorTransmitter<Player> getIndicatorTransmitter() {
        return indicatorTransmitter;
    }

    @Override
    @Nullable VirtualPlayer getPlayer(Player player) {
        return plugin.getPlayer(player);
    }
}
