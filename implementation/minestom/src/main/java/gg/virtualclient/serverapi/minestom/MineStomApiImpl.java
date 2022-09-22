package gg.virtualclient.serverapi.minestom;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.discord.DiscordRPCTransmitter;
import gg.virtualclient.serverapi.discord.DiscordRPCTransmitterImpl;
import gg.virtualclient.serverapi.indicator.IndicatorTransmitterImpl;
import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.minestom.transmitter.MinePacketTransmitter;
import gg.virtualclient.serverapi.mods.ModBlockTransmitter;
import gg.virtualclient.serverapi.mods.ModBlockTransmitterImpl;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class MineStomApiImpl extends VirtualServerApi {

    private final VirtualApiExtension extension;
    private final IndicatorTransmitter<Player> indicatorTransmitter;
    private final ModBlockTransmitter<Player> modBlockTransmitter;
    private final DiscordRPCTransmitterImpl<Player> discordRPCTransmitter;

    public MineStomApiImpl(VirtualApiExtension extension) {
        this.extension = extension;
        instance = this;

        MinePacketTransmitter transmitter = new MinePacketTransmitter();
        this.indicatorTransmitter = new IndicatorTransmitterImpl<>(transmitter);
        this.modBlockTransmitter = new ModBlockTransmitterImpl<>(transmitter);
        this.discordRPCTransmitter = new DiscordRPCTransmitterImpl<>(transmitter);

    }

    @Override
    public @Nullable VirtualPlayer getPlayer(UUID uuid) {
        return getPlayer(MinecraftServer.getConnectionManager().getPlayer(uuid));
    }

    @Override
    public IndicatorTransmitter<Player> getIndicatorTransmitter() {
        return indicatorTransmitter;
    }

    @Override
    public DiscordRPCTransmitter<Player> getDiscordRPCTransmitter() {
        return discordRPCTransmitter;
    }

    @Override
    public ModBlockTransmitter<Player> getModBlockTransmitter() {
        return modBlockTransmitter;
    }

    @Override
    public @Nullable VirtualPlayer getPlayer(Player player) {
        return extension.getPlayer(player);
    }
}
