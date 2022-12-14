package gg.virtualclient.serverapi.minestom;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.Nullable;

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
    public void clearBlockedMods() {
        MineStomApiImpl.instance().getModBlockTransmitter().clearBlockedMods(player);
    }

    @Override
    public void blockMods(List<String> mods) {
        MineStomApiImpl.instance().getModBlockTransmitter().blockMods(player, mods);
    }

    @Override
    public void unblockMods(List<String> mods) {
        MineStomApiImpl.instance().getModBlockTransmitter().unblockMods(player, mods);
    }

    @Override
    public void setDiscordGameMode(@Nullable String gameMode) {
        MineStomApiImpl.instance().getDiscordRPCTransmitter().setGameMode(player, gameMode);
    }

    @Override
    public String getClientVersion() {
        return clientVersion;
    }
}
