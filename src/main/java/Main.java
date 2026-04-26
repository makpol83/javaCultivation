import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import Database.DatabaseManager;
import Entities.Character.Characterr;
import Entities.Character.Data.LoreData;
import Entities.Item.Components.EquippableZone;
import PowerSystem.PowerStepType;
import PowerSystem.PowerSystem;

public class Main {
    public static void main(String[] args){
        DatabaseManager.init();

        ConnectionSource connectionSource = DatabaseManager.getConnectionSource();

        PowerSystem martialSystem = new PowerSystem("Sistema marcial", "Descripcion sistema marcial");

        PowerSystem qiSystem = new PowerSystem("Sistema Qi", "Descripcion sistema qi");

        PowerStepType powerStepType1 = new PowerStepType("Nivel 1 marcial",
         "Descripcion n1", 0, 20, null, null, null, martialSystem);


        PowerStepType powerStepType2 = new PowerStepType("Nivel 1 qi",
         "Descripcion n1", 0, 20, null, null, null, qiSystem);

        martialSystem.add(powerStepType1);
        qiSystem.add(powerStepType2);


        Characterr character = new Characterr(
            "Héroe",
            "Pasado",
            "Descripcion física",
            60,
            18,
            "Masculino",
            martialSystem,
            qiSystem,
            powerStepType1,
            powerStepType1,
            powerStepType2,
            powerStepType2,
            List.of(EquippableZone.ARM_LEFT_FINGER_1)
        );

        try{
            Dao<Characterr, Long> characterDao = DaoManager.createDao(connectionSource, Characterr.class);
            Dao<PowerSystem, Long> powerSystemDao = DaoManager.createDao(connectionSource, PowerSystem.class);

            powerSystemDao.create(martialSystem);
            powerSystemDao.create(qiSystem);

            // TODO --> Add saveOnDb interfaces and loadfromdb
        
            Dao<LoreData, Long> loreDataDao = DaoManager.createDao(connectionSource, LoreData.class);

            loreDataDao.create(character.getLoreData());

            characterDao.create(character);

            List<Characterr> todos = characterDao.queryForAll();
            List<PowerSystem> sistemas = powerSystemDao.queryForAll();

            System.out.println(todos);
            System.out.println(sistemas);
        } catch(SQLException e){
            System.out.println(e);
        }

    }
}
