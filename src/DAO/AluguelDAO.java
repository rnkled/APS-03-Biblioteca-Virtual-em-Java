/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import biblioteca.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author izael
 */
public class AluguelDAO {
    private Connection conecta;
    
    public AluguelDAO(){
        this.conecta = new DAO().conecta();
    }
    
    public void alugar(Aluguel alg, Cliente clt, Livro liv, Usuario usu){
        
        String sql = "INSERT INTO tb_aluguel(dt_aluguel, status, id_cliente, id_livro, id_usuario)"
                + "VALUES (?,?,?,?,?)";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, alg.getData());
            stmt.setString(2, alg.getStatus());
            stmt.setInt(3, clt.getId_cliente());
            stmt.setInt(4, liv.getId_Livro());
            stmt.setInt(5, usu.getId_usuario());
            
            stmt.execute();
            stmt.close();
        
        } catch(SQLException e){
            
            throw new RuntimeException(e);
        }
    }
    
    
    public void aprovacaoAluguel(Aluguel alg, int id_aluguel, Usuario us, String data){
        
        String sql = "UPDATE tb_aluguel SET status=?, id_usuario=?, dt_aluguel=? WHERE id_aluguel= ?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(4, id_aluguel);
            stmt.setString(1, alg.getStatus());
            stmt.setInt(2, us.getId_usuario());
            stmt.setString(3, data);
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    
    }
    
    public List<Aluguel> alugueisPendentes(){
        String sql = "SELECT al.id_aluguel, al.id_aluguel, al.status, cl.nome, cl.cpf, cl.qntd_liv_alug, liv.nm_livro, liv.quantidade " +
                     "FROM tb_aluguel al, tb_clientes cl, tb_livros liv " +
                     "WHERE al.status = 'Pendente' AND al.id_cliente = cl.id_cliente AND al.id_livro = liv.id_livro";
        
        List<Aluguel> algPendentes = new ArrayList<Aluguel>();
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            ResultSet rs;
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Aluguel algP = new Aluguel();
                Cliente clt = new Cliente();
                Livro liv = new Livro();
                
                algP.setId(rs.getInt("id_aluguel"));
                algP.setStatus(rs.getString("status"));
                clt.setNome(rs.getString("nome"));
                clt.setCpf(rs.getString("cpf"));
                clt.setQntd_livros_alugados(rs.getInt("qntd_liv_alug"));
                liv.setNome(rs.getString("nm_livro"));
                liv.setQuantidade(rs.getInt("quantidade"));
                algP.setClienteAlugou(clt);
                algP.setLivroAlugou(liv);
                
                algPendentes.add(algP);
            }
            
            rs.close();
            stmt.close();
            return algPendentes;
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return algPendentes;
    }
}


