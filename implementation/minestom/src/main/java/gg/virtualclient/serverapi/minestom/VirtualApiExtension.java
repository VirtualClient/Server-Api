package gg.virtualclient.serverapi.minestom;

import gg.virtualclient.serverapi.packet.ClientPacket;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerPluginMessageEvent;
import net.minestom.server.extensions.Extension;
import net.minestom.server.utils.binary.BinaryReader;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VirtualApiExtension extends Extension {

    /*
     * This holds the state of all virtual-client players.
     */
    private static final Map<Player, MineVirtualPlayer> virtualPlayers = new HashMap<>();


    @Override
    @ApiStatus.Internal
    public void initialize() {
        new MineStomApiImpl(this);

        getEventNode().addListener(PlayerPluginMessageEvent.class, event -> {
            if(event.getIdentifier().equals("virtualclient:serverapi")) {

                try(BinaryReader reader = new BinaryReader(event.getMessage())) {
                    ClientPacket clientPacket = ClientPacket.fromString(reader.readSizedString());

                    handleClientPacket(event.getPlayer(), clientPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        getEventNode().addListener(PlayerDisconnectEvent.class, event -> virtualPlayers.remove(event.getPlayer()));
    }

    private void handleClientPacket(Player player, ClientPacket clientPacket) {
        if(clientPacket.getIdentifier().equals("init")) {
            virtualPlayers.put(player, new MineVirtualPlayer(player, clientPacket.getPayload().get("client-version").getAsString()));
        }
    }

    @Nullable
    public MineVirtualPlayer getPlayer(Player player) {
        return virtualPlayers.get(player);
    }

    @Override
    @ApiStatus.Internal
    public void terminate() {
        virtualPlayers.clear();
    }

}
