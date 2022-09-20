package gg.virtualclient.serverapi.fabric.transmitter;

import gg.virtualclient.serverapi.fabric.VirtualApiEntrypoint;
import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketTransmitter;
import gg.virtualclient.serverapi.packet.PacketUtils;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class FabricPacketTransmitter implements PacketTransmitter<ServerPlayerEntity> {

    private VirtualApiEntrypoint mod;

    public FabricPacketTransmitter(VirtualApiEntrypoint mod) {
        this.mod = mod;
    }

    public void sendPacket(ServerPlayerEntity player, ClientPacket clientPacket) {
        ServerPlayNetworking.send(player, new Identifier("virtualclient", "serverapi"),
                new PacketByteBuf(PacketUtils.createPayloadBuffer(clientPacket)));
    }

    public void broadcastPacket(ClientPacket clientPacket) {
        for (ServerPlayerEntity allPlayer : mod.getAllPlayers()) {
            sendPacket(allPlayer, clientPacket);
        }
    }

}
