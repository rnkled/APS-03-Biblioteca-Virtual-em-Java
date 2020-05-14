/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import biblioteca.AutorLivro;
import biblioteca.CategoriaLivro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author kener_000
 */
public class AutorDAO {
    
    private Connection conecta;
    
    public AutorDAO(){
        this.conecta = new DAO().conecta();
    }
    
        
    public void salvar (AutorLivro autor){
        
        String sql = "INSERT INTO tb_autores(nm_autor) VALUES(?)";
        
        try{
            
            PreparedStatement stn = conecta.prepareStatement(sql);
            
            stn.setString(1, autor.getNome());
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
        
    public List<AutorLivro> listarTodos(){
        String sql = "SELECT * FROM tb_autores ORDER BY id_autor";
        ResultSet rs;
        List<AutorLivro> autores = new ArrayList<AutorLivro>();

    try{
        PreparedStatement stmt = conecta.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()){

            AutorLivro autor = new AutorLivro();

            autor.setId_Autor(rs.getInt("id_autor"));
            autor.setNome(rs.getString("nm_autor"));
            autores.add(autor);
        }
        rs.close();
        stmt.close();
        return autores;


    } catch(SQLException e){

        JOptionPane.showMessageDialog(null, e);
        
        }
    return autores;
    }
    
    public void alterar (AutorLivro autor){
        String sql = "UPDATE tb_autores SET nm_autor=?"
                +"WHERE id_autor=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(2, autor.getId_Autor());
            stmt.setString(1, autor.getNome());
            
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    public void deletar(AutorLivro autor){
        
        String sql = "DELETE FROM tb_autores"
                +" WHERE id_autor=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, autor.getId_Autor());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    
    }
}
