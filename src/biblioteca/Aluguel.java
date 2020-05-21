package biblioteca;

public class Aluguel {
    private int Id;
    private String data;
    private String status;
    private Cliente clienteAlugou;
    private Livro livroAlugou;
    private String dataDevolucao;
    private int idCarrinho;
    private String nmLivroAlugado;
    private int idLivroAlugado;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getClienteAlugou() {
        return clienteAlugou;
    }

    public void setClienteAlugou(Cliente clienteAlugou) {
        this.clienteAlugou = clienteAlugou;
    }

    public Livro getLivroAlugou() {
        return livroAlugou;
    }

    public void setLivroAlugou(Livro livroAlugou) {
        this.livroAlugou = livroAlugou;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public String getNmLivroAlugado() {
        return nmLivroAlugado;
    }

    public void setNmLivroAlugado(String nmLivroAlugado) {
        this.nmLivroAlugado = nmLivroAlugado;
    }

    public int getIdLivroAlugado() {
        return idLivroAlugado;
    }

    public void setIdLivroAlugado(int idLivroAlugado) {
        this.idLivroAlugado = idLivroAlugado;
    }
    
}
