package HOSPITAL.SYSTEM;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    Connection connection;
    Statement statement;

    public conn(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOSPITAL_MANAGE_SYSTEM", "root" , "Akash@9494");
            statement = connection.createStatement();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
