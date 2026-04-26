package Database;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import Entities.Character.Afflictions.Affliction;
import Entities.Character.Afflictions.AfflictionLevel;
import Entities.Character.Afflictions.AfflictionType;
import Entities.Character.Data.LoreData;
import Entities.Character.Data.PhysicalData;
import Entities.Character.Data.PowerStepData;
import Entities.Character.Equipment.Equipment;
import Entities.Character.Equipment.Slot;
import Entities.Character.Stats.Stat;
import Entities.Character.Stats.StatData.StatData;
import Entities.Character.Stats.StatData.StatHistory;
import Entities.Character.Stats.StatData.StatModifier;
import Entities.Character.Stats.StatData.StatPowerStepFinalInstance;
import Entities.Item.Inventory;
import Entities.Item.Item;
import Entities.Item.ItemInstance;
import Entities.Character.Characterr;
import PowerSystem.PowerStepType;
import PowerSystem.PowerSystem;
import PowerSystem.PowerStepData.AbstractRequirement;
import PowerSystem.PowerStepData.AdvanceType;
import PowerSystem.PowerStepData.DataRequirement;
import PowerSystem.PowerStepData.SpecialCharacterModifier;
import PowerSystem.PowerStepData.CharacterModifiers.StatCharModifier;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:cultivation.db";
    private static ConnectionSource connectionSource;

    public static void init() {
        if (connectionSource == null) {
            try{
                // We add source connection
                connectionSource = new JdbcConnectionSource(DATABASE_URL);
                
                // We create tables
                setupTables();
            } catch (SQLException e){
                System.out.println(e);
            }
        }
    }

    private static void setupTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, Affliction.class);
        TableUtils.createTableIfNotExists(connectionSource, AfflictionLevel.class);
        TableUtils.createTableIfNotExists(connectionSource, AfflictionType.class);
        TableUtils.createTableIfNotExists(connectionSource, LoreData.class);
        TableUtils.createTableIfNotExists(connectionSource, PhysicalData.class);
        TableUtils.createTableIfNotExists(connectionSource, PowerStepData.class);
        TableUtils.createTableIfNotExists(connectionSource, Equipment.class);
        TableUtils.createTableIfNotExists(connectionSource, Slot.class);
        TableUtils.createTableIfNotExists(connectionSource, Stat.class);
        TableUtils.createTableIfNotExists(connectionSource, StatData.class);
        TableUtils.createTableIfNotExists(connectionSource, StatHistory.class);
        TableUtils.createTableIfNotExists(connectionSource, StatModifier.class);
        TableUtils.createTableIfNotExists(connectionSource, StatPowerStepFinalInstance.class);
        TableUtils.createTableIfNotExists(connectionSource, Characterr.class);
        TableUtils.createTableIfNotExists(connectionSource, Inventory.class);
        TableUtils.createTableIfNotExists(connectionSource, Item.class);
        TableUtils.createTableIfNotExists(connectionSource, ItemInstance.class);
        TableUtils.createTableIfNotExists(connectionSource, PowerSystem.class);
        TableUtils.createTableIfNotExists(connectionSource, PowerStepType.class);
        TableUtils.createTableIfNotExists(connectionSource, AbstractRequirement.class);
        TableUtils.createTableIfNotExists(connectionSource, AdvanceType.class);
        TableUtils.createTableIfNotExists(connectionSource, DataRequirement.class);
        TableUtils.createTableIfNotExists(connectionSource, SpecialCharacterModifier.class);
        TableUtils.createTableIfNotExists(connectionSource, StatCharModifier.class);
    }

    public static ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}