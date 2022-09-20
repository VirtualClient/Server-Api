package gg.virtualclient.serverapi.fabric;

import org.jetbrains.annotations.Nullable;

public interface VirtualPlayerHolder {

    @Nullable
    FabricVirtualPlayer getVirtualServerApiPlayer();

    void setVirtualServerApiPlayer(FabricVirtualPlayer player);
}
