package gg.virtualclient.serverapi.mods;

import java.util.List;

public interface ModBlockTransmitter<Player> {

    void clearBlockedMods(Player player);

    void broadcastClearBlockedMods();

    void blockMods(Player player, List<String> mods);

    void broadcastBlockMods(List<String> mods);

    void unblockMods(Player player, List<String> mods);

    void broadcastUnblockMods(List<String> mods);

}
