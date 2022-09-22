package gg.virtualclient.serverapi.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import gg.virtualclient.serverapi.VirtualPlayer;
import gg.virtualclient.serverapi.discord.DiscordRPCTransmitter;
import gg.virtualclient.serverapi.discord.DiscordRPCTransmitterImpl;
import gg.virtualclient.serverapi.indicator.IndicatorTransmitterImpl;
import gg.virtualclient.serverapi.indicators.IndicatorTransmitter;
import gg.virtualclient.serverapi.mods.ModBlockTransmitter;
import gg.virtualclient.serverapi.mods.ModBlockTransmitterImpl;
import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketUtils;
import gg.virtualclient.serverapi.velocity.transmitter.VelocityTransmitter;
import io.netty.buffer.Unpooled;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Plugin(id = "virtualclient-serverapi",
        authors = "VirtualClient Team", url = "https://virtualclient.gg")
public class VirtualApiPlugin extends VirtualServerApi {

    private static final Map<Player, VelocityPlayer> virtualPlayers = new HashMap<>();

    private final ProxyServer proxyServer;
    private final DiscordRPCTransmitterImpl<Player> discordRPCTransmitter;
    private final ModBlockTransmitter<Player> modBlockTransmitter;
    private final IndicatorTransmitter<Player> indicatorTransmitter;

    @Inject
    public VirtualApiPlugin(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;

        VelocityTransmitter transmitter = new VelocityTransmitter(proxyServer);
        this.modBlockTransmitter = new ModBlockTransmitterImpl<>(transmitter);
        this.indicatorTransmitter = new IndicatorTransmitterImpl<>(transmitter);
        this.discordRPCTransmitter = new DiscordRPCTransmitterImpl<>(transmitter);
        proxyServer.getChannelRegistrar().register(VelocityTransmitter.CHANNEL);


        VirtualServerApi.instance = this;
    }

    @Subscribe
    public void handle(PluginMessageEvent event) {
        if(event.getSource() instanceof Player && event.getIdentifier().equals(VelocityTransmitter.CHANNEL)) {
            ClientPacket packet =  PacketUtils.readPacket(Unpooled.wrappedBuffer(event.getData()));

            handleClientPacket((Player) event.getSource(), packet);
        }
    }

    @Subscribe
    public void handle(DisconnectEvent event) {
        virtualPlayers.remove(event.getPlayer());
    }

    private void handleClientPacket(Player player, ClientPacket clientPacket) {
        if(clientPacket.getIdentifier().equals("init")) {
            virtualPlayers.put(player, new VelocityPlayer(player,
                    clientPacket.getPayload().get("client-version").getAsString()));
        }
    }


    @Override
    public @Nullable VirtualPlayer getPlayer(Player player) {
        return virtualPlayers.get(player);
    }

    @Override
    public @Nullable VirtualPlayer getPlayer(UUID uuid) {
        return virtualPlayers.get(proxyServer.getPlayer(uuid).orElse(null));
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
}
