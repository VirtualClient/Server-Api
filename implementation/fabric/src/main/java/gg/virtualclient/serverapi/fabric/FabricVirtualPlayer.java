package gg.virtualclient.serverapi.fabric;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FabricVirtualPlayer implements VirtualPlayer {

    private final ServerPlayerEntity player;
    private final String clientVersion;

    public FabricVirtualPlayer(ServerPlayerEntity player, String clientVersion) {
        this.player = player;
        this.clientVersion = clientVersion;
    }

    @Override
    public void setIndicators(List<InfoIndicator<?>> indicators) {
        FabricApiImpl.instance().getIndicatorTransmitter().setIndicators(player, indicators);
    }

    @Override
    public void clearBlockedMods() {
        FabricApiImpl.instance().getModBlockTransmitter().clearBlockedMods(player);
    }

    @Override
    public void blockMods(List<String> mods) {
        FabricApiImpl.instance().getModBlockTransmitter().blockMods(player, mods);
    }

    @Override
    public void unblockMods(List<String> mods) {
        FabricApiImpl.instance().getModBlockTransmitter().unblockMods(player, mods);
    }

    @Override
    public void setDiscordGameMode(@Nullable String gameMode) {
        FabricApiImpl.instance().getDiscordRPCTransmitter().setGameMode(player, gameMode);
    }

    @Override
    public String getClientVersion() {
        return clientVersion;
    }
}
