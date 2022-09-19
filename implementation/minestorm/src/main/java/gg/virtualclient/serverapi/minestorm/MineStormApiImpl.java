package gg.virtualclient.serverapi.minestorm;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MineStormApiImpl extends VirtualServerApi {

    private final VirtualApiExtension extension;

    public MineStormApiImpl(VirtualApiExtension extension) {
        this.extension = extension;
    }

    @Override
    public InfoIndicator.Factory getInfoIndicatorFactory() {
        return null;
    }

    @Override
    public @Nullable VirtualPlayer getPlayer(UUID uuid) {
        return getPlayer(MinecraftServer.getConnectionManager().getPlayer(uuid));
    }

    @Override
    @Nullable VirtualPlayer getPlayer(Player player) {
        return extension.getPlayer(player);
    }
}
