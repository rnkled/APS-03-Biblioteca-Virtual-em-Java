package DAO;

import java.sql.*;
import biblioteca.Usuario;
import biblioteca.Livro;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    private Connection conecta;
    
    public UsuarioDAO(){
        this.conecta = new DAO().conecta();
    }
    
    //Salva dados de Usuário no BD
    public void salvar (Usuario usuario){
        
        String sql = "INSERT INTO tb_usuarios(nome, cpf, email, login, senha, cargo, status) VALUES(?,?,?,?,?,?,'Ativo')";
        
        try{
            
            PreparedStatement stn = conecta.prepareStatement(sql);
            
            stn.setString(1, usuario.getNome());
            stn.setString(2, usuario.getCpf());
            stn.setString(3, usuario.getEmail());
            stn.setString(4, usuario.getLogin());
            stn.setString(5, usuario.getSenha());
            stn.setString(6, usuario.getCargo());
            
            stn.execute();
            stn.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    //Altera dados de Usuário no BD
    public void alterar (Usuario usuario){
        
        String sql = "UPDATE tb_usuarios SET nome=?, cpf=?,"
                +"email=?, login=?, senha=?, cargo=?"
                +"WHERE id_usuario=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(7, usuario.getId_usuario());
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getCargo());
            
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            throw new RuntimeException(e);
            
        }
    }
    
    //Deleta Usuário do BD
    public void deletar(Usuario usuario){
        String sql = "UPDATE tb_usuarios SET status='Desabilitado'"
                +" WHERE id_usuario=?";
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            
            stmt.setInt(1, usuario.getId_usuario());
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Erro! Você não pode deletar um Usuário com atividade ativa no sistema");

            
        }
    }
   
    //Executa a Validação de Login do Usuário no JFrame Login
    public boolean validarLoginUsuario(String login, String senha){
    boolean autenticado = false;
    
    String sql = "SELECT * FROM tb_usuarios WHERE login =? AND senha=? AND status='Ativo'";
    
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
        System.out.println(e);
    }
    
    return autenticado;
}

    //Valida se o Usuário é Administrador (possui o cargo Administrador)
    public boolean validarAdm(String login){
    boolean adm = false;
    
    String sql = "SELECT cargo FROM tb_usuarios WHERE login = ?";
    
    try{
        
        PreparedStatement stmt = conecta.prepareStatement(sql);
        stmt.setString(1, login);
        ResultSet rs;
        rs = stmt.executeQuery();
        
        if(rs.next()){
            String cargo = "Administrador";
            if(cargo.equals(rs.getString("cargo"))){
                adm = true;    
            }
            
        } else{
            stmt.close();
            return adm;
        }
    } catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    }
    
    return adm;
}

    //Faz a busca dos Dados do Usuário no BD
    public void dadosUsuario(Usuario usuario){
    String sql = "SELECT u.id_usuario, u.nome, u.cpf, u.email, u.cargo FROM tb_usuarios u WHERE login=?";
    
    try{
        PreparedStatement stmt = conecta.prepareStatement(sql);
        stmt.setString(1, usuario.getLogin());
        
        ResultSet rs;
        rs = stmt.executeQuery();
        
        if (rs.next()){
            usuario.setId_usuario(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setEmail(rs.getString("email"));
            usuario.setCargo(rs.getString("cargo"));
        } else {
            stmt.close();
        }
        
    } catch(SQLException e){
        JOptionPane.showMessageDialog(null, e);
    }
}

    //Gera um ArrayList com Todos Usuário do BD
    public List<Usuario> listarTodos(){
        String sql = "SELECT * FROM tb_usuarios  WHERE status = 'Ativo' ORDER BY id_usuario";
        ResultSet rs;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCargo(rs.getString("cargo"));
                
                usuarios.add(usuario);
            }
            rs.close();
            stmt.close();
            return usuarios;
        
            
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, e);
        }
        
        return usuarios;
    }
    
    //Gera um ArrayList com os Dados do Usuário Pesquisado
    public List<Usuario> pesquisa(int tipo, String texto){
        
        String where;
        
        if(tipo == 1){
            where = "WHERE cpf ilike ? AND status = 'Ativo' ORDER BY id_usuario";
        } else{
            where = "WHERE login ilike ? AND status = 'Ativo' ORDER BY id_usuario";
        }
        
        String sql = "SELECT * FROM tb_usuarios "+where;
        ResultSet rs;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        try{
            
            PreparedStatement stmt = conecta.prepareStatement(sql);
            stmt.setString(1, texto+"%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()){
            
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCargo(rs.getString("cargo"));
                
                usuarios.add(usuario);
            }
            rs.close();
            stmt.close();
            
            return usuarios;
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return usuarios;
    }

}

