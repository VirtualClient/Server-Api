package gg.virtualclient.serverapi.minestorm.indicator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.minestorm.transmitter.PacketTransmitter;
import gg.virtualclient.serverapi.packet.ClientPacket;
import net.minestom.server.entity.Player;

import java.util.List;

public class IndicatorTransmitterImpl implements IndicatorTransmitter<Player> {
    @Override
    public void setIndicators(Player player, List<InfoIndicator<?>> indicators) {
        PacketTransmitter.sendPacket(player, createPacket(indicators));
    }

    @Override
    public void broadcastIndicators(List<InfoIndicator<?>> indicators) {
        PacketTransmitter.broadcastPacket(createPacket(indicators));
    }

    private ClientPacket createPacket(List<InfoIndicator<?>> indicators) {
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        for(InfoIndicator<?> indicator : indicators) {
            array.add(indicator.serialize());
        }
        object.add("indicators", array);

        return new ClientPacket("indicators", object);
    }
}
