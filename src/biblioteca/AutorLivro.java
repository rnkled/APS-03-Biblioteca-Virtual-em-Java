package biblioteca;

public class AutorLivro {
       
    
    private int id_Autor;
    private String nome;

    
    public AutorLivro(){}
    
    public AutorLivro(int id_Autor, String nomeAutor){
        this.id_Autor = id_Autor;
        this.nome = nomeAutor;
    }
    
    
    
    public int getId_Autor() {
        return id_Autor;
    }

    public void setId_Autor(int id_Autor) {
        this.id_Autor = id_Autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
