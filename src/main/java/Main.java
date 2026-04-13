import net.dv8tion.jda.api.JDA;
import java.sql.Connection;

import Database.Database;

public class Main {
    public static void main(String[] args){
        Database.connect();
    }
}
