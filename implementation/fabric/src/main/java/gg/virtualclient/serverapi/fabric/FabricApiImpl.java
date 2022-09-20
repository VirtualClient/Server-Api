package gg.virtualclient.serverapi.fabric;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.fabric.transmitter.FabricPacketTransmitter;
import gg.virtualclient.serverapi.indicator.IndicatorTransmitterImpl;
import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.mods.ModBlockTransmitter;
import gg.virtualclient.serverapi.mods.ModBlockTransmitterImpl;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FabricApiImpl extends VirtualServerApi {

    private final VirtualApiEntrypoint mod;
    private final IndicatorTransmitter<ServerPlayerEntity> indicatorTransmitter;
    private final ModBlockTransmitter<ServerPlayerEntity> modBlockTransmitter;

    public FabricApiImpl(VirtualApiEntrypoint mod) {
        this.mod = mod;
        instance = this;

        FabricPacketTransmitter transmitter = new FabricPacketTransmitter(mod);
        this.indicatorTransmitter = new IndicatorTransmitterImpl<>(transmitter);
        this.modBlockTransmitter = new ModBlockTransmitterImpl<>(transmitter);
    }

    @Override
    public IndicatorTransmitter<ServerPlayerEntity> getIndicatorTransmitter() {
        return indicatorTransmitter;
    }

    @Override
    public ModBlockTransmitter<ServerPlayerEntity> getModBlockTransmitter() {
        return modBlockTransmitter;
    }

    @Override
    public @Nullable VirtualPlayer getPlayer(ServerPlayerEntity player) {
        return ((VirtualPlayerHolder) player).getVirtualServerApiPlayer();
    }

    @Override
    public @Nullable VirtualPlayer getPlayer(UUID uuid) {
        return getPlayer(mod.getPlayer(uuid));
    }
}
