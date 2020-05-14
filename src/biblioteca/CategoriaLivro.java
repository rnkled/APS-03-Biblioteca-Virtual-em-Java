package biblioteca;

public class CategoriaLivro {
    
    
    private int id_Categoria;
    private String nome;

    public void conseguirLivros(){
    
    }
    
    public CategoriaLivro(){
    
    }

    public CategoriaLivro(int id_Categoria, String nome) {
        this.id_Categoria = id_Categoria;
        this.nome = nome;
    }

    
    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
