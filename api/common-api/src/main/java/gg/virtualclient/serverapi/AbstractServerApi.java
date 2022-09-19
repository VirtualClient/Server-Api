package gg.virtualclient.serverapi;

import gg.virtualclient.serverapi.indicators.InfoIndicator;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractServerApi {

    protected static AbstractServerApi instance;

    @Nullable
    public abstract VirtualPlayer getPlayer(UUID uuid);

    @SuppressWarnings("unchecked")
    public static <T extends AbstractServerApi> T instance() {
        return (T) instance;
    }

}
