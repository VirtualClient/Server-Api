package gg.virtualclient.serverapi.minestorm;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.minestorm.indicator.IndicatorTransmitterImpl;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MineStormApiImpl extends VirtualServerApi {

    private final VirtualApiExtension extension;
    private final IndicatorTransmitter<Player> indicatorTransmitter;

    public MineStormApiImpl(VirtualApiExtension extension) {
        this.extension = extension;
        VirtualServerApi.instance = this;

        this.indicatorTransmitter = new IndicatorTransmitterImpl();
    }

    @Override
    public @Nullable VirtualPlayer getPlayer(UUID uuid) {
        return getPlayer(MinecraftServer.getConnectionManager().getPlayer(uuid));
    }

    @Override
    public IndicatorTransmitter<Player> getIndicatorTransmitter() {
        return indicatorTransmitter;
    }

    @Override
    @Nullable VirtualPlayer getPlayer(Player player) {
        return extension.getPlayer(player);
    }
}
