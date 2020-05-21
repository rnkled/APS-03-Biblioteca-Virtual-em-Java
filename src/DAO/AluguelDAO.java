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
    
    public void solicitaAluguel(Aluguel alg, Cliente clt, Livro liv){
        
        String sql = "INSERT INTO tb_aluguel(dt_aluguel, status, id_cliente, id_livro)"
                + "VALUES (?,?,?,?)";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setString(1, alg.getData());
            stmt.setString(2, alg.getStatus());
            stmt.setInt(3, clt.getId_cliente());
            stmt.setInt(4, liv.getId_Livro());
            
            stmt.execute();
            stmt.close();
        
        } catch(SQLException e){
            
            throw new RuntimeException(e);
        }
    }
    
    public void solicitaAluguelCarrinho(Aluguel alg){
        String sql = "UPDATE tb_carrinho SET status='Pedido Encaminhado' WHERE id_carrinho= ?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(1, alg.getIdCarrinho());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
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
                     "WHERE al.status = 'Em Análise' AND al.id_cliente = cl.id_cliente AND al.id_livro = liv.id_livro";
        
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
    
    public List<Aluguel> listarAlugueis(Cliente clt){

        String sql = "SELECT alg.dt_aluguel, alg.status, liv.nm_livro, alg.dt_devolucao FROM tb_aluguel alg , tb_livros liv WHERE alg.id_livro = liv.id_livro AND id_cliente = ? ORDER BY liv.nm_livro";
	
        ResultSet rs;
        List <Aluguel> alugueis = new ArrayList<Aluguel>();

        try{
		PreparedStatement stmt = conecta.prepareStatement(sql);
		stmt.setInt(1, clt.getId_cliente());
		rs = stmt.executeQuery();
		while(rs.next()){
			Aluguel alg = new Aluguel();
			alg.setData(rs.getString("dt_aluguel"));
			alg.setStatus(rs.getString("status"));
			alg.setDataDevolucao(rs.getString("dt_devolucao"));
			alg.setNmLivroAlugado(rs.getString("nm_livro"));

			alugueis.add(alg);		
                }
                
                rs.close();
                stmt.close();
                return alugueis;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }

	return alugueis;
    }

    public List<Aluguel> listarAlugueisStatus(Cliente clt, String status){

        String sql = "SELECT alg.id_aluguel, alg.dt_aluguel, alg.status, alg.dt_devolucao, liv.nm_livro FROM tb_aluguel alg , tb_livros liv WHERE alg.id_livro = liv.id_livro AND alg.id_cliente = ? AND alg.status = ? ORDER BY liv.nm_livro";
	
        ResultSet rs;
        List <Aluguel> alugueis = new ArrayList<Aluguel>();

        try{
		PreparedStatement stmt = conecta.prepareStatement(sql);
		stmt.setInt(1, clt.getId_cliente());
		stmt.setString(2, status);
		rs = stmt.executeQuery();
	
		while(rs.next()){
			Aluguel alg = new Aluguel();
                        alg.setId(rs.getInt("id_aluguel"));
			alg.setData(rs.getString("dt_aluguel"));
			alg.setStatus(rs.getString("status"));
			alg.setDataDevolucao(rs.getString("dt_devolucao"));
                        alg.setLivroAlugou(new Livro());
			alg.getLivroAlugou().setNome(rs.getString("nm_livro"));

			alugueis.add(alg);		
                }

                rs.close();
                stmt.close();
                return alugueis;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }

	return alugueis;
    }

    public void adcCarrinho(String data, Livro liv, Cliente clt){
	String sql = "INSERT INTO tb_carrinho(dt_inclusao, status, id_livro, id_cliente) VALUES (?,?,?,?)";

	try{
		PreparedStatement stmt = conecta.prepareStatement(sql);
		stmt.setString(1, data);
		stmt.setString(2, "No Carrinho");
		stmt.setInt(3, liv.getId_Livro());
		stmt.setInt(4, clt.getId_cliente());
		stmt.execute();
		stmt.close();

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public List<Aluguel> listarCarrinho(Cliente clt){
	String sql = "SELECT car.id_carrinho, car.id_livro, liv.nm_livro, car.dt_inclusao FROM tb_carrinho car, tb_livros liv WHERE car.id_livro = liv.id_livro AND car.id_cliente = ? AND status = 'No Carrinho' ORDER BY id_carrinho";
	
	ResultSet rs;
	List<Aluguel> carrinho = new ArrayList<Aluguel>();

	try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
	
            stmt.setInt(1, clt.getId_cliente());
            rs = stmt.executeQuery();

            while(rs.next()){
                    Aluguel alg = new Aluguel();
                    alg.setIdCarrinho(rs.getInt("id_carrinho"));
                    alg.setIdLivroAlugado(rs.getInt("id_livro"));
                    alg.setNmLivroAlugado(rs.getString("nm_livro"));
                    alg.setData(rs.getString("dt_inclusao"));
		
                    carrinho.add(alg);
            }

            rs.close();
            stmt.close();	
            return carrinho;

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return carrinho;
    }


    public void delCarrinho(Aluguel alg){
	String sql = "DELETE FROM tb_carrinho WHERE id_carrinho= ?";

	try{
		PreparedStatement stmt = conecta.prepareStatement(sql);
		stmt.setInt(1, alg.getIdCarrinho());
		stmt.execute();
		stmt.close();
        }  catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void devolverLivro(Aluguel alg, String data){
        String sql = "UPDATE tb_aluguel SET status= ?, dt_devolucao= ? WHERE id_aluguel= ?";
        
        try{
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setInt(3, alg.getId());
            stmt.setString(1, "Devolvido");
            stmt.setString(2, data);
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

}


