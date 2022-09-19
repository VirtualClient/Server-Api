package gg.virtualclient.serverapi.spigot.transmitter;

import gg.virtualclient.serverapi.packet.ClientPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public interface PacketTransmitter {

    void sendPacket(Player player, ClientPacket clientPacket);

    default void broadcastPacket(ClientPacket clientPacket) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            sendPacket(onlinePlayer, clientPacket);
        }
    }

}
