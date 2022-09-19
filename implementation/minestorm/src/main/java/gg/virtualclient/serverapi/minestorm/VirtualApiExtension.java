package gg.virtualclient.serverapi.minestorm;

import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.minestorm.indicators.icon.ItemIndicator;
import gg.virtualclient.serverapi.packet.ClientPacket;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerPluginMessageEvent;
import net.minestom.server.extensions.Extension;
import net.minestom.server.item.Material;
import net.minestom.server.utils.binary.BinaryReader;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualApiExtension extends Extension {

    /*
     * This holds the state of all virtual-client players.
     */
    private static final Map<Player, MineVirtualPlayer> virtualPlayers = new HashMap<>();


    @Override
    @ApiStatus.Internal
    public void initialize() {
        new MineStormApiImpl(this);

        getEventNode().addListener(PlayerPluginMessageEvent.class, event -> {
            if(event.getIdentifier().equals("virtualclient:serverapi")) {

                try(BinaryReader reader = new BinaryReader(event.getMessage())) {
                    ClientPacket clientPacket = ClientPacket.fromString(reader.readSizedString());

                    if(clientPacket.getIdentifier().equals("init")) {
                        virtualPlayers.put(event.getPlayer(), new MineVirtualPlayer(event.getPlayer(),
                                clientPacket.getPayload().get("client-version").getAsString()));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        getEventNode().addListener(PlayerDisconnectEvent.class, event -> virtualPlayers.remove(event.getPlayer()));
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
