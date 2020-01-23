package org.kkdevs.scoreboardapi.manager;

import cn.nukkit.Player;

import java.util.HashMap;

public class BoardsManager {

    public static HashMap<String, Scoreboard> scoreboards = new HashMap<String, Scoreboard>();

    public static Scoreboard createScoreboard(Player player) {
        Scoreboard scoreboard;
        if(isExist(player)) {
            scoreboard = getScoreboard(player);
        } else {
            scoreboard = new Scoreboard(player);
        }

        scoreboards.put(player.getName(), scoreboard);
        return getScoreboard(player);
    }

    public static void removeScoreboard(Player player) {
        if(isExist(player)) {
            scoreboards.remove(player.getName());
        }
    }

    public static Scoreboard getScoreboard(Player player) {
        if(isExist(player)) {
            return scoreboards.get(player.getName());
        }

        return null;
    }

    public static boolean isExist(Player player) {
        if(scoreboards.containsKey(player.getName())) {
            return true;
        }

        return false;
    }
}
