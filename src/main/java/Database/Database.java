package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.crypto.Data;

import Character.Cultivation.CultivationRange;
import Character.Cultivation.CultivationRealm;
import Character.Stats.StatQuality;
import Character.Stats.StatType;

public class Database {
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

        //Character stats table
        String sqlCharacterStats = Database.getCharacterStatsTableCreation();        

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


        //Table creation
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlCharacters);
            System.out.println("Characters table created.");

            stmt.execute(sqlCharacterStats.toString());
            System.out.println("Characters stats table created.");

            stmt.execute(sqlCharacterStatModifiers);
            System.out.println("Characters stats modifiers table created.");

            stmt.execute(sqlLevelProgression);
            System.out.println("Character level table created.");

            stmt.execute(sqlPyshicalData);
            System.out.println("Character pyshical data table created.");

            stmt.execute(sqlCultivationData.toString());
            System.out.println("Character cultivation data table created.");
            
            stmt.execute(sqlLoreData);
            System.out.println("Character lore data table created.");

            stmt.execute(sqlDiseaseInjuryTypes);
            System.out.println("Disease injury types table created.");

            stmt.execute(sqlDiseaseInjuryLevels);
            System.out.println("Disease injury levels table created.");

            stmt.execute(sqlCharacterDiseaseInjury);
            System.out.println("Character disease injury table created.");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    private static String getCharacterTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characters (" +
                "id INTEGER PRIMARY KEY," +
                "owner TEXT NOT NULL," +
                "name TEXT NOT NULL" +
                ");";
    }

    private static String getCharacterStatsTableCreation(){
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

        sbStats.append("FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE);");

        return sbStats.toString();
    }
    
    private static String getCharacterStatModifiersTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterStatsModifiers (" +
                "modifier_id INTEGER PRIMARY KEY AUTOINCREMENT," + 
                "character_id INTEGER NOT NULL," + 
                "modifier_value REAL DEFAULT 1, " + 
                "FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE);";
    }

    private static String getCharacterLevelProgressionTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterLevel (" +
                "character_id INTEGER PRIMARY KEY," + 
                "level INTEGER DEFAULT 1," +
                "xp_needed_to_lvlup REAL DEFAULT 1," + 
                "xp_actual REAL DEFAULT 1," +
                "FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE);";
    }

    private static String getCharacterPyshicalDataTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterPyshicalData (" +
                "character_id INTEGER PRIMARY KEY," + 
                "lifespan REAL DEFAULT 80," +
                "age INTEGER DEFAULT 20," + 
                "gender TEXT DEFAULT 'No gender assigned'," +
                "FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE);";
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
                          .append("FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE);");

        return sqlCultivationData.toString();
    }

    private static String getCharacterLoreDataTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterLoreData (" +
                "character_id INTEGER  PRIMARY KEY," + 
                "pyshical_appearance TEXT DEFAULT 'No appearance defined'," +
                "past TEXT DEFAULT 'No past defined'," + 
                "FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE);";
    }

    private static String getDiseaseInjuryTypesTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS diseaseInjuryTypes (" +
                "id INTEGER PRIMARY KEY," +
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
                "FOREIGN KEY (disease_injury_id) REFERENCES diseaseInjuryTypes(id) ON DELETE CASCADE);";
    }

    private static String getCharacterDiseaseInjuryTableCreation(){
        return  "CREATE TABLE IF NOT EXISTS characterDiseaseInjuryData (" +
                "character_id INTEGER," + 
                "disease_injury_id INTEGER," +
                "severity_level INTEGER NOT NULL DEFAULT 1," +
                "PRIMARY KEY (character_id, disease_injury_id)," +
                "FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE," +
                "FOREIGN KEY (disease_injury_id) REFERENCES diseaseInjuryTypes(id) ON DELETE CASCADE" +
                ");";
    }
}
