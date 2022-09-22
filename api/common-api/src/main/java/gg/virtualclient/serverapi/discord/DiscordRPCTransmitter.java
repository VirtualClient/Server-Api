package gg.virtualclient.serverapi.discord;

import org.jetbrains.annotations.Nullable;

public interface DiscordRPCTransmitter<Player> {

    void setGameMode(Player player, @Nullable String gameMode);

    void broadcastGameMode(@Nullable String gameMode);

}
