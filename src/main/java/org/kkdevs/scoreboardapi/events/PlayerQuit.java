package org.kkdevs.scoreboardapi.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import org.kkdevs.scoreboardapi.manager.BoardsManager;

public class PlayerQuit implements Listener {

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        //BoardsManager.removeScoreboard(player);
    }
}
