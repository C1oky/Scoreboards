package org.kkdevs.scoreboardapi;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import org.kkdevs.scoreboardapi.events.PlayerJoin;
import org.kkdevs.scoreboardapi.events.PlayerQuit;

public class Loader extends PluginBase {

    @Override
    public void onEnable() {
        Server.getInstance().getPluginManager().registerEvents(new PlayerJoin(), this);
        Server.getInstance().getPluginManager().registerEvents(new PlayerQuit(), this);
        Server.getInstance().getLogger().info("§7Simple API for working with Scoreboard.");
    }
}
