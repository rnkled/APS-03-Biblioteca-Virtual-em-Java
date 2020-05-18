/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class CategoriaDAO {
    
private Connection conecta;
    
    public CategoriaDAO(){
        this.conecta = new DAO().conecta();
    }
    
        
    public void salvar (CategoriaLivro categoria){
        
        String sql = "INSERT INTO tb_categorias(nm_categoria) VALUES(?)";
        
        try{
            
            PreparedStatement stn = conecta.prepareStatement(sql);
            
            stn.setString(1, categoria.getNome());
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    public List<CategoriaLivro> listarTodos(){
        String sql = "SELECT * FROM tb_categorias ORDER BY id_categoria";
        ResultSet rs;
        List<CategoriaLivro> categorias = new ArrayList<CategoriaLivro>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                CategoriaLivro categoria = new CategoriaLivro();
                
                categoria.setId_Categoria(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nm_categoria"));
                categorias.add(categoria);
                
            }
            rs.close();
            stmt.close();
            return categorias;
        
            
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, e);
        }
        
        return categorias;
    }
    
        public void alterar (CategoriaLivro categoria){
        String sql = "UPDATE tb_categorias SET nm_categoria=?"
                +"WHERE id_categoria=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(2, categoria.getId_Categoria());
            stmt.setString(1, categoria.getNome());
            
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
        
    public void deletar(CategoriaLivro categoria){
    
        String sql = "DELETE FROM tb_categorias"
                +" WHERE id_categoria=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, categoria.getId_Categoria());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    
    }
    
    public List<CategoriaLivro> listarCategorias(){
        String sql = "SELECT nm_categoria FROM tb_categorias ORDER BY nm_categoria";
        ResultSet rs;
        List<CategoriaLivro> categorias = new ArrayList<CategoriaLivro>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                CategoriaLivro categoria = new CategoriaLivro();
                
                categoria.setNome(rs.getString("nm_categoria"));
                categorias.add(categoria);
                
            }
            rs.close();
            stmt.close();
            return categorias;
        
            
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, e);
        }
        
        return categorias;
    }
    
    
 
    
}