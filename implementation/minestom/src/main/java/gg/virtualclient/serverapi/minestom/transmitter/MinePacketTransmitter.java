package gg.virtualclient.serverapi.minestom.transmitter;

import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketTransmitter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.binary.BinaryWriter;

import java.io.IOException;

public class MinePacketTransmitter implements PacketTransmitter<Player> {

    public void sendPacket(Player player, ClientPacket clientPacket) {
        try(BinaryWriter binaryWriter = new BinaryWriter()) {
            binaryWriter.writeSizedString(clientPacket.serialize().toString());

            player.sendPluginMessage("virtualclient:serverapi", binaryWriter.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastPacket(ClientPacket clientPacket) {
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
