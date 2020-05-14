/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import biblioteca.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kener_000
 */
public class LivroDAO {
    
        private Connection conecta;
    
        public LivroDAO(){
        this.conecta = new DAO().conecta();
    }
    
        public void salvar (Livro livro){
        
        String sql = "INSERT INTO tb_livros(nm_livro, data_publi, quantidade, qualidade, resumo, "
                + "qntd_alugado, id_categoria, id_autor) VALUES(?,?,?,?,?,?,?,?)";
        
        try{
            
            PreparedStatement stn = conecta.prepareStatement(sql);
            
            stn.setString(1, livro.getNome());
            stn.setString(2, livro.getData());
            stn.setInt(3, livro.getQuantidade());
            stn.setString(4, livro.getQualidade());
            stn.setString(5, livro.getResumo());
            stn.setInt(6, livro.getAlugados());
            stn.setInt(7, livro.getCategoriaID());
            stn.setInt(8, livro.getAutorID());
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
}
