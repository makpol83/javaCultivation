package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Entities.Character.Cultivation.CultivationRange;
import Entities.Character.Cultivation.CultivationRealm;
import Entities.Character.Stats.StatData.StatQuality;
import Entities.Character.Stats.StatData.StatType;

public abstract class Database {
    public static Connection connection;

    public static void connect(){
        try{
            String url = "jdbc:sqlite:cultivation.db";
            Database.connection = DriverManager.getConnection(url);
            System.out.println("Database connected.");

            //We activate foreign_keys
            Database.connection.createStatement().execute("PRAGMA foreign_keys = ON;");

            createTables();
        } catch (SQLException e){
            System.err.println("Error connecting to SQLite: " + e.getMessage());
        }


    }

    private static void createTables() {

        //Character table
        String sqlCharacters =  Database.getCharacterTableCreation();

        //Stats groups table
        String sqlStats = Database.getStatsTableCreation();

        //Character stats table
        String sqlCharacterStats = Database.getCharacterStatsTableCreation();        

        //Stats instances table
        String sqlStatsInstances = Database.getStatsInstancesTableCreation();

        //Stats modifiers table
        String sqlCharacterStatModifiers = Database.getCharacterStatModifiersTableCreation();

        //Character level progression table
        String sqlLevelProgression = Database.getCharacterLevelProgressionTableCreation();

        //Character pyshical data table
        String sqlPyshicalData = Database.getCharacterPyshicalDataTableCreation();

        //Character cultivation data table
        String sqlCultivationData = Database.getCharacterCultivationDataTableCreation();

        // Lore data table
        String sqlLoreData = Database.getCharacterLoreDataTableCreation();

        //Disease Type table
        String sqlDiseaseInjuryTypes =  Database.getDiseaseInjuryTypesTableCreation();

        //Disease levels table
        String sqlDiseaseInjuryLevels =  Database.getDiseaseInjuryLevelsTableCreation();

        //Character disease Injury
        String sqlCharacterDiseaseInjury = Database.getCharacterDiseaseInjuryTableCreation();

        //Inventories on the db
        String sqlInventories = Database.getInventoryTableCreation();

        //Extensions of the inventories
        String sqlInventoriesExtension = Database.getInventoryExtensionTableCreation();

        //Relation between inventories and extensions
        String sqlInventoryAssociatedExtension = Database.getInventoryAssociatedExtensionTableCreation();

        //Object types
        String sqlObjectType = Database.getObjectTypeTableCreation();

        //Object instances
        String sqlObject = Database.getObjectTableCreation();

        //Inventory object relation
        String sqlInventoryObject = Database.getInventoryObjectTableCreation();

        //Object type related modifier
        String sqlObjectModifier = Database.getObjectModifierTableCreation();

        //Table creation
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlCharacters);
            System.out.println("Characters table created.");

            stmt.execute(sqlStats);
            System.out.println("Stats block table created.");

            stmt.execute(sqlCharacterStats);
            System.out.println("Characters stats table created.");

            stmt.execute(sqlStatsInstances);
            System.out.println("Stats instances table created.");

            stmt.execute(sqlCharacterStatModifiers);
            System.out.println("Characters stats modifiers table created.");

            stmt.execute(sqlLevelProgression);
            System.out.println("Character level table created.");

            stmt.execute(sqlPyshicalData);
            System.out.println("Character pyshical data table created.");

            stmt.execute(sqlCultivationData);
            System.out.println("Character cultivation data table created.");
            
            stmt.execute(sqlLoreData);
            System.out.println("Character lore data table created.");

            stmt.execute(sqlDiseaseInjuryTypes);
            System.out.println("Disease injury types table created.");

            stmt.execute(sqlDiseaseInjuryLevels);
            System.out.println("Disease injury levels table created.");

            stmt.execute(sqlCharacterDiseaseInjury);
            System.out.println("Character disease injury table created.");

            stmt.execute(sqlInventories);
            System.out.println("Inventories table created.");

            stmt.execute(sqlInventoriesExtension);
            System.out.println("Inventory extensions table created.");

            stmt.execute(sqlInventoryAssociatedExtension);
            System.out.println("Inventory associated extensions table created.");

            stmt.execute(sqlObjectType);
            System.out.println("Object Type table created.");

            stmt.execute(sqlObject);
            System.out.println("Object instances table created.");

            stmt.execute(sqlInventoryObject);
            System.out.println("Inventory object relations table created.");

            stmt.execute(sqlObjectModifier);
            System.out.println("Object type modifiers table created.");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    private static String getCharacterTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characters (" +
                "character_id INTEGER PRIMARY KEY," +
                "owner TEXT NOT NULL," +
                "name TEXT NOT NULL" +
                ");";
    }

    private static String getStatsTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS stats (" +
                "stats_id INTEGER PRIMARY KEY AUTOINCREMENT," + 
                "general_stat_modifier REAL DEFAULT 1" +
                ");";
    }

    private static String getCharacterStatsTableCreation(){
        /*
        //Auxiliar string with qualities
        StringBuilder qualityLevels = new StringBuilder();
        for(StatQuality qualityLevel : StatQuality.values()){
            qualityLevels.append("'"+qualityLevel.name()+"',");
        }

        //Stats table
        StringBuilder sbStats = new StringBuilder();
        sbStats.append("CREATE TABLE IF NOT EXISTS characterStats (")
                .append("character_id INTEGER PRIMARY KEY,");

        for(StatType type : StatType.values()){
            sbStats.append(type.name()).append(" INTEGER DEFAULT 1,")
                .append(type.name()).append("_BASE_MODIFIER REAL DEFAULT 1.0,")
                .append(type.name()).append("_QUALITY TEXT NOT NULL DEFAULT 'AVERAGE' CHECK (" + //
                                        type.name() + "_QUALITY" +" IN (" + 
                                        qualityLevels.toString() +"'NO_QUALITY')),");
        }

        sbStats.append("FOREIGN KEY (character_id) REFERENCES characters(character_id) ON DELETE CASCADE);");

        */
        return  "CREATE TABLE IF NOT EXISTS characterStats (" +
                "character_id INTEGER," + 
                "stats_id INTEGER," + 
                "PRIMARY KEY (character_id, stats_id)," +
                "FOREIGN KEY (character_id) REFERENCES characters(character_id) ON DELETE CASCADE," +
                "FOREIGN KEY (stats_id) REFERENCES stats(stats_id) ON DELETE CASCADE);";

    }

    private static String getStatsInstancesTableCreation(){
        //Auxiliar string with qualities
        StringBuilder qualityLevels = new StringBuilder();
        for(StatQuality qualityLevel : StatQuality.values()){
            qualityLevels.append("'"+qualityLevel.name()+"',");
        }

        //Auxiliar string with types
        StringBuilder types = new StringBuilder();
        for(StatType type : StatType.values()){
            qualityLevels.append("'"+type.name()+"',");
        }

        return  "CREATE TABLE IF NOT EXISTS statsInstances (" +
                "stat_id INTEGER PRIMARY KEY," +
                "stats_id INTEGER NOT NULL," + 
                "quality TEXT NOT NULL DEFAULT 'NO_QUALITY' CHECK (" +
                "quality IN (" + 
                qualityLevels.toString() +"'NO_QUALITY'))," +
                "type TEXT NOT NULL DEFAULT 'NO_TYPE' CHECK (" +
                "type IN (" + 
                types.toString() +"'NO_TYPE'))," +
                "FOREIGN KEY (stats_id) REFERENCES stats(stat_id) ON DELETE CASCADE" +
                ");";
    }
    
    private static String getCharacterStatModifiersTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterStatsModifiers (" +
                "modifier_id INTEGER PRIMARY KEY AUTOINCREMENT," + 
                "modified_stat_name TEXT NOT NULL DEFAULT 'Not assigned'," +
                "modifier_value REAL DEFAULT 1 " + 
                ");";
    }

    private static String getCharacterLevelProgressionTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterLevel (" +
                "character_id INTEGER PRIMARY KEY," + 
                "level INTEGER DEFAULT 1," +
                "xp_needed_to_lvlup REAL DEFAULT 1," + 
                "xp_actual REAL DEFAULT 1," +
                "FOREIGN KEY (character_id) REFERENCES characters(character_id) ON DELETE CASCADE);";
    }

    private static String getCharacterPyshicalDataTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterPyshicalData (" +
                "character_id INTEGER PRIMARY KEY," + 
                "lifespan REAL DEFAULT 80," +
                "age INTEGER DEFAULT 20," + 
                "gender TEXT DEFAULT 'No gender assigned'," +
                "FOREIGN KEY (character_id) REFERENCES characters(character_id) ON DELETE CASCADE);";
    }

    private static String getCharacterCultivationDataTableCreation(){
        StringBuilder sqlCultivationData = new StringBuilder();
        sqlCultivationData.append("CREATE TABLE IF NOT EXISTS characterCultivationData (")
                          .append("character_id INTEGER PRIMARY KEY,")
                          .append("cultivation_realm TEXT NOT NULL CHECK (")
                          .append("cultivation_realm IN (");
        for(CultivationRealm realm : CultivationRealm.values()){
            sqlCultivationData.append("'" + realm.name() + "',");
        }
        sqlCultivationData.append("'NO_REALM')),");

        sqlCultivationData.append("cultivation_range TEXT NOT NULL CHECK (")
                          .append("cultivation_range IN (");
        for(CultivationRange range : CultivationRange.values()){
            sqlCultivationData.append("'" + range.name() + "',");
        }
        sqlCultivationData.append("'NO_RANGE')),")
                          .append("FOREIGN KEY (character_id) REFERENCES characters(character_id) ON DELETE CASCADE);");

        return sqlCultivationData.toString();
    }

    private static String getCharacterLoreDataTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterLoreData (" +
                "character_id INTEGER  PRIMARY KEY," + 
                "pyshical_appearance TEXT DEFAULT 'No appearance defined'," +
                "past TEXT DEFAULT 'No past defined'," + 
                "FOREIGN KEY (character_id) REFERENCES characters(character_id) ON DELETE CASCADE);";
    }

    private static String getDiseaseInjuryTypesTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS diseaseInjuryTypes (" +
                "disease_injury_id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL DEFAULT 'No name'," +
                "description TEXT NOT NULL DEFAULT 'No description'," +
                "cause TEXT NOT NULL DEFAULT 'No cause'," +
                "cure TEXT NOT NULL DEFAULT 'No cure'" +
                ");";
    }

    private static String getDiseaseInjuryLevelsTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS diseaseInjuryLevels (" +
                "disease_injury_id INTEGER," +
                "severity_level INTEGER," +
                "effect TEXT NOT NULL DEFAULT 'No effect'," +
                "PRIMARY KEY (disease_injury_id, severity_level)," +
                "FOREIGN KEY (disease_injury_id) REFERENCES diseaseInjuryTypes(disease_injury_id) ON DELETE CASCADE);";
    }

    private static String getCharacterDiseaseInjuryTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterDiseaseInjuryData (" +
                "character_id INTEGER," + 
                "disease_injury_id INTEGER," +
                "severity_level INTEGER NOT NULL DEFAULT 1," +
                "PRIMARY KEY (character_id, disease_injury_id)," +
                "FOREIGN KEY (character_id) REFERENCES characters(character_id) ON DELETE CASCADE," +
                "FOREIGN KEY (disease_injury_id) REFERENCES diseaseInjuryTypes(disease_injury_id) ON DELETE CASCADE" +
                ");";
    }

    private static String getInventoryTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS inventories (" +
                "inventory_id INTEGER PRIMARY KEY," + 
                "base_capacity INTEGER DEFAULT 1" +
                ");";
    }

    private static String getInventoryExtensionTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS inventoryExtensions (" +
                "extension_id INTEGER PRIMARY KEY," + 
                "added_capacity INTEGER DEFAULT 1" +
                ");";
    }

    private static String getInventoryAssociatedExtensionTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS inventoriesExtensions (" +
                "inventory_id INTEGER," + 
                "extension_id INTEGER," +
                "PRIMARY KEY (inventory_id, extension_id)," +
                "FOREIGN KEY (inventory_id) REFERENCES inventories(inventory_id) ON DELETE CASCADE," +
                "FOREIGN KEY (extension_id) REFERENCES inventoryExtensions(extension_id) ON DELETE CASCADE" +
                ");";
    }

    private static String getObjectTypeTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS objectType (" +
                "object_type_id INTEGER PRIMARY KEY," + 
                "name TEXT NOT NULL DEFAULT 'No name assigned'," +
                "description TEXT NOT NULL DEFAULT 'No description assigned'," +
                "on_rol_effect TEXT NOT NULL DEFAULT ' No effect assigned'," +
                "is_equippable INTEGER NOT NULL DEFAULT 0," +
                "is_consumable INTEGER NOT NULL DEFAULT 0" +
                ");";
    }

    private static String getObjectTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS objects (" +
                "object_id INTEGER," + 
                "object_type_id INTEGER," + 
                "notes_added TEXT NOT NULL DEFAULT 'No notes assigned'," +
                "PRIMARY KEY (object_id, object_type_id)," +
                "FOREIGN KEY (object_type_id) REFERENCES objectType(object_type_id) ON DELETE CASCADE" +
                ");";
    }

    private static String getInventoryObjectTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS objects (" +
                "object_id INTEGER," + 
                "inventory_id INTEGER," +
                "PRIMARY KEY (object_id, inventory_id)," +
                "FOREIGN KEY (object_id) REFERENCES objects(object_id) ON DELETE CASCADE," +
                "FOREIGN KEY (inventory_id) REFERENCES inventories(inventory_id) ON DELETE CASCADE" +
                ");";
    }

    private static String getObjectModifierTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS objectModifiers (" +
                "object_type_id INTEGER NOT NULL," + 
                "modifier_id INTEGER NOT NULL," + 
                "PRIMARY KEY (object_type_id, modifier_id)," +
                "FOREIGN KEY (object_type_id) REFERENCES objectType(object_type_id) ON DELETE CASCADE," +
                "FOREIGN KEY (modifier_id) REFERENCES statModifiers(modifier_id) ON DELETE CASCADE" +
                ");";
    }
}
