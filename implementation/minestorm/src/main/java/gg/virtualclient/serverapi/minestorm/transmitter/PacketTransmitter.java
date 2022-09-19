package gg.virtualclient.serverapi.minestorm.transmitter;

import gg.virtualclient.serverapi.packet.ClientPacket;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.binary.BinaryWriter;

import java.io.IOException;

public class PacketTransmitter {

    public static void sendPacket(Player player, ClientPacket clientPacket) {
        try(BinaryWriter binaryWriter = new BinaryWriter()) {
            binaryWriter.writeSizedString(clientPacket.serialize().toString());

            player.sendPluginMessage("virtualclient:serverapi", binaryWriter.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastPacket(ClientPacket clientPacket) {
        try(BinaryWriter binaryWriter = new BinaryWriter()) {
            binaryWriter.writeSizedString(clientPacket.serialize().toString());
            byte[] bytes = binaryWriter.toByteArray();

            for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                player.sendPluginMessage("virtualclient:serverapi", bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
