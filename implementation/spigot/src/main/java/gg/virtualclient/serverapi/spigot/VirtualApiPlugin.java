package gg.virtualclient.serverapi.spigot;

import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketUtils;
import gg.virtualclient.serverapi.spigot.indicators.icon.ItemIndicator;
import gg.virtualclient.serverapi.spigot.transmitter.PacketTransmitter;
import gg.virtualclient.serverapi.spigot.transmitter.ProtocolLibTransmitter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualApiPlugin extends JavaPlugin implements Listener, PluginMessageListener {

    /*
     * This holds the state of all virtual-client players.
     */
    private static final Map<Player, SpigotPlayer> virtualPlayers = new HashMap<>();

    private PacketTransmitter packetTransmitter;

    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
            this.packetTransmitter = new ProtocolLibTransmitter();
        } else {
            //TODO: Maybe other transmitters?
            throw new RuntimeException("ProtocolLib is required!");
        }

        new SpigotApiImpl(this);

        getServer().getMessenger().registerIncomingPluginChannel(this, "virtualclient:serverapi", this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void handle(PlayerQuitEvent event) {
        virtualPlayers.remove(event.getPlayer());
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte @NotNull [] message) {
        if (!channel.equals("virtualclient:serverapi")) {
            return;
        }
        ByteBuf buf = Unpooled.wrappedBuffer(message);
        ClientPacket packet = PacketUtils.readPacket(buf);

        handleClientPacket(player, packet);
    }

    @Override
    public void onDisable() {
        virtualPlayers.clear();
    }

    private void handleClientPacket(Player player, ClientPacket clientPacket) {
        if(clientPacket.getIdentifier().equals("init")) {
            virtualPlayers.put(player, new SpigotPlayer(player, clientPacket.getPayload().get("client-version").getAsString()));
        }
    }

    public PacketTransmitter getPacketTransmitter() {
        return packetTransmitter;
    }

    @Nullable
    public SpigotPlayer getPlayer(Player player) {
        return virtualPlayers.get(player);
    }

}
