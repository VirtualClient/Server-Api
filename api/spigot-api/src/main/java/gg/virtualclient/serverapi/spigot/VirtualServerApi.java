package gg.virtualclient.serverapi.spigot;

import gg.virtualclient.serverapi.AbstractServerApi;
import gg.virtualclient.serverapi.VirtualPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class VirtualServerApi extends AbstractServerApi<Player> {

    public static VirtualServerApi instance() {
        return AbstractServerApi.instance();
    }

}
