package gg.virtualclient.serverapi.spigot;

import gg.virtualclient.serverapi.AbstractServerApi;
import gg.virtualclient.serverapi.VirtualPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public abstract class VirtualServerApi extends AbstractServerApi<Player> {

    @Nullable
    abstract VirtualPlayer getPlayer(Player player);

    public static VirtualServerApi instance() {
        return AbstractServerApi.instance();
    }

}
