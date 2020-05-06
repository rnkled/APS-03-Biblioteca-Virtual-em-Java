package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

#TESTE TESTE
    
    public Connection conecta(){
        try{
            
            String url = "jdbc:postgresql://localhost:5432/APS03";
            String usuario = "postgres";
            String senha = "221297";
            
            return DriverManager.getConnection(url, usuario, senha);
            
        } catch(SQLException e){
           
            throw new RuntimeException(e);
            
        }
    }
    
}
