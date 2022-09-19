package gg.virtualclient.serverapi.indicator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.packet.PacketTransmitter;
import gg.virtualclient.serverapi.packet.ClientPacket;

import java.util.List;

public class IndicatorTransmitterImpl<Player> implements IndicatorTransmitter<Player> {

    private final PacketTransmitter<Player> transmitter;

    public IndicatorTransmitterImpl(PacketTransmitter<Player> transmitter) {
        this.transmitter = transmitter;
    }

    @Override
    public void setIndicators(Player player, List<InfoIndicator<?>> indicators) {
        transmitter.sendPacket(player, createPacket(indicators));
    }

    @Override
    public void broadcastIndicators(List<InfoIndicator<?>> indicators) {
        transmitter.broadcastPacket(createPacket(indicators));
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
