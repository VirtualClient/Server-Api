package gg.virtualclient.serverapi.packet;

public interface PacketTransmitter<Player> {

    void sendPacket(Player player, ClientPacket clientPacket);

    void broadcastPacket(ClientPacket clientPacket);

}
