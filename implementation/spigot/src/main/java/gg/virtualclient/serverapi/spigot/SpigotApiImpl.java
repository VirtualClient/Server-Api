package gg.virtualclient.serverapi.spigot;

import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.discord.DiscordRPCTransmitter;
import gg.virtualclient.serverapi.discord.DiscordRPCTransmitterImpl;
import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.indicator.IndicatorTransmitterImpl;
import gg.virtualclient.serverapi.mods.ModBlockTransmitter;
import gg.virtualclient.serverapi.mods.ModBlockTransmitterImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class SpigotApiImpl extends VirtualServerApi {

    private final VirtualApiPlugin plugin;
    private final IndicatorTransmitter<Player> indicatorTransmitter;
    private final ModBlockTransmitter<Player> modBlockTransmitter;
    private final DiscordRPCTransmitterImpl<Player> discordRPCTransmitter;

    public SpigotApiImpl(VirtualApiPlugin plugin) {
        this.plugin = plugin;
        VirtualServerApi.instance = this;

        this.indicatorTransmitter = new IndicatorTransmitterImpl<>(plugin.getPacketTransmitter());
        this.modBlockTransmitter = new ModBlockTransmitterImpl<>(plugin.getPacketTransmitter());
        this.discordRPCTransmitter = new DiscordRPCTransmitterImpl<>(plugin.getPacketTransmitter());
    }

    @Override
    public @Nullable VirtualPlayer getPlayer(UUID uuid) {
        return getPlayer(Bukkit.getPlayer(uuid));
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
        return plugin.getPlayer(player);
    }
}
