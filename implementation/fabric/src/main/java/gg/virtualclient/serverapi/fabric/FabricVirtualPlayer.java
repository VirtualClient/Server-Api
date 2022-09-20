package gg.virtualclient.serverapi.fabric;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public class FabricVirtualPlayer implements VirtualPlayer {

    private final ServerPlayerEntity player;
    private final String clientVersion;

    public FabricVirtualPlayer(ServerPlayerEntity player, String clientVersion) {
        this.player = player;
        this.clientVersion = clientVersion;
    }

    @Override
    public void setIndicators(List<InfoIndicator<?>> indicators) {
        FabricApiImpl.instance().getIndicatorTransmitter().setIndicators(player, indicators);
    }

    @Override
    public String getClientVersion() {
        return clientVersion;
    }
}