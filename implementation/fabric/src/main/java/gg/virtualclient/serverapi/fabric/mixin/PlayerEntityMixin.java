package gg.virtualclient.serverapi.fabric.mixin;

import gg.virtualclient.serverapi.fabric.FabricVirtualPlayer;
import gg.virtualclient.serverapi.fabric.VirtualPlayerHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ServerPlayerEntity.class)
public class PlayerEntityMixin implements VirtualPlayerHolder {

    @Unique
    private FabricVirtualPlayer virtualPlayer;

    @Override
    @Nullable
    public FabricVirtualPlayer getVirtualServerApiPlayer() {
        return virtualPlayer;
    }

    @Override
    public void setVirtualServerApiPlayer(FabricVirtualPlayer player) {
        this.virtualPlayer = player;
    }

}
