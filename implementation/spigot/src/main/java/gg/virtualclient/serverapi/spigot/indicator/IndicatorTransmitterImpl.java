package gg.virtualclient.serverapi.spigot.indicator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.spigot.transmitter.PacketTransmitter;
import gg.virtualclient.serverapi.packet.ClientPacket;
import org.bukkit.entity.Player;

import java.util.List;

public class IndicatorTransmitterImpl implements IndicatorTransmitter<Player> {

    private final PacketTransmitter transmitter;

    public IndicatorTransmitterImpl(PacketTransmitter transmitter) {
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
