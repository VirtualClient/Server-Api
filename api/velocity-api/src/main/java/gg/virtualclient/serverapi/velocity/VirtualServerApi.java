package gg.virtualclient.serverapi.velocity;

import com.velocitypowered.api.proxy.Player;
import gg.virtualclient.serverapi.AbstractServerApi;

public abstract class VirtualServerApi extends AbstractServerApi<Player> {

    public static VirtualServerApi instance() {
        return AbstractServerApi.instance();
    }

}
