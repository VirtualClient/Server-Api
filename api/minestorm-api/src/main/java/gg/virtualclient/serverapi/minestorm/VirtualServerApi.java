package gg.virtualclient.serverapi.minestorm;

import gg.virtualclient.serverapi.AbstractServerApi;
import gg.virtualclient.serverapi.VirtualPlayer;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.Nullable;

public abstract class VirtualServerApi extends AbstractServerApi {

    @Nullable
    abstract VirtualPlayer getPlayer(Player player);

    public static VirtualServerApi instance() {
        return AbstractServerApi.instance();
    }

}
