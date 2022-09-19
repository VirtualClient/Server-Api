package gg.virtualclient.serverapi.indicators;

import java.util.List;

/*
 * Represents the indicator packet.
 *
 * Indicators are small boxes displayed in the upper right corner displaying images, items and colors next to a piece
 * of text configurable by the server. This can be used e.g. for displaying money, remaking time, your team and many other things.
 */
public interface IndicatorTransmitter<Player> {

    /*
     * Override the indicators of the player with the given ones. This will remove all previous indicators.
     */
    void setIndicators(Player player, List<InfoIndicator<?>> indicators);

    /*
     * Broadcasts the indicators to all players.
     */
    void broadcastIndicators(List<InfoIndicator<?>> indicators);

}
