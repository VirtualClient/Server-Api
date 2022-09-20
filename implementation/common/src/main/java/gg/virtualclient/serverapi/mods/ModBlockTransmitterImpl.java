package gg.virtualclient.serverapi.mods;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.virtualclient.serverapi.packet.ClientPacket;
import gg.virtualclient.serverapi.packet.PacketTransmitter;

import java.util.List;

public class ModBlockTransmitterImpl<Player> implements ModBlockTransmitter<Player> {
    private static final String CHANNEL = "disable-mods";

    private PacketTransmitter<Player> transmitter;

    public ModBlockTransmitterImpl(PacketTransmitter<Player> transmitter) {
        this.transmitter = transmitter;
    }

    @Override
    public void clearBlockedMods(Player player) {
        transmitter.sendPacket(player, clearPacket());
    }

    @Override
    public void broadcastClearBlockedMods() {
        transmitter.broadcastPacket(clearPacket());
    }

    private ClientPacket clearPacket() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("clear", true);
        return new ClientPacket(CHANNEL, jsonObject);
    }

    private ClientPacket changePacket(List<String> mods, ChangeType type) {
        JsonObject jsonObject = new JsonObject();
        JsonArray modArr = new JsonArray();
        for (String mod : mods) {
            modArr.add(mod);
        }
        jsonObject.add(type.key, modArr);
        return new ClientPacket(CHANNEL, jsonObject);
    }

    @Override
    public void blockMods(Player player, List<String> mods) {
        transmitter.sendPacket(player, changePacket(mods, ChangeType.BLOCK));
    }

    @Override
    public void broadcastBlockMods(List<String> mods) {
        transmitter.broadcastPacket(changePacket(mods, ChangeType.BLOCK));
    }

    @Override
    public void unblockMods(Player player, List<String> mods) {
        transmitter.sendPacket(player, changePacket(mods, ChangeType.UNBLOCK));
    }

    @Override
    public void broadcastUnblockMods(List<String> mods) {
        transmitter.broadcastPacket(changePacket(mods, ChangeType.UNBLOCK));
    }

    private enum ChangeType {
        BLOCK("blockMods"), UNBLOCK("unblockMods");
        String key;

        ChangeType(String key) {
            this.key = key;
        }
    }

}
