package org.kkdevs.scoreboardapi.manager;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.network.protocol.DataPacket;
import org.kkdevs.scoreboardapi.network.data.ScorePacketEntry;
import org.kkdevs.scoreboardapi.network.packets.RemoveObjectivePacket;
import org.kkdevs.scoreboardapi.network.packets.SetDisplayObjectivePacket;
import org.kkdevs.scoreboardapi.network.packets.SetScorePacket;

import java.util.*;

public class Scoreboard {

    public ArrayList<String> lines = new ArrayList<String>();
    public String objectiveName = "objective";
    public String displaySlot = "sidebar";
    public String criteria = "dummy";
    public String displayName = "displayName";
    public Player player;

    public Scoreboard(Player player) {
        this.player = player;
    }

    public Scoreboard send() {
        SetDisplayObjectivePacket setDisplayObjectivePacket = new SetDisplayObjectivePacket();
        setDisplayObjectivePacket.displaySlot = displaySlot;
        setDisplayObjectivePacket.objectiveName = objectiveName;
        setDisplayObjectivePacket.displayName = displayName;
        setDisplayObjectivePacket.criteriaName = criteria;
        setDisplayObjectivePacket.sortOrder = 0;

        player.dataPacket(setDisplayObjectivePacket);
        return this;
    }

    public Scoreboard hide() {
        RemoveObjectivePacket removeObjectivePacket = new RemoveObjectivePacket();
        removeObjectivePacket.objectiveName = objectiveName;

        player.dataPacket(removeObjectivePacket);
        return this;
    }

    public Scoreboard addLine(String line) {
        if (lines.size() < 15) {
            lines.add(line);
        } else {
            Server.getInstance().getLogger().alert("The maximum number of rows is 14");
        }

        return this;
    }

    /* public Scoreboard setLine(String line, int score) { // Bug
        if(lines.size() < 15) {
            lines.set(score, line);
        } else {
            Server.getInstance().getLogger().alert("The maximum number of rows is 14");
        }

        return this;
    } */

    public Scoreboard setLines(ArrayList<String> lines) {
        if (lines.size() < 15) {
            this.lines = lines;
        } else {
            Server.getInstance().getLogger().alert("The maximum number of rows is 14");
        }

        return this;
    }

    public Scoreboard removeLine(String line) {
        if (lines.contains(line)) {
            lines.remove(line);
        }

        return this;
    }

    public Scoreboard removeLine(int line) {
        if (!lines.isEmpty() && line < lines.size()) {
            lines.remove(line);
        }

        return this;
    }

    public Scoreboard removeAllLines() {
        lines.clear();

        return this;
    }

    public Scoreboard setDisplayName(String displayName) {
        this.displayName = displayName;

        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void update() {
        RemoveObjectivePacket removeObjectivePacket = new RemoveObjectivePacket();
        removeObjectivePacket.objectiveName = objectiveName;

        SetDisplayObjectivePacket setDisplayObjectivePacket = new SetDisplayObjectivePacket();
        setDisplayObjectivePacket.displaySlot = displaySlot;
        setDisplayObjectivePacket.objectiveName = objectiveName;
        setDisplayObjectivePacket.displayName = displayName;
        setDisplayObjectivePacket.criteriaName = criteria;
        setDisplayObjectivePacket.sortOrder = 0;

        List<ScorePacketEntry> list = new ArrayList<ScorePacketEntry>();
        for (String line : lines) {
            ScorePacketEntry packetEntry = new ScorePacketEntry();
            packetEntry.customName = line;
            packetEntry.objectiveName = objectiveName;
            packetEntry.type = ScorePacketEntry.TYPE_FAKE_PLAYER;
            packetEntry.score = lines.indexOf(line);
            packetEntry.scoreboardId = lines.indexOf(line);
            list.add(packetEntry);
        }

        SetScorePacket setScorePacket = new SetScorePacket();
        setScorePacket.type = (byte) SetScorePacket.CHANGE;
        setScorePacket.entries = list;

        Server.getInstance().batchPackets(new Player[]{
                player
        }, new DataPacket[]{
                removeObjectivePacket,
                setDisplayObjectivePacket,
                setScorePacket
        });
    }
}