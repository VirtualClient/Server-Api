package gg.virtualclient.serverapi.velocity;

import com.velocitypowered.api.proxy.Player;
import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VelocityPlayer implements VirtualPlayer {

    private final Player player;
    private final String clientVersion;

    public VelocityPlayer(Player player, String clientVersion) {
        this.player = player;
        this.clientVersion = clientVersion;
    }

    @Override
    public void setIndicators(List<InfoIndicator<?>> indicators) {
        VirtualApiPlugin.instance().getIndicatorTransmitter().setIndicators(player, indicators);
    }

    @Override
    public void clearBlockedMods() {
        VirtualApiPlugin.instance().getModBlockTransmitter().clearBlockedMods(player);
    }

    @Override
    public void blockMods(List<String> mods) {
        VirtualApiPlugin.instance().getModBlockTransmitter().blockMods(player, mods);
    }

    @Override
    public void unblockMods(List<String> mods) {
        VirtualApiPlugin.instance().getModBlockTransmitter().unblockMods(player, mods);
    }

    @Override
    public void setDiscordGameMode(@Nullable String gameMode) {
        VirtualApiPlugin.instance().getDiscordRPCTransmitter().setGameMode(player, gameMode);
    }

    @Override
    public String getClientVersion() {
        return clientVersion;
    }
}
