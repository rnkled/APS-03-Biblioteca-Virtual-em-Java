package biblioteca;
import java.util.Date;



public class Livro {
    
    private int id_Livro;
    private String nome;
    private String data;
    private String qualidade = "Desconhecida";
    private String resumo = "Desconhecido";
    private int quantidade;
    private int alugados;
    private AutorLivro autor;
    private CategoriaLivro categoria;
    
    public Livro() {
    };

    public Livro(int id_Livro, String nome, int quantidade, AutorLivro autor, CategoriaLivro categoria) {
        this.id_Livro = id_Livro;
        this.nome = nome;
        this.autor = autor;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }
    
    public void print(){
        
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Livro: "+this.getNome());
        //System.out.println("Autor: "+this.autor.getNome());
        //System.out.println("Categoria: "+this.categoria.getNome());
        System.out.println("Data: "+this.data.toString());
        System.out.println("Qualidade: "+this.getQualidade());
        System.out.println("Quantidade: "+this.getQuantidade());
        System.out.println("Resumo: "+this.getResumo());
       
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public AutorLivro getAutor() {
        return autor;
    }

    public void setAutor(AutorLivro autor) {
        this.autor = autor;
    }

    public CategoriaLivro getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaLivro categoria) {
        this.categoria = categoria;
    }
    
    public int getId_Livro() {
        return id_Livro;
    }

    public void setId_Livro(int id_Livro) {
        this.id_Livro = id_Livro;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getAlugados() {
        return alugados;
    }

    public void setAlugados(int alugados) {
        this.alugados = alugados;
    }
    
    
}

