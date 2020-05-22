/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import biblioteca.Cliente;
import biblioteca.Livro;
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
public class LivroDAO {
    
    private Connection conecta;
    
    public LivroDAO(){
        this.conecta = new DAO().conecta();
    }
    
    public void salvar (Livro livro){
        
        String sql = "INSERT INTO tb_livros(nm_livro, data_publi, quantidade, qualidade, resumo, "
                + "qntd_alugado, id_categoria, id_autor, capa) VALUES(?,?,?,?,?,?,?,?,?)";
        
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
            stn.setBytes(9, livro.getCapa());
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    public void alterar (Livro livro){
        
        String sql = "UPDATE tb_livros SET nm_livro=?, data_publi=?, quantidade=?, qualidade=?, resumo=?, "
                + "qntd_alugado=?, id_categoria=?, id_autor=?, capa=? "
                + "WHERE id_livro =?";
        
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
            stn.setBytes(9, livro.getCapa());
            stn.setInt(10, livro.getId_Livro());
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
     public void remover(Livro livro){
        
            
        String sql = "DELETE FROM tb_livros"
                +" WHERE id_livro=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, livro.getId_Livro());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Erro! Você não pode deletar um Livro que esta sendo utilizado");
            
        }
    }
    
    //O Método a Seguir permite fazer uma Pesquisa passando o Tipo de pesquisa e
    //o Texto a ser Pesquisado. Logo após ele, existem diversos métodos especificos
    //que Também fazem diversos tipos de pesquisas.
    
    public List<Livro> pesquisa(String tipo, String texto){
        
        String select = "SELECT * FROM tb_livros, tb_autores, tb_categorias ";
        String where = "WHERE tb_livros.id_autor = tb_autores.id_autor " +
                        "AND tb_livros.id_categoria = tb_categorias.id_categoria";

        if (tipo == "Autor"){
            where = "WHERE nm_autor ilike ? " +
                    "AND tb_livros.id_autor = tb_autores.id_autor " +
                    "AND tb_livros.id_categoria = tb_categorias.id_categoria";}
        if (tipo == "Categoria"){
            where = "WHERE nm_categoria ilike ? " +
                    "AND tb_livros.id_autor = tb_autores.id_autor " +
                    "AND tb_livros.id_categoria = tb_categorias.id_categoria";
        }
        if (tipo == "Nome"){
            where = "WHERE nm_livro ilike ? " +
                    "AND tb_livros.id_autor = tb_autores.id_autor " +
                    "AND tb_livros.id_categoria = tb_categorias.id_categoria";
        }

        String sql = select+where;
        
        ResultSet rs;
        List<Livro> livros = new ArrayList<Livro>();
        
        try{       
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, "%"+texto+"%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Livro livro = new Livro();
                
                livro.setNome(rs.getString("nm_livro"));
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setData(rs.getString("data_publi"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setQualidade(rs.getString("qualidade"));
                livro.setAutorID(rs.getInt("id_autor"));
                livro.setCategoriaID(rs.getInt("id_categoria"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAlugados(rs.getInt("qntd_alugado"));
                livro.setCapa(rs.getBytes("capa"));
                
                livro.setOPT_nm_autor(rs.getString("nm_autor"));
                livro.setOPT_nm_categoria(rs.getString("nm_categoria"));
                
                livros.add(livro);
            }
            rs.close();
            stmt.close();
            
            return livros;
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return livros;
    }
    
    public List<Livro> pesquisaPorNome(String texto){
        
        String select = "SELECT * FROM tb_livros, tb_autores, tb_categorias ";
        String where = "WHERE nm_livro ilike ? " +
                       "AND tb_livros.id_autor = tb_autores.id_autor " +
                       "AND tb_livros.id_categoria = tb_categorias.id_categoria";

        String sql = select+where;
        
        ResultSet rs;
        List<Livro> livros = new ArrayList<Livro>();
        
        try{       
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, "%"+texto+"%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Livro livro = new Livro();
                
                livro.setNome(rs.getString("nm_livro"));
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setData(rs.getString("data_publi"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setQualidade(rs.getString("qualidade"));
                livro.setAutorID(rs.getInt("id_autor"));
                livro.setCategoriaID(rs.getInt("id_categoria"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAlugados(rs.getInt("qntd_alugado"));
                livro.setCapa(rs.getBytes("capa"));
                
                livro.setOPT_nm_autor(rs.getString("nm_autor"));
                livro.setOPT_nm_categoria(rs.getString("nm_categoria"));
                
                livros.add(livro);
            }
            rs.close();
            stmt.close();
            
            return livros;
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return livros;
    }
    
    public List<Livro> pesquisaPorAutor(String texto){
        
        String select = "SELECT * FROM tb_livros, tb_autores, tb_categorias ";
        String where = "WHERE nm_autor ilike ? " +
                    "AND tb_livros.id_autor = tb_autores.id_autor " +
                    "AND tb_livros.id_categoria = tb_categorias.id_categoria";

        String sql = select+where;
        
        ResultSet rs;
        List<Livro> livros = new ArrayList<Livro>();
        
        try{       
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, "%"+texto+"%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Livro livro = new Livro();
                
                livro.setNome(rs.getString("nm_livro"));
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setData(rs.getString("data_publi"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setQualidade(rs.getString("qualidade"));
                livro.setAutorID(rs.getInt("id_autor"));
                livro.setCategoriaID(rs.getInt("id_categoria"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAlugados(rs.getInt("qntd_alugado"));
                livro.setCapa(rs.getBytes("capa"));
                
                livro.setOPT_nm_autor(rs.getString("nm_autor"));
                livro.setOPT_nm_categoria(rs.getString("nm_categoria"));
                
                livros.add(livro);
            }
            rs.close();
            stmt.close();
            
            return livros;
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return livros;
    }


    public List<Livro> pesquisaPorCategoria(String texto){
        String select = "SELECT * FROM tb_livros, tb_autores, tb_categorias ";
        String where = "WHERE nm_categoria ilike ? " +
                       "AND tb_livros.id_autor = tb_autores.id_autor " +
                       "AND tb_livros.id_categoria = tb_categorias.id_categoria";

        String sql = select+where;
        
        ResultSet rs;
        List<Livro> livros = new ArrayList<Livro>();
        
        try{       
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, "%"+texto+"%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Livro livro = new Livro();
                
                livro.setNome(rs.getString("nm_livro"));
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setData(rs.getString("data_publi"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setQualidade(rs.getString("qualidade"));
                livro.setAutorID(rs.getInt("id_autor"));
                livro.setCategoriaID(rs.getInt("id_categoria"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAlugados(rs.getInt("qntd_alugado"));
                livro.setCapa(rs.getBytes("capa"));
                
                livro.setOPT_nm_autor(rs.getString("nm_autor"));
                livro.setOPT_nm_categoria(rs.getString("nm_categoria"));
                
                livros.add(livro);
            }
            rs.close();
            stmt.close();
            
            return livros;
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return livros;
    }
    
    
    public Livro pesquisaPorID(int id){
        
        String select = "SELECT * FROM tb_livros, tb_autores, tb_categorias ";
        String where = "WHERE id_livro = ? " +
                    "AND tb_livros.id_autor = tb_autores.id_autor " +
                    "AND tb_livros.id_categoria = tb_categorias.id_categoria";

        String sql = select+where;
        ResultSet rs;
        Livro livro = new Livro();
        
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                livro.setNome(rs.getString("nm_livro"));
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setData(rs.getString("data_publi"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setQualidade(rs.getString("qualidade"));
                livro.setAutorID(rs.getInt("id_autor"));
                livro.setCategoriaID(rs.getInt("id_categoria"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAlugados(rs.getInt("qntd_alugado"));
                livro.setCapa(rs.getBytes("capa"));
            }
            rs.close();
            stmt.close();
            
            return livro;
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return livro;
    }
    
    public List<Livro> getTodos(){
        
        String sql = "SELECT * FROM tb_livros, tb_categorias, tb_autores " +
                     "WHERE tb_livros.id_categoria = tb_categorias.id_categoria "+
                     "AND tb_livros.id_autor = tb_autores.id_autor ORDER BY tb_livros.id_livro";
        ResultSet rs;
        List<Livro> livros = new ArrayList<Livro>();

    try{
        PreparedStatement stmt = conecta.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()){

            Livro livro = new Livro();
                
                livro.setNome(rs.getString("nm_livro"));
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setData(rs.getString("data_publi"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setQualidade(rs.getString("qualidade"));
                livro.setAutorID(rs.getInt("id_autor"));
                livro.setCategoriaID(rs.getInt("id_categoria"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAlugados(rs.getInt("qntd_alugado"));
                livro.setCapa(rs.getBytes("capa"));
                livro.setOPT_nm_autor(rs.getString("nm_autor"));
                livro.setOPT_nm_categoria(rs.getString("nm_categoria"));
                
                livros.add(livro);
        }
        rs.close();
        stmt.close();
        return livros;
    } catch(SQLException e){

        JOptionPane.showMessageDialog(null, e);
        
        }
    return livros;
    
    }
    
    public List<Livro> listarLivrosAlug(){
        
        String sql = "SELECT id_livro, nm_livro, quantidade FROM tb_livros ORDER BY id_livro";
        ResultSet rs;
        List<Livro> livros = new ArrayList<Livro>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Livro livro = new Livro();
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setNome(rs.getString("nm_livro"));
                livro.setQuantidade(rs.getInt("quantidade"));
                
                livros.add(livro);
            }
            
            rs.close();
            stmt.close();
            return livros;
            
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, e);
        }
        
        return livros;
    }
        
    public void dadosLivroAlug(Livro livro){
        String sql = "SELECT l.id_livro, l.nm_livro, l.quantidade, l.qntd_alugado " +
                     "FROM tb_livros l " +
                     "WHERE nm_livro= ?";
            
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, livro.getNome());
            
            ResultSet rs;
            rs = stmt.executeQuery();
            
            if(rs.next()){
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setNome(rs.getString("nm_livro"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setAlugados(rs.getInt("qntd_alugado"));
            } else{
                stmt.close();
            }
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void alugaLivro(Livro livro){
        String sql = "UPDATE tb_livros SET quantidade= ?, qntd_alugado= ? WHERE id_livro= ?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, livro.getQuantidade() - 1);
            stmt.setInt(2, livro.getAlugados() + 1);
            stmt.setInt(3, livro.getId_Livro());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void devolveLivro(Livro livro){
        String sql = "UPDATE tb_livros SET quantidade= ?, qntd_alugado= ? WHERE id_livro= ?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, livro.getQuantidade() + 1);
            stmt.setInt(2, livro.getAlugados() - 1);
            stmt.setInt(3, livro.getId_Livro());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public List<Livro> getRecomendados(){
        String sql = "SELECT tb_recomendados.*, tb_livros.* FROM tb_recomendados INNER JOIN tb_livros ON tb_recomendados.id_livro = tb_livros.id_livro";
        ResultSet rs;
        List<Livro> livros = new ArrayList<Livro>();

    try{
        PreparedStatement stmt = conecta.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()){

            Livro livro = new Livro();
                
                livro.setNome(rs.getString("nm_livro"));
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setData(rs.getString("data_publi"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setQualidade(rs.getString("qualidade"));
                livro.setAutorID(rs.getInt("id_autor"));
                livro.setCategoriaID(rs.getInt("id_categoria"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAlugados(rs.getInt("qntd_alugado"));
                livro.setCapa(rs.getBytes("capa"));
                
                livros.add(livro);
        }
        rs.close();
        stmt.close();
        return livros;


    } catch(SQLException e){

        JOptionPane.showMessageDialog(null, e);
        
        }
    return livros;
    }
    
    
    public List<Livro> getLancamentos(){
        String sql = "SELECT tb_lancamentos.*, tb_livros.* FROM tb_lancamentos "
                + "INNER JOIN tb_livros ON tb_lancamentos.id_livro = tb_livros.id_livro";
        ResultSet rs;
        List<Livro> livros = new ArrayList<Livro>();

    try{
        PreparedStatement stmt = conecta.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()){

            Livro livro = new Livro();
                
                livro.setNome(rs.getString("nm_livro"));
                livro.setId_Livro(rs.getInt("id_livro"));
                livro.setData(rs.getString("data_publi"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setQualidade(rs.getString("qualidade"));
                livro.setAutorID(rs.getInt("id_autor"));
                livro.setCategoriaID(rs.getInt("id_categoria"));
                livro.setResumo(rs.getString("resumo"));
                livro.setAlugados(rs.getInt("qntd_alugado"));
                livro.setCapa(rs.getBytes("capa"));
                
                livros.add(livro);
        }
        rs.close();
        stmt.close();
        return livros;


    } catch(SQLException e){

        JOptionPane.showMessageDialog(null, e);
        
        }
    return livros;
    }
    
    
    public void salvarLancamento(Livro livro){
        
            
        String sql = "INSERT INTO tb_lancamentos(id_livro) VALUES(?)";
        
        try{
            
            PreparedStatement stn = conecta.prepareStatement(sql);
            
            stn.setInt(1, livro.getId_Livro());
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    public void salvarRecomendado(Livro livro){
        
            
        String sql = "INSERT INTO tb_recomendados(id_livro) VALUES(?)";
        
        try{
            
            PreparedStatement stn = conecta.prepareStatement(sql);
            
            stn.setInt(1, livro.getId_Livro());
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    public void removerLancamento(Livro livro){
        
        String sql = "DELETE FROM tb_lancamentos"
                +" WHERE id_livro=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, livro.getId_Livro());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
            
    }
    
    public void removerRecomendado(Livro livro){
        
            
        String sql = "DELETE FROM tb_recomendados"
                +" WHERE id_livro=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, livro.getId_Livro());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    
    
    
}
    
