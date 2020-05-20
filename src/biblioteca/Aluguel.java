package biblioteca;

public class Aluguel {
    private int Id;
    private String data;
    private String status;
    private Cliente clienteAlugou;
    private Livro livroAlugou;

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
    
    
    
}
