package gg.virtualclient.serverapi.spigot.transmitter;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.MinecraftKey;
import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketUtils;
import io.netty.buffer.ByteBuf;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class ProtocolLibTransmitter implements PacketTransmitter {
    @Override
    public void sendPacket(Player player, ClientPacket clientPacket) {
        ByteBuf buffer = PacketUtils.createPayloadBuffer(clientPacket);

        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.CUSTOM_PAYLOAD);
        packetContainer.getMinecraftKeys().write(0, new MinecraftKey("virtualclient", "serverapi"));

        if (MinecraftReflection.is(MinecraftReflection.getPacketDataSerializerClass(), buffer)) {
            packetContainer.getModifier().withType(ByteBuf.class).write(0, buffer);
        } else {
            Object serializer = MinecraftReflection.getPacketDataSerializer(buffer);
            packetContainer.getModifier().withType(ByteBuf.class).write(0, serializer);
        }

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetContainer);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
