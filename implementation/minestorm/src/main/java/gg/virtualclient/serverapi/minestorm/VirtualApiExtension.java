package gg.virtualclient.serverapi.minestorm;

import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketUtils;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerPluginMessageEvent;
import net.minestom.server.extensions.Extension;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class VirtualApiExtension extends Extension {

    private static VirtualApiExtension instance;

    /*
     * This holds the state of all virtual-client players.
     */
    private static final Map<Player, MineVirtualPlayer> virtualPlayers = new HashMap<>();

    @Override
    @ApiStatus.Internal
    public void initialize() {
        VirtualApiExtension.instance = this;
        getEventNode().addListener(PlayerPluginMessageEvent.class, event -> {
            if(event.getIdentifier().equals("virtualclient:serverapi")) {
                ClientPacket clientPacket = PacketUtils.readPacket(ByteBuffer.wrap(event.getMessage()));

                if(clientPacket.getIdentifier().equals("init")) {
                    virtualPlayers.put(event.getPlayer(), new MineVirtualPlayer(event.getPlayer(),
                            clientPacket.getPayload().get("client-version").getAsString()));
                }
            }
        });
        getEventNode().addListener(PlayerDisconnectEvent.class, event -> virtualPlayers.remove(event.getPlayer()));
    }

    @Nullable
    public MineVirtualPlayer getPlayer(Player player) {
        return virtualPlayers.get(player);
    }

    public static VirtualApiExtension instance() {
        return instance;
    }

    @Override
    @ApiStatus.Internal
    public void terminate() {
        virtualPlayers.clear();
    }

}
