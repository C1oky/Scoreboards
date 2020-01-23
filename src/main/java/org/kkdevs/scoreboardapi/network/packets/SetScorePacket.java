package org.kkdevs.scoreboardapi.network.packets;

import cn.nukkit.network.protocol.DataPacket;
import org.kkdevs.scoreboardapi.network.data.ScorePacketEntry;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class SetScorePacket extends DataPacket {

    public static int CHANGE = 0;
    public static int REMOVE = 1;

    public byte type;
    public List<ScorePacketEntry> entries;

    @Override
    public void decode() {
        List<ScorePacketEntry> list = new ArrayList<ScorePacketEntry>();
        this.type = (byte) this.getByte();
        for (int i = 0, i2 = (int) this.getUnsignedVarInt(); i < i2; ++i) {

            ScorePacketEntry entry = new ScorePacketEntry();
            entry.scoreboardId = Math.round(this.getVarLong());
            entry.objectiveName = this.getString();
            entry.score = this.getLInt();

            if (this.type != REMOVE) {
                entry.type = this.getByte();
                switch (entry.type) {
                    case ScorePacketEntry.TYPE_PLAYER:
                    case ScorePacketEntry.TYPE_ENTITY:
                        entry.entityUniqueId = Math.round(this.getEntityUniqueId());
                        break;
                    case ScorePacketEntry.TYPE_FAKE_PLAYER:
                        entry.customName = this.getString();
                        break;
                    default:
                        throw new InvalidParameterException("Unknown entry type");
                }
            }

            list.add(entry);
        }

        this.entries = list;
    }

    @Override
    public void encode() {
        this.reset();
        this.putByte(this.type);
        this.putUnsignedVarInt(this.entries.size());
        for (ScorePacketEntry entry : this.entries) {
            this.putVarLong(entry.scoreboardId);
            this.putString(entry.objectiveName);
            this.putLInt(entry.score);

            if (this.type != REMOVE) {
                this.putByte((byte) entry.type);
                switch (entry.type) {
                    case 1:
                    case 2:
                        this.putEntityUniqueId(entry.entityUniqueId);
                        break;
                    case 3:
                        this.putString(entry.customName);
                        break;
                    default:
                        throw new InvalidParameterException("Unknown entry type");
                }
            }
        }
    }

    @Override
    public byte pid() {
        return 0x6c;
    }
}