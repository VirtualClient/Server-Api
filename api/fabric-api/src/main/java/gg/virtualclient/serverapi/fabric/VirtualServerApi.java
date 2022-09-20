package gg.virtualclient.serverapi.fabric;

import gg.virtualclient.serverapi.AbstractServerApi;
import gg.virtualclient.serverapi.VirtualPlayer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class VirtualServerApi extends AbstractServerApi<ServerPlayerEntity> {

    public static VirtualServerApi instance() {
        return AbstractServerApi.instance();
    }

}
