package gg.virtualclient.serverapi;

import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.mods.ModBlockTransmitter;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractServerApi<Player> {

    protected static AbstractServerApi<?> instance;

    @Nullable
    public abstract VirtualPlayer getPlayer(Player player);

    @Nullable
    public abstract VirtualPlayer getPlayer(UUID uuid);

    public abstract IndicatorTransmitter<Player> getIndicatorTransmitter();

    public abstract ModBlockTransmitter<Player> getModBlockTransmitter();

    @SuppressWarnings("unchecked")
    public static <P, T extends AbstractServerApi<P>> T instance() {
        return (T) instance;
    }

}