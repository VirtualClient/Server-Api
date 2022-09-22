package gg.virtualclient.serverapi.spigot;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

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
    public void clearBlockedMods() {
        SpigotApiImpl.instance().getModBlockTransmitter().clearBlockedMods(player);
    }

    @Override
    public void blockMods(List<String> mods) {
        SpigotApiImpl.instance().getModBlockTransmitter().blockMods(player, mods);
    }

    @Override
    public void unblockMods(List<String> mods) {
        SpigotApiImpl.instance().getModBlockTransmitter().unblockMods(player, mods);
    }

    @Override
    public void setDiscordGameMode(@Nullable String gameMode) {
        SpigotApiImpl.instance().getDiscordRPCTransmitter().setGameMode(player, gameMode);
    }

    @Override
    public String getClientVersion() {
        return clientVersion;
    }
}
