/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import biblioteca.Cliente;
import biblioteca.Usuario;
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
public class ClienteDAO {
    private Connection conecta;
    
    public ClienteDAO(){
        this.conecta = new DAO().conecta();
    }
    
    //Salva dados de Cliente no BD
    public void salvar (Cliente cliente, Usuario usuario){
        
        Usuario us = new Usuario();
        
        String sql = "INSERT INTO tb_clientes(nome, cpf, endereco, data_nasc, email, login, senha, qntd_liv_alug, cad_por) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        
        try{
            
            PreparedStatement stn = conecta.prepareStatement(sql);
            
            stn.setString(1, cliente.getNome());
            stn.setString(2, cliente.getCpf());
            stn.setString(3, cliente.getEndereco());
            stn.setString(4, cliente.getData_nasc());
            stn.setString(5, cliente.getEmail());
            stn.setString(6, cliente.getLogin());
            stn.setString(7, cliente.getSenha());
            stn.setInt(8, cliente.getQntd_livros_alugados());
            stn.setInt(9, usuario.getId_usuario());
            
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    public void alterar (Cliente cliente){
        
        String sql = "UPDATE tb_clientes SET nome=?, cpf=?,"
                +"endereco=?, data_nasc=?, email=?, login=?,"
                + "senha=?"
                +"WHERE id_cliente=?";
        
        try{
            
            PreparedStatement stn = conecta.prepareStatement(sql);
            
            stn.setString(1, cliente.getNome());
            stn.setString(2, cliente.getCpf());
            stn.setString(3, cliente.getEndereco());
            stn.setString(4, cliente.getData_nasc());
            stn.setString(5, cliente.getEmail());
            stn.setString(6, cliente.getLogin());
            stn.setString(7, cliente.getSenha());
            stn.setInt(8, cliente.getId_cliente());
            
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    public void deletar(Cliente cliente){
        String sql = "DELETE FROM tb_clientes"
                +" WHERE id_cliente=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, cliente.getId_cliente());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    //Executa a Validação de Login do Cliente no JFrame Login
    public boolean validarLoginCliente(String login, String senha){
    boolean autenticado = false;
    
    String sql = "SELECT * FROM tb_clientes WHERE login =? AND senha=?";
    
    try{
        PreparedStatement stmt = conecta.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, senha);
        
        ResultSet rs;
        rs = stmt.executeQuery();
        
        if (rs.next()){
            login = rs.getString("login");
            senha = rs.getString("senha");
            autenticado = true;
        } else{
            stmt.close();
            return autenticado;
        }
    } catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    }
    
    return autenticado;
}
     
    //Faz a busca dos Dados do Usuário no BD
    public void dadosUsuario(Cliente cliente){
    String sql = "SELECT * FROM tb_clientes WHERE login = ?";
    
    try{
        PreparedStatement stmt = conecta.prepareStatement(sql);
        stmt.setString(1, cliente.getLogin());
        
        ResultSet rs;
        rs = stmt.executeQuery();
        
        if (rs.next()){
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setData_nasc(rs.getString("data_nasc"));
            cliente.setEmail(rs.getString("email"));
            cliente.setLogin(rs.getString("login"));
            cliente.setSenha(rs.getString("senha"));
        } else {
            stmt.close();
        }
        
    } catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    public List<Cliente> listarTodos(){
        String sql = "SELECT * FROM tb_clientes ORDER BY id_cliente";
        ResultSet rs;
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Cliente clt = new Cliente();
                clt.setId_cliente(rs.getInt("id_cliente"));
                clt.setNome(rs.getString("nome"));
                clt.setCpf(rs.getString("cpf"));
                clt.setEndereco(rs.getString("endereco"));
                clt.setData_nasc(rs.getString("data_nasc"));
                clt.setEmail(rs.getString("email"));
                clt.setLogin(rs.getString("login"));
                clt.setSenha(rs.getString("senha"));
                
                clientes.add(clt);
            }
            rs.close();
            stmt.close();
            return clientes;
        
            
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, e);
        }
        
        return clientes;
    }
    
    public List<Cliente> pesquisa(int tipo, String texto){
        
        String where;
        
        if(tipo == 1){
            where = "WHERE cpf ilike ? ORDER BY id_cliente";
        } else{
            where = "WHERE login ilike ? ORDER BY id_cliente";
        }
        
        String sql = "SELECT * FROM tb_clientes "+where;
        ResultSet rs;
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, texto+"%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Cliente clt = new Cliente();
                clt.setId_cliente(rs.getInt("id_cliente"));
                clt.setNome(rs.getString("nome"));
                clt.setCpf(rs.getString("cpf"));
                clt.setEndereco(rs.getString("endereco"));
                clt.setData_nasc(rs.getString("data_nasc"));
                clt.setEmail(rs.getString("email"));
                clt.setLogin(rs.getString("login"));
                clt.setSenha(rs.getString("senha"));
                
                clientes.add(clt);
                
                
            }
            rs.close();
            stmt.close();
            return clientes;
        
            
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, e);
        }
        
        return clientes;
    }
    
}