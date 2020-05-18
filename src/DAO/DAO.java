package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    
    public Connection conecta(){
        try{
            
            String url = "jdbc:postgresql://localhost:5432/APS03";
            String usuario = "postgres";
<<<<<<< HEAD
            String senha = "root123";
=======
            String senha = "221297";
>>>>>>> d2aad36fda5c92544f14169a5e48680f77287a68
            
            return DriverManager.getConnection(url, usuario, senha);
            
        } catch(SQLException e){
           
            throw new RuntimeException(e);
            
        }
    }
    
}
