package gg.virtualclient.serverapi.minestorm;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketUtils;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.binary.BinaryWriter;

import java.io.IOException;
import java.util.List;

public class MineVirtualPlayer implements VirtualPlayer {

    private final Player player;
    private final String clientVersion;

    public MineVirtualPlayer(Player player, String clientVersion) {
        this.player = player;
        this.clientVersion = clientVersion;
    }

    @Override
    public void setIndicators(List<InfoIndicator<?>> indicators) {
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        for(InfoIndicator<?> indicator : indicators) {
            array.add(indicator.serialize());
        }
        object.add("indicators", array);

        sendPacket(new ClientPacket("indicators", object));
    }

    public void sendPacket(ClientPacket clientPacket) {
        try(BinaryWriter binaryWriter = new BinaryWriter()) {
            binaryWriter.writeSizedString(clientPacket.serialize().toString());

            player.sendPluginMessage("virtualclient:serverapi", binaryWriter.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getClientVersion() {
        return clientVersion;
    }
}
