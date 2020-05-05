/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import view.*;
import DAO.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Biblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Login login = new Login();
        login.show();
        login.setLocationRelativeTo(null);
        
        Connection conecta = new DAO().conecta();
        System.out.println("Teste com sucesso");
        conecta.close();
        
        
    }
    
}
