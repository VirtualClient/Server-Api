package gg.virtualclient.serverapi;

import gg.virtualclient.serverapi.indicators.InfoIndicator;

import java.util.List;

/*
 * VirtualPlayers are player instances that are using the VirtualClient.
 * This class holds information like client version and also acts as a wrapper class for the transmitter functions.
 */
public interface VirtualPlayer {

    /*
     * The version of the client. Keep in mind that the client version is not directly bound to any format.
     *  We will try to stick to the format of primary-release.secondary-release.patch-release + SUFFIX(optional) though.
     */
    String getClientVersion();

    /*
     * Override the indicators of the player with the given ones. This will remove all previous indicators.
     */
    void setIndicators(List<InfoIndicator<?>> indicators);

}
