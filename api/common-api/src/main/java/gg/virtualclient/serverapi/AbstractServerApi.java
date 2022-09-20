package gg.virtualclient.serverapi;

import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractServerApi<Player> {

    protected static AbstractServerApi<?> instance;

    @Nullable
    public abstract VirtualPlayer getPlayer(Player player);

    @Nullable
    public abstract VirtualPlayer getPlayer(UUID uuid);

    public abstract IndicatorTransmitter<Player> getIndicatorTransmitter();

    @SuppressWarnings("unchecked")
    public static <P, T extends AbstractServerApi<P>> T instance() {
        return (T) instance;
    }

}