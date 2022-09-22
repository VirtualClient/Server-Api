package gg.virtualclient.serverapi.velocity.transmitter;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketTransmitter;
import gg.virtualclient.serverapi.packet.PacketUtils;

public class VelocityTransmitter implements PacketTransmitter<Player> {

    public static final MinecraftChannelIdentifier CHANNEL = MinecraftChannelIdentifier
            .create("virtualclient", "serverapi");

    private ProxyServer proxyServer;

    public VelocityTransmitter(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    public void sendPacket(Player player, ClientPacket clientPacket) {
        player.sendPluginMessage(CHANNEL, PacketUtils.packetToBytes(clientPacket));
    }

    @Override
    public void broadcastPacket(ClientPacket clientPacket) {
        proxyServer.getAllPlayers().forEach(player -> sendPacket(player, clientPacket));
    }
}
