package biblioteca;




public class Livro {
    
    private int id_Livro;
    private String nome;
    private String data;
    private String qualidade;
    private String resumo;
    private String OPT_nm_autor;
    private String OPT_nm_categoria;
    private int quantidade;
    private int alugados;
    private int autorID;
    private int categoriaID;
    private byte[] capa;

    public Livro() {
    };

    public Livro(int id_Livro, String nome, int quantidade, int autorID, int categoriaID) {
        this.id_Livro = id_Livro;
        this.nome = nome;
        this.autorID = autorID;
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

    public int getAutorID() {
        return autorID;
    }

    public void setAutorID(int autor) {
        this.autorID = autor;
    }

    public int getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(int categoria) {
        this.categoriaID = categoria;
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
    
    
    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public String getOPT_nm_autor() {
        return OPT_nm_autor;
    }

    public void setOPT_nm_autor(String pesquisa_nm_autor) {
        this.OPT_nm_autor = pesquisa_nm_autor;
    }

    public String getOPT_nm_categoria() {
        return OPT_nm_categoria;
    }

    public void setOPT_nm_categoria(String pesquisa_nm_categoria) {
        this.OPT_nm_categoria = pesquisa_nm_categoria;
    }
    
    
    
    
}

