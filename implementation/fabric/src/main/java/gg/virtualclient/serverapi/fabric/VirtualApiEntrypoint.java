package gg.virtualclient.serverapi.fabric;

import gg.virtualclient.serverapi.fabric.icon.ItemIndicator;
import gg.virtualclient.serverapi.fabric.indicators.MinecraftInfoIndicator;
import gg.virtualclient.serverapi.indicators.InfoIndicator;
import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VirtualApiEntrypoint implements ModInitializer {

    private List<MinecraftServer> runningServers = new ArrayList<>();

    private void handleClientPacket(ServerPlayerEntity player, ClientPacket clientPacket) {
        if(clientPacket.getIdentifier().equals("init")) {
            ((VirtualPlayerHolder) player).setVirtualServerApiPlayer(new FabricVirtualPlayer(player,
                    clientPacket.getPayload().get("client-version").getAsString()));
        }
    }

    @Override
    public void onInitialize() {
        new FabricApiImpl(this);

        ServerLifecycleEvents.SERVER_STARTED.register(server -> runningServers.add(server));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> runningServers.remove(server));

        ServerPlayNetworking.registerGlobalReceiver(new Identifier("virtualclient", "serverapi"),
                (server, player, handler, buf, responseSender) -> handleClientPacket(player, PacketUtils.readPacket(buf)));
    }

    public List<ServerPlayerEntity> getAllPlayers() {
        if(runningServers.size() == 1)
            return runningServers.get(0).getPlayerManager().getPlayerList();
        List<ServerPlayerEntity> players = new ArrayList<>();
        for (MinecraftServer runningServer : runningServers) {
            players.addAll(runningServer.getPlayerManager().getPlayerList());
        }
        return players;
    }

    public ServerPlayerEntity getPlayer(UUID uuid) {
        for (MinecraftServer runningServer : runningServers) {
            ServerPlayerEntity entity = runningServer.getPlayerManager().getPlayer(uuid);
            if(entity != null)
                return entity;
        }
        return null;
    }

    public List<MinecraftServer> getRunningServers() {
        return runningServers;
    }
}
