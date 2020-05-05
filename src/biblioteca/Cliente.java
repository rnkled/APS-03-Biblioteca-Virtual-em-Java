package biblioteca;

public class Cliente {
    
    private String nome;
    private String cpf;
    private String endereco;
    private String data_nasc;
    private String email;
    private String login;
    private String senha;
    private int qntd_livros_alugados;

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    } 
    
    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getQntd_livros_alugados() {
        return qntd_livros_alugados;
    }

    public void setQntd_livros_alugados(int qntd_livros_alugados) {
        this.qntd_livros_alugados = qntd_livros_alugados;
    }
       
}
