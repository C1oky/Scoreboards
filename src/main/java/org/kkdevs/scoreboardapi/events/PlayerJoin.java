package org.kkdevs.scoreboardapi.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import org.kkdevs.scoreboardapi.manager.BoardsManager;
import org.kkdevs.scoreboardapi.manager.Scoreboard;

import java.util.ArrayList;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //For example:
        /* ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("one");
        Scoreboard scoreboard = BoardsManager.createScoreboard(player);
        scoreboard
                .setDisplayName("test")
                .setLines(stringList)
                .addLine("two")
                .addLine("three")
                .update();
        scoreboard
                .setDisplayName("asfafsssffd")
                .removeAllLines()
                .removeLine("one")
                .removeLine(0)
                .addLine("test")
                .update(); */
    }
}