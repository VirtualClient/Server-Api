package gg.virtualclient.serverapi.minestom;

import gg.virtualclient.serverapi.AbstractServerApi;
import gg.virtualclient.serverapi.VirtualPlayer;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class VirtualServerApi extends AbstractServerApi<Player> {

    public static VirtualServerApi instance() {
        return AbstractServerApi.instance();
    }

}
