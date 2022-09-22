package gg.virtualclient.serverapi.discord;

import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketTransmitter;
import org.jetbrains.annotations.Nullable;

public class DiscordRPCTransmitterImpl<Player> implements DiscordRPCTransmitter<Player> {

    private PacketTransmitter<Player> packetTransmitter;

    public DiscordRPCTransmitterImpl(PacketTransmitter<Player> packetTransmitter) {
        this.packetTransmitter = packetTransmitter;
    }

    @Override
    public void setGameMode(Player player, @Nullable String gameMode) {
        packetTransmitter.sendPacket(player, createGameModePacket(gameMode));
    }

    @Override
    public void broadcastGameMode(@Nullable String gameMode) {
        packetTransmitter.broadcastPacket(createGameModePacket(gameMode));
    }

    public ClientPacket createGameModePacket(@Nullable String gameMode) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("hasGameMode", gameMode != null);
        if(gameMode != null)
            jsonObject.addProperty("gameMode", gameMode);
        return new ClientPacket("discordrpc", jsonObject);
    }
}
