package view;

import DAO.*;
import biblioteca.*;
import CustomClass.*;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AreaUsuario extends javax.swing.JFrame {
    
    private Usuario sessao;
    private BufferedImage selectedImage;
    List<Integer> idsAut = new ArrayList<Integer>();
    private Cliente cltSessao;
    private Livro livSessao;
    private int id_selec;
    private String clt_selec;
    private String liv_selec;
    
    public AreaUsuario() {
        initComponents();
        setCbCargos();
        setCbAltDelCargos();
        listarTabela();
        listarTabelaCliente();
        listarTabelaCategorias();
        listarTabelaAutores();
        listarTabelaRecomendados();
        listarTabelaLancamentos();
        listarTabelaAlugClt();
        listarTabelaAlugLivro();
        listarTabelaPedidos();
    }
    
    //Adiciona as opções de Cargos na ComboBox de Cadastro de Usuários
    public void setCbCargos(){
        jCbCargo.removeAllItems();
        jCbCargo.addItem("-");
        jCbCargo.addItem("Administrador");
        jCbCargo.addItem("Bibliotecário");
    }
    
    //Adiciona as opções de Cargos na ComboBox de Alt/Del Usuários
    public void setCbAltDelCargos(){
        jCbAltDelCargo.removeAllItems();
        jCbAltDelCargo.addItem("-");
        jCbAltDelCargo.addItem("Administrador");
        jCbAltDelCargo.addItem("Bibliotecário");
    }
    
    //Pega os dados do Usuários e inclui no JFrame
    public void setUsLogin(Usuario usuario){
        UsuarioDAO us = new UsuarioDAO();
        us.dadosUsuario(usuario);
        
        //Salvo os dados do Usuario na Varia Sessão
        sessao = usuario;
        
    }
    
    //Validação de Usuário, se é ou não Administrador (habilita/desabilita Ger. Usuários)
    public void validaAdm(boolean x){
        if(x == false){
            jBtGerAnuncios.setEnabled(false);
        } else{
            jBtGerAnuncios.setEnabled(true);
        }
    }
    
    
    
    //Pega nome do Usuário, e inclui na TelaInicial
    public void bemVindo(){
        jLabelNomeExib.setText(sessao.getNome());
        jLabelCargo.setText(sessao.getCargo());
    }
    
    //Exibi a tabela da TelaUsuarios
    public void listarTabela(){
        DefaultTableModel valor = (DefaultTableModel) jTableUsuarios.getModel();
        
        valor.getDataVector().removeAllElements();
        UsuarioDAO usDAO = new UsuarioDAO();
        
        List<Usuario> usuarios = usDAO.listarTodos();
        int i = 0;
        while (usuarios.size() > i){
        
            valor.addRow(new Object[]{String.valueOf(usuarios.get(i).getId_usuario()), usuarios.get(i).getNome(),
            usuarios.get(i).getCpf(), usuarios.get(i).getEmail(), usuarios.get(i).getLogin(), usuarios.get(i).getSenha(),
            usuarios.get(i).getCargo()});
            i++;
            
        }
    }
    
    public void listarTabelaCliente(){
        DefaultTableModel valor = (DefaultTableModel) jTableClientes.getModel();
        
        valor.getDataVector().removeAllElements();
        ClienteDAO cltDAO = new ClienteDAO();
        
        List<Cliente> clientes = cltDAO.listarTodos();
        int i = 0;
        while (clientes.size() > i){
        
            valor.addRow(new Object[]{String.valueOf(clientes.get(i).getId_cliente()), clientes.get(i).getNome(),
            clientes.get(i).getCpf(), clientes.get(i).getEndereco(), clientes.get(i).getData_nasc(),
            clientes.get(i).getEmail(), clientes.get(i).getLogin(), clientes.get(i).getSenha()});
            i++;
            
        }
    }
    
    public void listarTabelaAlugClt(){
        DefaultTableModel valor = (DefaultTableModel) jTableAlugClt.getModel();
        valor.getDataVector().removeAllElements();
        
        ClienteDAO cltDAO = new ClienteDAO();
        List<Cliente> clientes = cltDAO.listarCltAluguel();
        
        int i = 0;
        while (clientes.size() > i){
        
            valor.addRow(new Object[]{String.valueOf(clientes.get(i).getId_cliente()), clientes.get(i).getNome(),
                         String.valueOf(clientes.get(i).getQntd_livros_alugados())});
            i++;
        }
    }
    
    public void listarTabelaAlugLivro(){
        DefaultTableModel valor = (DefaultTableModel) jTableAlugLivros.getModel();
        valor.getDataVector().removeAllElements();
        
        LivroDAO livDAO = new LivroDAO();
        List<Livro> livros = livDAO.listarLivrosAlug();
        
        int i = 0;
        while(livros.size() > i){
            valor.addRow(new Object[]{String.valueOf(livros.get(i).getId_Livro()), livros.get(i).getNome(), String.valueOf(livros.get(i).getQuantidade())});
            i++;
        }
    }
    
    public void listarTabelaPedidos(){
        DefaultTableModel valor = (DefaultTableModel) jTablePedidos.getModel();
        valor.getDataVector().removeAllElements();
        
        AluguelDAO algDAO = new AluguelDAO();
        List<Aluguel> algPendentes = algDAO.alugueisPendentes();
        
        int i = 0;
        while(algPendentes.size() > i){
            valor.addRow(new Object[]{String.valueOf(algPendentes.get(i).getId()),
                         algPendentes.get(i).getClienteAlugou().getNome(),
                         algPendentes.get(i).getClienteAlugou().getCpf(),
                         algPendentes.get(i).getClienteAlugou().getQntd_livros_alugados(),
                         algPendentes.get(i).getLivroAlugou().getNome(),
                         algPendentes.get(i).getLivroAlugou().getQuantidade(),
                         algPendentes.get(i).getStatus()});
            i++;
        }
    }
    
    
    List<Integer> idsCat = new ArrayList<Integer>();
    public void listarTabelaCategorias(){
        DefaultTableModel valor = (DefaultTableModel) jTableCategoriasLivro.getModel();
        idsCat.clear();
        valor.getDataVector().removeAllElements();
        jComboBoxCategoriaLivro.removeAllItems();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        List<CategoriaLivro> categorias = categoriaDAO.listarTodos();
        int i = 0;
        while (categorias.size() > i){
        
            valor.addRow(new Object[]{String.valueOf(categorias.get(i).getId_Categoria()), 
            categorias.get(i).getNome(),});
            jComboBoxCategoriaLivro.addItem(categorias.get(i).getNome());
            idsCat.add(categorias.get(i).getId_Categoria());
            i++;
        }
    }
    
    public void listarTabelaAutores(){
        DefaultTableModel valor = (DefaultTableModel) jTableAutoresLivros.getModel();
        idsAut.clear();
        valor.getDataVector().removeAllElements();
        jComboBoxAutorLivro.removeAllItems();
        AutorDAO autorDAO = new AutorDAO();
        
        List<AutorLivro> autores = autorDAO.listarTodos();
        int i = 0;
        while (autores.size() > i){
        
            valor.addRow(new Object[]{String.valueOf(autores.get(i).getId_Autor()), 
            autores.get(i).getNome(),});
            jComboBoxAutorLivro.addItem(autores.get(i).getNome());
            idsAut.add(autores.get(i).getId_Autor());
            i++;
        }
    
    }
    

    public void listarTabelaRecomendados(){
        
        DefaultTableModel valor = (DefaultTableModel) jTableRecomendados.getModel();
        valor.getDataVector().removeAllElements();
        LivroDAO livroDAO = new LivroDAO();
        
        List<Livro> livros = livroDAO.getRecomendados();
        int i = 0;
        while (livros.size() > i){
        
            valor.addRow(new Object[]{String.valueOf(livros.get(i).getId_Livro()), 
            livros.get(i).getNome(),});
            i++;
        }
            
        DefaultTableModel valortabela2 = (DefaultTableModel) jTablePesquisaReco.getModel();
        valortabela2.getDataVector().removeAllElements();
        
        List<Livro> livrostabela2 = new ArrayList<Livro>();
        
        livrostabela2 = livroDAO.getTodos();
        
        int j = 0;
        while (livrostabela2.size() > j){
            valortabela2.addRow(new Object[]{String.valueOf(livrostabela2.get(j).getId_Livro()), 
            livrostabela2.get(j).getNome()});
            j++;
        }
        
    }
    
    public void listarTabelaLancamentos(){
        
        DefaultTableModel valor = (DefaultTableModel) jTableLancamentos.getModel();
        valor.getDataVector().removeAllElements();
        LivroDAO livroDAO = new LivroDAO();
        
        List<Livro> livros = livroDAO.getLancamentos();
        int i = 0;
        while (livros.size() > i){
        
            valor.addRow(new Object[]{String.valueOf(livros.get(i).getId_Livro()), 
            livros.get(i).getNome(),});
            i++;
        }
        
        DefaultTableModel valortabela2 = (DefaultTableModel) jTablePesquisaLanca.getModel();
        valortabela2.getDataVector().removeAllElements();
        
        List<Livro> livrostabela2 = new ArrayList<Livro>();
        
        livrostabela2 = livroDAO.getTodos();
        
        int j = 0;
        while (livrostabela2.size() > j){
            valortabela2.addRow(new Object[]{String.valueOf(livrostabela2.get(j).getId_Livro()), 
            livrostabela2.get(j).getNome()});
            j++;
        }}
        
    public String getDataAtual(){
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formData = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(formData.format(data));
    }
    
    //Limpa os campos de Cadastro de Usuários     
    public void limparCamposCad(){
        jTxtNome.setText("");
        jTxtCpf.setText("");
        jTxtEmail.setText("");
        jTxtLogin.setText("");
        jPassSenha.setText("");
        jCbCargo.setSelectedItem("-");
    }
    
    //Limpa os campos de Alt/Del Usuários
    public void limparCamposAltDel(){
        jTxtAltDelId.setText("");
        jTxtAltDelNome.setText("");
        jTxtAltDelCpf.setText("");
        jTxtAltDelEmail.setText("");
        jTxtAltDelLogin.setText("");
        jTxtAltDelSenha.setText("");
        jCbAltDelCargo.setSelectedItem("-");
    }
    
    public void limparCamposCadCliente(){
        jTxtNomeGerCliente.setText("");
        jTxtCpfGerCliente.setText("");
        jTxtDataNascGerCliente.setText("");
        jTxtEmailGerCliente.setText("");
        jTxtLoginGerCliente.setText("");
        jPassSenhaGerCliente.setText("");
        jTxtEnderecoGerCliente.setText("");
    }
    
    public void limparCamposAltClientes(){
        jTxtAltIdGerClientes.setText("");
        jTxtAltNomeGerClientes.setText("");
        jTxtAltCpfGerClientes.setText("");
        jTxtAltEnderecoGerClientes.setText("");
        jTxtAltDataNascGerClientes.setText("");
        jTxtAltEmailGerClientes.setText("");
        jTxtAltLoginGerClientes.setText("");
        jTxtAltSenhaGerClientes.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bGPesquisa = new javax.swing.ButtonGroup();
        jPanelFather = new javax.swing.JPanel();
        jPanelHome = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabelCargo = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabelNomeExib = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanelGerUsuarios = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTxtNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTxtCpf = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jTxtEmail = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jCbCargo = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTxtLogin = new javax.swing.JTextField();
        jPassSenha = new javax.swing.JPasswordField();
        jBtCadastrar = new javax.swing.JButton();
        jBtLimpar = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTxtAltDelId = new javax.swing.JTextField();
        jTxtAltDelNome = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTxtAltDelCpf = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jTxtAltDelEmail = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTxtAltDelLogin = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTxtAltDelSenha = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jCbAltDelCargo = new javax.swing.JComboBox<>();
        jBtAlterar = new javax.swing.JButton();
        jBtExcluir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jRbId = new javax.swing.JRadioButton();
        jRbLogin = new javax.swing.JRadioButton();
        jTxtPesqId = new javax.swing.JTextField();
        jTxtPesqLogin = new javax.swing.JTextField();
        jBtPesquisar = new javax.swing.JButton();
        jPanelGerCliente = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel19 = new javax.swing.JPanel();
        jLabelNomeGerCliente = new javax.swing.JLabel();
        jTxtNomeGerCliente = new javax.swing.JTextField();
        jLabelCPFGerCliente1 = new javax.swing.JLabel();
        jTxtCpfGerCliente = new javax.swing.JFormattedTextField();
        jLabelEnderecoGerCliente1 = new javax.swing.JLabel();
        jTxtEnderecoGerCliente = new javax.swing.JTextField();
        jTxtDataNascGerCliente = new javax.swing.JFormattedTextField();
        jLabelEnderecoGerCliente2 = new javax.swing.JLabel();
        jTxtEmailGerCliente = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jBtCadastrar1 = new javax.swing.JButton();
        jBtLimpar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTxtLoginGerCliente = new javax.swing.JTextField();
        jPassSenhaGerCliente = new javax.swing.JPasswordField();
        jPanel20 = new javax.swing.JPanel();
        jLabelIdGerClientes = new javax.swing.JLabel();
        jLabelNomeAlterarGerClientes = new javax.swing.JLabel();
        jTxtAltIdGerClientes = new javax.swing.JTextField();
        jTxtAltNomeGerClientes = new javax.swing.JTextField();
        jLabelAltCPFGerClientes = new javax.swing.JLabel();
        jTxtAltCpfGerClientes = new javax.swing.JFormattedTextField();
        jLabelAltEnderecoGerCliente = new javax.swing.JLabel();
        jTxtAltEnderecoGerClientes = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTxtAltEmailGerClientes = new javax.swing.JTextField();
        jTxtAltLoginGerClientes = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabelEnderecoGerCliente = new javax.swing.JLabel();
        jTxtAltSenhaGerClientes = new javax.swing.JTextField();
        jTxtAltDataNascGerClientes = new javax.swing.JFormattedTextField();
        jBtAlterarGerCliente = new javax.swing.JButton();
        jBtExcluirGerCliente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jRbIdGerCliente = new javax.swing.JRadioButton();
        jRbLoginGerCliente = new javax.swing.JRadioButton();
        jTxtPesqIdGerCliente = new javax.swing.JTextField();
        jTxtPesqLoginGerCliente = new javax.swing.JTextField();
        jBtPesquisarGerCliente = new javax.swing.JButton();
        jPanelGerLivros = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelDataLivro = new javax.swing.JLabel();
        jTextFieldNomeLivro = new javax.swing.JTextField();
        jTextFieldQuantidadeLivro = new javax.swing.JTextField();
        jLabelQuantidadeLivro = new javax.swing.JLabel();
        jFormattedTextFieldDataLivro = new javax.swing.JFormattedTextField();
        jLabelResumoLivro = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaResumoLivro = new javax.swing.JTextArea();
        jLabelNomeLivro = new javax.swing.JLabel();
        jComboBoxAutorLivro = new javax.swing.JComboBox<>();
        jLabelAutorLivro = new javax.swing.JLabel();
        jComboBoxCategoriaLivro = new javax.swing.JComboBox<>();
        jLabelQualidadeLivro = new javax.swing.JLabel();
        jTextFieldQualidadeLivro = new javax.swing.JTextField();
        jLabelCategoriaLivro = new javax.swing.JLabel();
        jButtonCadastrarLivro = new javax.swing.JButton();
        jButtonLimparCamposLivro = new javax.swing.JButton();
        jPanelCapaLivro = new javax.swing.JPanel();
        jLabelImagemCapa = new javax.swing.JLabel();
        jButtonAddCapa = new javax.swing.JButton();
        jLabelGerenciarCategorias = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPaneCadastrarCat = new javax.swing.JPanel();
        jLabelNomeAutLivro = new javax.swing.JLabel();
        jTextFieldNomeAutLivro = new javax.swing.JTextField();
        jButtonAddNovoAutLivro = new javax.swing.JButton();
        jButtonRemoverAutLivro = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableAutoresLivros = new javax.swing.JTable();
        jButtonAlterarAutLivro = new javax.swing.JButton();
        jTextFieldSelAutNome = new javax.swing.JTextField();
        jTextFieldSelAutID = new javax.swing.JTextField();
        jLabelCadastrarAutoresLivro = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanelCadastrarAut = new javax.swing.JPanel();
        jLabelNomeCatLivro = new javax.swing.JLabel();
        jButtonRemoverCatLivro = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCategoriasLivro = new javax.swing.JTable();
        jButtonAddNovaCatLivro = new javax.swing.JButton();
        jButtonAlterarCatLivro = new javax.swing.JButton();
        jTextFieldNovaCatLivro = new javax.swing.JTextField();
        jTextFieldSelCatID = new javax.swing.JTextField();
        jTextFieldSelCatNome = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanelGerAlugueis = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableAlugLivros = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableAlugClt = new javax.swing.JTable();
        jBtReprovar = new javax.swing.JButton();
        jBtAprovar = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTxtAlugCliente = new javax.swing.JTextField();
        jTxtAlugCpf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtAlugLivro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtAlugQntd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtAlugData = new javax.swing.JTextField();
        jBtAlugar = new javax.swing.JButton();
        jBtLimpDadosAlug = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTablePedidos = new javax.swing.JTable();
        jPanelAnuncios = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabelGerenciarCategorias3 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableRecomendados = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTablePesquisaReco = new javax.swing.JTable();
        jTextFieldPesquisaReco = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jButtonPesquisaReco = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabelGerenciarCategorias4 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTableLancamentos = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTablePesquisaLanca = new javax.swing.JTable();
        jTextFieldPesquisaLanca = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jButtonPesquisarLanca = new javax.swing.JButton();
        jLabelGerenciarCategorias5 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        SideMenu = new javax.swing.JPanel();
        jBtGerAnuncios = new javax.swing.JButton();
        jBtGerClientes = new javax.swing.JButton();
        jBtGerLivros = new javax.swing.JButton();
        jBtGerAlugueis = new javax.swing.JButton();
        jBtHome = new javax.swing.JButton();
        jBtLogOut = new javax.swing.JButton();
        jBtGerUsuarios1 = new javax.swing.JButton();
        jLabelLogo = new javax.swing.JLabel();
        jLabelCategoria = new javax.swing.JLabel();
        jSeparatorCat = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(400, 100));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelFather.setBackground(new java.awt.Color(153, 153, 153));
        jPanelFather.setMaximumSize(new java.awt.Dimension(745, 623));
        jPanelFather.setLayout(new java.awt.CardLayout());

        jPanelHome.setBackground(new java.awt.Color(0, 0, 0));
        jPanelHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelHome.setMaximumSize(new java.awt.Dimension(745, 623));
        jPanelHome.setMinimumSize(new java.awt.Dimension(745, 623));
        jPanelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(67, 67, 67));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel28.setBackground(new java.awt.Color(0, 0, 0));
        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(240, 240, 240));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Este é o Painel de Controle do Funcionário.");

        jLabel32.setBackground(new java.awt.Color(0, 0, 0));
        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(240, 240, 240));
        jLabel32.setText("Você possui um Nivel de Acesso ");

        jLabelCargo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCargo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCargo.setText("jLabelCargo");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel32)
                .addGap(2, 2, 2)
                .addComponent(jLabelCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCargo)
                    .addComponent(jLabel32))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanelHome.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 380, 100));

        jPanel14.setBackground(new java.awt.Color(67, 67, 67));
        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel14.setPreferredSize(new java.awt.Dimension(745, 20));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        jPanelHome.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 10, 17, 600));

        jPanel5.setBackground(new java.awt.Color(89, 89, 89));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelNomeExib.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNomeExib.setForeground(new java.awt.Color(255, 204, 204));
        jLabelNomeExib.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNomeExib.setText("jLabelNomeExib");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(240, 240, 240));
        jLabel11.setText("Seja Bem-Vindo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel11)
                .addContainerGap(115, Short.MAX_VALUE))
            .addComponent(jLabelNomeExib, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNomeExib)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanelHome.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 320, 90));

        jPanel7.setBackground(new java.awt.Color(67, 67, 67));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setPreferredSize(new java.awt.Dimension(745, 20));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jPanelHome.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 480, 830, 20));

        jPanel6.setBackground(new java.awt.Color(67, 67, 67));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setPreferredSize(new java.awt.Dimension(745, 20));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 806, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jPanelHome.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 100, 810, 20));

        jPanel13.setBackground(new java.awt.Color(67, 67, 67));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setPreferredSize(new java.awt.Dimension(745, 20));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jPanelHome.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 603, 820, 20));

        jPanel12.setBackground(new java.awt.Color(67, 67, 67));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setPreferredSize(new java.awt.Dimension(745, 20));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jPanelHome.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 820, 20));

        jPanel8.setBackground(new java.awt.Color(67, 67, 67));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        jPanelHome.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 340, 110));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/biblioteca.jpg"))); // NOI18N
        jLabel23.setText("jLabel23");
        jPanelHome.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, 830, 760));

        jPanelFather.add(jPanelHome, "TelaInicial");

        jPanelGerUsuarios.setBackground(new java.awt.Color(204, 204, 204));
        jPanelGerUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 16)); // NOI18N
        jLabel8.setText("Alterar / Deletar Usuários:");
        jPanelGerUsuarios.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "E-mail", "Login", "Senha", "Cargo"
            }
        ));
        jTableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableUsuarios);

        jPanelGerUsuarios.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 690, 118));

        jLabel13.setFont(new java.awt.Font("Rockwell", 1, 16)); // NOI18N
        jLabel13.setText("Cadastro de Usuários:");
        jPanelGerUsuarios.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));
        jPanelGerUsuarios.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 230, 10));
        jPanelGerUsuarios.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 230, 10));

        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Nome:");
        jPanel17.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));
        jPanel17.add(jTxtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 340, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("CPF: ");
        jPanel17.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        try {
            jTxtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTxtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCpfActionPerformed(evt);
            }
        });
        jPanel17.add(jTxtCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 149, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("E-mail:");
        jPanel17.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));
        jPanel17.add(jTxtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 180, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Cargo:");
        jPanel17.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        jCbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel17.add(jCbCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 70, 120, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Login:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Senha:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 68, -1, -1));
        jPanel3.add(jTxtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 28, 150, -1));
        jPanel3.add(jPassSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 66, 148, -1));

        jPanel17.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 240, 110));

        jBtCadastrar.setText("Cadastrar");
        jBtCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtCadastrarActionPerformed(evt);
            }
        });
        jPanel17.add(jBtCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, -1, -1));

        jBtLimpar.setText("Limpar Campos");
        jBtLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtLimparActionPerformed(evt);
            }
        });
        jPanel17.add(jBtLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, -1, -1));

        jPanelGerUsuarios.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 690, 180));

        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Id:");
        jPanel18.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Nome:");
        jPanel18.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jTxtAltDelId.setEditable(false);
        jPanel18.add(jTxtAltDelId, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 73, -1));
        jPanel18.add(jTxtAltDelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 187, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("CPF: ");
        jPanel18.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        try {
            jTxtAltDelCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel18.add(jTxtAltDelCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 123, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("E-mail:");
        jPanel18.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));
        jPanel18.add(jTxtAltDelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 188, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Login:");
        jPanel18.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));
        jPanel18.add(jTxtAltDelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 112, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Senha:");
        jPanel18.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));
        jPanel18.add(jTxtAltDelSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 112, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Cargo:");
        jPanel18.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jCbAltDelCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel18.add(jCbAltDelCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 110, -1));

        jBtAlterar.setText("Alterar");
        jBtAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAlterarActionPerformed(evt);
            }
        });
        jPanel18.add(jBtAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        jBtExcluir.setText("Excluir");
        jBtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtExcluirActionPerformed(evt);
            }
        });
        jPanel18.add(jBtExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setText("Realizar pesquisa por:");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        bGPesquisa.add(jRbId);
        jRbId.setText("CPF");
        jPanel1.add(jRbId, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        bGPesquisa.add(jRbLogin);
        jRbLogin.setText("Login");
        jPanel1.add(jRbLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));
        jPanel1.add(jTxtPesqId, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 147, -1));
        jPanel1.add(jTxtPesqLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 141, -1));

        jBtPesquisar.setText("Pesquisar");
        jBtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(jBtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        jPanel18.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 230, 170));

        javax.swing.GroupLayout jPanelGerUsuariosLayout = new javax.swing.GroupLayout(jPanelGerUsuarios);
        jPanelGerUsuarios.setLayout(jPanelGerUsuariosLayout);
        jPanelGerUsuariosLayout.setHorizontalGroup(
            jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                .addGap(259, 259, 259)
                                .addComponent(jLabel13))
                            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                            .addComponent(jBtCadastrar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jBtLimpar))
                                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                .addGap(236, 236, 236)
                                                .addComponent(jLabel8))
                                            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGerUsuariosLayout.createSequentialGroup()
                                                        .addComponent(jLabel9)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                        .addComponent(jLabel10)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jTxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel19)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jCbCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                        .addComponent(jLabel12)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                            .addComponent(jLabel17)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jTxtAltDelId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(153, 153, 153))
                                                        .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                            .addComponent(jLabel15)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jTxtAltDelCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(90, 90, 90)))
                                                    .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jTxtAltDelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                            .addComponent(jLabel16)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jTxtAltDelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(jLabel14))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jLabel25)
                                                            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(jLabel24)))
                                                        .addGap(130, 130, 130))
                                                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                                        .addComponent(jLabel26)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jTxtAltDelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jCbAltDelCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTxtAltDelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGerUsuariosLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jBtAlterar)
                                                .addGap(43, 43, 43)
                                                .addComponent(jBtExcluir)
                                                .addGap(34, 34, 34)))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 69, Short.MAX_VALUE))
                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanelGerUsuariosLayout.setVerticalGroup(
            jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jTxtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jCbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jTxtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtLimpar)
                            .addComponent(jBtCadastrar))))
                .addGap(7, 7, 7)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jTxtAltDelId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jTxtAltDelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jTxtAltDelCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(jTxtAltDelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelGerUsuariosLayout.createSequentialGroup()
                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(jTxtAltDelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(jTxtAltDelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(jCbAltDelCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelGerUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtAlterar)
                            .addComponent(jBtExcluir))))
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jPanelGerUsuarios.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 690, 200));

        jPanelFather.add(jPanelGerUsuarios, "TelaUsuarios");

        jPanelGerCliente.setBackground(new java.awt.Color(204, 204, 204));
        jPanelGerCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Rockwell", 1, 16)); // NOI18N
        jLabel22.setText("Alterar / Deletar Clientes:");
        jPanelGerCliente.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Endereco", "Data", "Email", "Login", "Senha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableClientes);

        jPanelGerCliente.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 690, 118));

        jLabel33.setFont(new java.awt.Font("Rockwell", 1, 16)); // NOI18N
        jLabel33.setText("Cadastro de Clientes:");
        jPanelGerCliente.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));
        jPanelGerCliente.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 230, 10));
        jPanelGerCliente.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 220, 10));

        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNomeGerCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNomeGerCliente.setText("Nome:");
        jPanel19.add(jLabelNomeGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        jPanel19.add(jTxtNomeGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 350, -1));

        jLabelCPFGerCliente1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCPFGerCliente1.setText("CPF: ");
        jPanel19.add(jLabelCPFGerCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        try {
            jTxtCpfGerCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTxtCpfGerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtCpfGerClienteActionPerformed(evt);
            }
        });
        jPanel19.add(jTxtCpfGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 110, -1));

        jLabelEnderecoGerCliente1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEnderecoGerCliente1.setText("Endereço:");
        jPanel19.add(jLabelEnderecoGerCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, -1));
        jPanel19.add(jTxtEnderecoGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 350, -1));

        try {
            jTxtDataNascGerCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel19.add(jTxtDataNascGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 100, -1));

        jLabelEnderecoGerCliente2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEnderecoGerCliente2.setText("Data de Nascimento:");
        jPanel19.add(jLabelEnderecoGerCliente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 120, -1));
        jPanel19.add(jTxtEmailGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 190, -1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("E-mail:");
        jPanel19.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 40, -1));

        jBtCadastrar1.setText("Cadastrar");
        jBtCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtCadastrar1ActionPerformed(evt);
            }
        });
        jPanel19.add(jBtCadastrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, -1, -1));

        jBtLimpar1.setText("Limpar Campos");
        jBtLimpar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtLimpar1ActionPerformed(evt);
            }
        });
        jPanel19.add(jBtLimpar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, -1, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("Login:");
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Senha:");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        jPanel4.add(jTxtLoginGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 145, -1));
        jPanel4.add(jPassSenhaGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 148, -1));

        jPanel19.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 240, 100));

        jPanelGerCliente.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 690, 180));

        jPanel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIdGerClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelIdGerClientes.setText("Id:");
        jPanel20.add(jLabelIdGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabelNomeAlterarGerClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelNomeAlterarGerClientes.setText("Nome:");
        jPanel20.add(jLabelNomeAlterarGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jTxtAltIdGerClientes.setEditable(false);
        jPanel20.add(jTxtAltIdGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 73, -1));
        jPanel20.add(jTxtAltNomeGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 170, -1));

        jLabelAltCPFGerClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelAltCPFGerClientes.setText("CPF: ");
        jPanel20.add(jLabelAltCPFGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        try {
            jTxtAltCpfGerClientes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel20.add(jTxtAltCpfGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 110, -1));

        jLabelAltEnderecoGerCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelAltEnderecoGerCliente.setText("Endereço:");
        jPanel20.add(jLabelAltEnderecoGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 70, -1));
        jPanel20.add(jTxtAltEnderecoGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 360, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("E-mail:");
        jPanel20.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        jPanel20.add(jTxtAltEmailGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 240, -1));
        jPanel20.add(jTxtAltLoginGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 120, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setText("Login:");
        jPanel20.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText("Senha:");
        jPanel20.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        jLabelEnderecoGerCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEnderecoGerCliente.setText("Data de Nascimento:");
        jPanel20.add(jLabelEnderecoGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 120, -1));

        jTxtAltSenhaGerClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtAltSenhaGerClientesActionPerformed(evt);
            }
        });
        jPanel20.add(jTxtAltSenhaGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 120, -1));

        try {
            jTxtAltDataNascGerClientes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel20.add(jTxtAltDataNascGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 120, -1));

        jBtAlterarGerCliente.setText("Alterar");
        jBtAlterarGerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAlterarGerClienteActionPerformed(evt);
            }
        });
        jPanel20.add(jBtAlterarGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        jBtExcluirGerCliente.setText("Excluir");
        jBtExcluirGerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtExcluirGerClienteActionPerformed(evt);
            }
        });
        jPanel20.add(jBtExcluirGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, -1));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setText("Realizar pesquisa por:");
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jRbIdGerCliente.setBackground(new java.awt.Color(204, 204, 204));
        bGPesquisa.add(jRbIdGerCliente);
        jRbIdGerCliente.setText("CPF");
        jPanel2.add(jRbIdGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jRbLoginGerCliente.setBackground(new java.awt.Color(204, 204, 204));
        bGPesquisa.add(jRbLoginGerCliente);
        jRbLoginGerCliente.setText("Login");
        jPanel2.add(jRbLoginGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));
        jPanel2.add(jTxtPesqIdGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 133, -1));

        jTxtPesqLoginGerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtPesqLoginGerClienteActionPerformed(evt);
            }
        });
        jPanel2.add(jTxtPesqLoginGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 133, -1));

        jBtPesquisarGerCliente.setText("Pesquisar");
        jBtPesquisarGerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtPesquisarGerClienteActionPerformed(evt);
            }
        });
        jPanel2.add(jBtPesquisarGerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jPanel20.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 220, 150));

        jPanelGerCliente.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 690, 220));

        jPanelFather.add(jPanelGerCliente, "TelaClientes");

        jPanelGerLivros.setBackground(new java.awt.Color(204, 204, 204));
        jPanelGerLivros.setMaximumSize(new java.awt.Dimension(745, 623));
        jPanelGerLivros.setMinimumSize(new java.awt.Dimension(745, 623));
        jPanelGerLivros.setPreferredSize(new java.awt.Dimension(745, 623));
        jPanelGerLivros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel18.setText("Cadastrar novo Livro");
        jPanelGerLivros.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 46, -1, -1));
        jPanelGerLivros.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));
        jPanelGerLivros.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 390, -1));

        jLabelDataLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDataLivro.setText("Data de Publicação:");
        jPanelGerLivros.add(jLabelDataLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 130, 20));

        jTextFieldNomeLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeLivroActionPerformed(evt);
            }
        });
        jPanelGerLivros.add(jTextFieldNomeLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 87, 270, -1));
        jPanelGerLivros.add(jTextFieldQuantidadeLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 30, -1));

        jLabelQuantidadeLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelQuantidadeLivro.setText("Quantidade:");
        jPanelGerLivros.add(jLabelQuantidadeLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 80, 20));

        jFormattedTextFieldDataLivro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        jPanelGerLivros.add(jFormattedTextFieldDataLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 70, -1));

        jLabelResumoLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelResumoLivro.setText("Resumo:");
        jPanelGerLivros.add(jLabelResumoLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, 30));

        jTextAreaResumoLivro.setColumns(20);
        jTextAreaResumoLivro.setRows(5);
        jScrollPane2.setViewportView(jTextAreaResumoLivro);

        jPanelGerLivros.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 320, 150));

        jLabelNomeLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNomeLivro.setText("Nome:");
        jPanelGerLivros.add(jLabelNomeLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 60, 30));

        jComboBoxAutorLivro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxAutorLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAutorLivroActionPerformed(evt);
            }
        });
        jPanelGerLivros.add(jComboBoxAutorLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 117, 210, -1));

        jLabelAutorLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelAutorLivro.setText("Autor:");
        jPanelGerLivros.add(jLabelAutorLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 40, -1));

        jComboBoxCategoriaLivro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCategoriaLivro.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jComboBoxCategoriaLivroComponentAdded(evt);
            }
        });
        jComboBoxCategoriaLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoriaLivroActionPerformed(evt);
            }
        });
        jPanelGerLivros.add(jComboBoxCategoriaLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 87, 210, -1));

        jLabelQualidadeLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelQualidadeLivro.setText("Qualidade:");
        jPanelGerLivros.add(jLabelQualidadeLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 140, 80, 30));

        jTextFieldQualidadeLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQualidadeLivroActionPerformed(evt);
            }
        });
        jPanelGerLivros.add(jTextFieldQualidadeLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 146, 70, -1));

        jLabelCategoriaLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCategoriaLivro.setText("Categoria:");
        jPanelGerLivros.add(jLabelCategoriaLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 90, 67, -1));

        jButtonCadastrarLivro.setText("Cadastrar");
        jButtonCadastrarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarLivroActionPerformed(evt);
            }
        });
        jPanelGerLivros.add(jButtonCadastrarLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 100, -1));

        jButtonLimparCamposLivro.setText("Limpar");
        jButtonLimparCamposLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparCamposLivroActionPerformed(evt);
            }
        });
        jPanelGerLivros.add(jButtonLimparCamposLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 100, -1));

        jPanelCapaLivro.setBackground(new java.awt.Color(211, 211, 211));

        jLabelImagemCapa.setBackground(new java.awt.Color(188, 188, 188));
        jLabelImagemCapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelImagemCapaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelCapaLivroLayout = new javax.swing.GroupLayout(jPanelCapaLivro);
        jPanelCapaLivro.setLayout(jPanelCapaLivroLayout);
        jPanelCapaLivroLayout.setHorizontalGroup(
            jPanelCapaLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCapaLivroLayout.createSequentialGroup()
                .addComponent(jLabelImagemCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelCapaLivroLayout.setVerticalGroup(
            jPanelCapaLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCapaLivroLayout.createSequentialGroup()
                .addComponent(jLabelImagemCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelGerLivros.add(jPanelCapaLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 105, 150));

        jButtonAddCapa.setText("Add Capa");
        jButtonAddCapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCapaActionPerformed(evt);
            }
        });
        jPanelGerLivros.add(jButtonAddCapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 146, 110, 22));

        jLabelGerenciarCategorias.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabelGerenciarCategorias.setText("Gerenciar Categorias");
        jPanelGerLivros.add(jLabelGerenciarCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));
        jPanelGerLivros.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 330, -1));

        jPaneCadastrarCat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPaneCadastrarCat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNomeAutLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNomeAutLivro.setText("Nome:");
        jPaneCadastrarCat.add(jLabelNomeAutLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 50, 40));
        jPaneCadastrarCat.add(jTextFieldNomeAutLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 120, -1));

        jButtonAddNovoAutLivro.setText("Adicionar Novo");
        jButtonAddNovoAutLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddNovoAutLivroActionPerformed(evt);
            }
        });
        jPaneCadastrarCat.add(jButtonAddNovoAutLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 140, -1));

        jButtonRemoverAutLivro.setText("Remover Selecionado");
        jButtonRemoverAutLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverAutLivroActionPerformed(evt);
            }
        });
        jPaneCadastrarCat.add(jButtonRemoverAutLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 143, 170, -1));

        jTableAutoresLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jTableAutoresLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAutoresLivrosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableAutoresLivros);

        jPaneCadastrarCat.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 110));

        jButtonAlterarAutLivro.setText("Alterar");
        jButtonAlterarAutLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarAutLivroActionPerformed(evt);
            }
        });
        jPaneCadastrarCat.add(jButtonAlterarAutLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 143, 140, -1));

        jTextFieldSelAutNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSelAutNomeActionPerformed(evt);
            }
        });
        jPaneCadastrarCat.add(jTextFieldSelAutNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 115, 180, -1));

        jTextFieldSelAutID.setEditable(false);
        jPaneCadastrarCat.add(jTextFieldSelAutID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, 130, -1));

        jPanelGerLivros.add(jPaneCadastrarCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 340, 220));

        jLabelCadastrarAutoresLivro.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabelCadastrarAutoresLivro.setText("Cadastrar novo Autor");
        jPanelGerLivros.add(jLabelCadastrarAutoresLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, -1, -1));
        jPanelGerLivros.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 330, -1));

        jPanelCadastrarAut.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanelCadastrarAut.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNomeCatLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNomeCatLivro.setText("Nome:");
        jPanelCadastrarAut.add(jLabelNomeCatLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 50, 40));

        jButtonRemoverCatLivro.setText("Remover Selecionada");
        jButtonRemoverCatLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverCatLivroActionPerformed(evt);
            }
        });
        jPanelCadastrarAut.add(jButtonRemoverCatLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 143, 160, -1));

        jTableCategoriasLivro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jTableCategoriasLivro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCategoriasLivroMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableCategoriasLivro);
        if (jTableCategoriasLivro.getColumnModel().getColumnCount() > 0) {
            jTableCategoriasLivro.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jPanelCadastrarAut.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 110));

        jButtonAddNovaCatLivro.setText("Adicionar Nova ");
        jButtonAddNovaCatLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddNovaCatLivroActionPerformed(evt);
            }
        });
        jPanelCadastrarAut.add(jButtonAddNovaCatLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 140, -1));

        jButtonAlterarCatLivro.setText("Alterar");
        jButtonAlterarCatLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarCatLivroActionPerformed(evt);
            }
        });
        jPanelCadastrarAut.add(jButtonAlterarCatLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 143, 140, -1));
        jPanelCadastrarAut.add(jTextFieldNovaCatLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 110, -1));

        jTextFieldSelCatID.setEditable(false);
        jPanelCadastrarAut.add(jTextFieldSelCatID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, 120, -1));
        jPanelCadastrarAut.add(jTextFieldSelCatNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 115, 180, -1));

        jPanelGerLivros.add(jPanelCadastrarAut, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 330, 220));

        jPanel15.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Controle de Livros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 150, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("pressionando o botão Abaixo:");
        jPanel15.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, 200, 20));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Para Visualizar, Alterar ou Remover");
        jPanel15.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 2, 200, 20));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Os livros, acesse o Menu de livros ");
        jPanel15.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 22, 200, 20));

        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(471, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(128, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelGerLivros.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 700, 250));

        jPanelFather.add(jPanelGerLivros, "TelaLivros");

        jPanelGerAlugueis.setBackground(new java.awt.Color(204, 204, 204));
        jPanelGerAlugueis.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());


        javax.swing.GroupLayout jPanelGerAlugueisLayout = new javax.swing.GroupLayout(jPanelGerAlugueis);
        jPanelGerAlugueis.setLayout(jPanelGerAlugueisLayout);
        jPanelGerAlugueisLayout.setHorizontalGroup(
            jPanelGerAlugueisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanelGerAlugueisLayout.setVerticalGroup(
            jPanelGerAlugueisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );

        jLabel34.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel34.setText("Selecionar Livro:");
        jPanelGerAlugueis.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));
        jPanelGerAlugueis.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 200, 10));

        jLabel35.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel35.setText("Novo Aluguel:");
        jPanelGerAlugueis.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jTableAlugLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome Livro", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAlugLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlugLivrosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableAlugLivros);

        jPanelGerAlugueis.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 330, 100));

        jLabel40.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel40.setText("Selecionar Cliente:");
        jPanelGerAlugueis.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jTableAlugClt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Clientes", "Nº Aluguéis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAlugClt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlugCltMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTableAlugClt);

        jPanelGerAlugueis.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 300, 100));

        jBtReprovar.setText("Reprovar");
        jBtReprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtReprovarActionPerformed(evt);
            }
        });
        jPanelGerAlugueis.add(jBtReprovar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 580, 100, -1));

        jBtAprovar.setText("Aprovar");
        jBtAprovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAprovarActionPerformed(evt);
            }
        });
        jPanelGerAlugueis.add(jBtAprovar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 580, 100, -1));
        jPanelGerAlugueis.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 260, 10));

        jLabel42.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel42.setText("Pedidos Pendentes:");
        jPanelGerAlugueis.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Cliente:");
        jPanel15.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel1.setText("CPF:");
        jPanel15.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jTxtAlugCliente.setEditable(false);
        jPanel15.add(jTxtAlugCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 170, -1));

        jTxtAlugCpf.setEditable(false);
        jPanel15.add(jTxtAlugCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 170, -1));

        jLabel6.setText("Livro:");
        jPanel15.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        jTxtAlugLivro.setEditable(false);
        jPanel15.add(jTxtAlugLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 200, -1));

        jLabel5.setText("Disponíveis:");
        jPanel15.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        jTxtAlugQntd.setEditable(false);
        jPanel15.add(jTxtAlugQntd, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 170, -1));

        jLabel4.setText("Data Aluguel:");
        jPanel15.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        jTxtAlugData.setEditable(false);
        jPanel15.add(jTxtAlugData, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 170, -1));

        jBtAlugar.setText("Alugar");
        jBtAlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtAlugarActionPerformed(evt);
            }
        });
        jPanel15.add(jBtAlugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 80, -1));

        jBtLimpDadosAlug.setText("Limpar");
        jBtLimpDadosAlug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtLimpDadosAlugActionPerformed(evt);
            }
        });
        jPanel15.add(jBtLimpDadosAlug, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 80, -1));

        jLabel43.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel43.setText("Dados do Pedido:");
        jPanel15.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanelGerAlugueis.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 660, 140));

        jTablePedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pedido", "Cliente", "CPF", "Qntd Alugados", "Livro", "Qntd Disponível", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePedidosMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTablePedidos);

        jPanelGerAlugueis.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 660, 160));

        jPanelFather.add(jPanelGerAlugueis, "TelaAlugueis");

        jPanelAnuncios.setBackground(new java.awt.Color(204, 204, 204));
        jPanelAnuncios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelAnuncios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelGerenciarCategorias3.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabelGerenciarCategorias3.setText("Defina os Recomendados:");
        jPanel9.add(jLabelGerenciarCategorias3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel9.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 250, 10));

        jTableRecomendados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jTableRecomendados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRecomendadosMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTableRecomendados);
        if (jTableRecomendados.getColumnModel().getColumnCount() > 0) {
            jTableRecomendados.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jPanel9.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 240, 150));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel48.setText("Pesquisa:");
        jPanel9.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        jButton5.setText("Retirar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 77, -1));

        jButton6.setText("Adicionar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        jLabel49.setText("<<");
        jPanel9.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, 20));

        jLabel50.setText(">>");
        jPanel9.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, 20));

        jLabel51.setText(">>");
        jPanel9.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, 20));

        jLabel52.setText("<<");
        jPanel9.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, 20));

        jTablePesquisaReco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Autor"
            }
        ));
        jTablePesquisaReco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePesquisaRecoMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTablePesquisaReco);
        if (jTablePesquisaReco.getColumnModel().getColumnCount() > 0) {
            jTablePesquisaReco.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jPanel9.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 270, 120));

        jTextFieldPesquisaReco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisaRecoActionPerformed(evt);
            }
        });
        jPanel9.add(jTextFieldPesquisaReco, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 41, 120, -1));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setText("Livros Mostrados Atualmente:");
        jPanel9.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));
        jPanel9.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 250, 10));

        jButtonPesquisaReco.setText("Pesquisar");
        jButtonPesquisaReco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisaRecoActionPerformed(evt);
            }
        });
        jPanel9.add(jButtonPesquisaReco, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 90, -1));

        jPanelAnuncios.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 680, 200));

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelGerenciarCategorias4.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabelGerenciarCategorias4.setText("Defina os Lançamentos:");
        jPanel10.add(jLabelGerenciarCategorias4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel10.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 250, 10));

        jTableLancamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jTableLancamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLancamentosMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTableLancamentos);
        if (jTableLancamentos.getColumnModel().getColumnCount() > 0) {
            jTableLancamentos.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jPanel10.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 240, 150));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText("Pesquisa:");
        jPanel10.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        jButton7.setText("Retirar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 77, -1));

        jButton8.setText("Adicionar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        jLabel55.setText("<<");
        jPanel10.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, 20));

        jLabel56.setText(">>");
        jPanel10.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, 20));

        jLabel57.setText(">>");
        jPanel10.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, 20));

        jLabel58.setText("<<");
        jPanel10.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, 20));

        jTablePesquisaLanca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Autor"
            }
        ));
        jTablePesquisaLanca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePesquisaLancaMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(jTablePesquisaLanca);
        if (jTablePesquisaLanca.getColumnModel().getColumnCount() > 0) {
            jTablePesquisaLanca.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jPanel10.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 270, 120));

        jTextFieldPesquisaLanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisaLancaActionPerformed(evt);
            }
        });
        jPanel10.add(jTextFieldPesquisaLanca, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 41, 120, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel59.setText("Livros Mostrados Atualmente:");
        jPanel10.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jButtonPesquisarLanca.setText("Pesquisar");
        jButtonPesquisarLanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarLancaActionPerformed(evt);
            }
        });
        jPanel10.add(jButtonPesquisarLanca, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 90, -1));

        jPanelAnuncios.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 680, 200));

        jLabelGerenciarCategorias5.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabelGerenciarCategorias5.setText("Defina quais Livros estarão na Página Inicial do Cliente:");
        jPanelAnuncios.add(jLabelGerenciarCategorias5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));
        jPanelAnuncios.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 570, 10));

        jPanelFather.add(jPanelAnuncios, "TelaAnuncios");

        getContentPane().add(jPanelFather, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 0, 745, 623));

        SideMenu.setBackground(new java.awt.Color(67, 67, 67));
        SideMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SideMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtGerAnuncios.setText("Gerenciar Anúncios");
        jBtGerAnuncios.setFocusable(false);
        jBtGerAnuncios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtGerAnunciosActionPerformed(evt);
            }
        });
        SideMenu.add(jBtGerAnuncios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 240, 32));

        jBtGerClientes.setText("Gerenciar Clientes");
        jBtGerClientes.setFocusable(false);
        jBtGerClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtGerClientesActionPerformed(evt);
            }
        });
        SideMenu.add(jBtGerClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 240, 32));

        jBtGerLivros.setText("Gerenciar Livros");
        jBtGerLivros.setFocusable(false);
        jBtGerLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtGerLivrosActionPerformed(evt);
            }
        });
        SideMenu.add(jBtGerLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 240, 32));

        jBtGerAlugueis.setText("Gerenciar Aluguéis");
        jBtGerAlugueis.setFocusable(false);
        jBtGerAlugueis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtGerAlugueisActionPerformed(evt);
            }
        });
        SideMenu.add(jBtGerAlugueis, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 240, 32));

        jBtHome.setText("Home");
        jBtHome.setFocusable(false);
        jBtHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtHomeActionPerformed(evt);
            }
        });
        SideMenu.add(jBtHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 240, 32));

        jBtLogOut.setText("LogOut");
        jBtLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtLogOutActionPerformed(evt);
            }
        });
        SideMenu.add(jBtLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 560, 85, -1));

        jBtGerUsuarios1.setText("Gerenciar Usuários");
        jBtGerUsuarios1.setFocusable(false);
        jBtGerUsuarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtGerUsuarios1ActionPerformed(evt);
            }
        });
        SideMenu.add(jBtGerUsuarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 240, 32));

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo.png"))); // NOI18N
        SideMenu.add(jLabelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 210, 180));

        jLabelCategoria.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabelCategoria.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCategoria.setText("Ferramentas");
        SideMenu.add(jLabelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, 30));
        SideMenu.add(jSeparatorCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 240, 10));

        getContentPane().add(SideMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 623));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Quando clicado no Botão Gerenciar Clientes, chama a TelaClientes
    private void jBtGerClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtGerClientesActionPerformed
        CardLayout cl = (CardLayout) jPanelFather.getLayout();
        cl.show(jPanelFather, "TelaClientes");
    }//GEN-LAST:event_jBtGerClientesActionPerformed

    
    private void jBtGerAnunciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtGerAnunciosActionPerformed
        CardLayout cl = (CardLayout) jPanelFather.getLayout();
        cl.show(jPanelFather, "TelaAnuncios");
    }//GEN-LAST:event_jBtGerAnunciosActionPerformed

   
    //Quando clicado no Botão Gerenciar Livros, chama a TelaLivros
    private void jBtGerLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtGerLivrosActionPerformed
        CardLayout cl = (CardLayout) jPanelFather.getLayout();
        cl.show(jPanelFather, "TelaLivros");
    }//GEN-LAST:event_jBtGerLivrosActionPerformed
    
    //Quando clicado no Botão Gerenciar Alugueis, chama a TelaAlugueis
    private void jBtGerAlugueisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtGerAlugueisActionPerformed
        CardLayout cl = (CardLayout) jPanelFather.getLayout();
        cl.show(jPanelFather, "TelaAlugueis");
    }//GEN-LAST:event_jBtGerAlugueisActionPerformed

    //Quando clicado no Botão Home, chama a TelaInicial
    private void jBtHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtHomeActionPerformed
        CardLayout cl = (CardLayout) jPanelFather.getLayout();
        cl.show(jPanelFather, "TelaInicial");
    }//GEN-LAST:event_jBtHomeActionPerformed

    private void jBtLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtLogOutActionPerformed
        Login lg = new Login();
        lg.show();
        lg.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jBtLogOutActionPerformed

    //Exclusão de Usuário
    private void jBtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtExcluirActionPerformed
        Usuario us = new Usuario();
        us.setId_usuario(Integer.parseInt(jTxtAltDelId.getText()));

        UsuarioDAO usDAO = new UsuarioDAO();
        usDAO.deletar(us);
        JOptionPane.showMessageDialog(null, "Usuário deletado");

        limparCamposAltDel();
        listarTabela();
    }//GEN-LAST:event_jBtExcluirActionPerformed

    //Alteração de Cadastro de Usuário
    private void jBtAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAlterarActionPerformed
        
        Usuario us = new Usuario();
        us.setId_usuario(Integer.parseInt(jTxtAltDelId.getText()));
        us.setNome(jTxtAltDelNome.getText());
        us.setCpf(jTxtAltDelCpf.getText());
        us.setEmail(jTxtAltDelEmail.getText());
        us.setLogin(jTxtAltDelLogin.getText());
        us.setSenha(jTxtAltDelSenha.getText());
        us.setCargo(String.valueOf(jCbAltDelCargo.getSelectedItem()));

        UsuarioDAO usDAO = new UsuarioDAO();
        usDAO.alterar(us);
        JOptionPane.showMessageDialog(null, "Usuário alterado!");

        limparCamposAltDel();
        listarTabela();
    }//GEN-LAST:event_jBtAlterarActionPerformed

    //Quando clicado sobre um Usuário na tabela, seus dados são inseridos nos jTextField correspondente
    private void jTableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableUsuariosMouseClicked
        jTxtAltDelId.setText((String) jTableUsuarios.getModel().getValueAt(jTableUsuarios.getSelectedRow(), 0));
        jTxtAltDelNome.setText((String) jTableUsuarios.getModel().getValueAt(jTableUsuarios.getSelectedRow(), 1));
        jTxtAltDelCpf.setText((String) jTableUsuarios.getModel().getValueAt(jTableUsuarios.getSelectedRow(), 2));
        jTxtAltDelEmail.setText((String) jTableUsuarios.getModel().getValueAt(jTableUsuarios.getSelectedRow(), 3));
        jTxtAltDelLogin.setText((String) jTableUsuarios.getModel().getValueAt(jTableUsuarios.getSelectedRow(), 4));
        jTxtAltDelSenha.setText((String) jTableUsuarios.getModel().getValueAt(jTableUsuarios.getSelectedRow(), 5));
        jCbAltDelCargo.setSelectedItem((String) jTableUsuarios.getModel().getValueAt(jTableUsuarios.getSelectedRow(), 6));
    }//GEN-LAST:event_jTableUsuariosMouseClicked

    private void jTxtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCpfActionPerformed

    //Limpar campos de Cadastro de Usuários
    private void jBtLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtLimparActionPerformed
        limparCamposCad();
    }//GEN-LAST:event_jBtLimparActionPerformed

    //Cadastro de Usuário
    private void jBtCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtCadastrarActionPerformed
        if(jTxtNome.getText().isEmpty() || jTxtCpf.getText().isEmpty() || jTxtLogin.getText().isEmpty() || jPassSenha.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Campos faltando, Você precisa preencher os Campos essenciais!");
        }
        else{
        Usuario us = new Usuario();
        us.setNome(jTxtNome.getText());
        us.setCpf(jTxtCpf.getText());
        us.setEmail(jTxtEmail.getText());
        us.setLogin(jTxtLogin.getText());
        us.setSenha(jPassSenha.getText());
        us.setCargo(String.valueOf(jCbCargo.getSelectedItem()));

        UsuarioDAO usDAO = new UsuarioDAO();
        usDAO.salvar(us);
        JOptionPane.showMessageDialog(null, "Usuario "+us.getNome()+" cadastrado!");

        limparCamposCad();
        listarTabela();
        }
    }//GEN-LAST:event_jBtCadastrarActionPerformed

    private void jBtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtPesquisarActionPerformed
        UsuarioDAO usDAO = new UsuarioDAO();
        List<Usuario> usuarios = null;
        
        if(jRbId.isSelected()){
            usuarios = usDAO.pesquisa(1, jTxtPesqId.getText());
        } else if(jRbLogin.isSelected()){
            usuarios = usDAO.pesquisa(2, jTxtPesqLogin.getText());
        }
        
        DefaultTableModel valor = (DefaultTableModel) jTableUsuarios.getModel();
        valor.getDataVector().removeAllElements();

        int i = 0;
        while (usuarios.size() > i){
        
            valor.addRow(new Object[]{String.valueOf(usuarios.get(i).getId_usuario()), usuarios.get(i).getNome(),
            usuarios.get(i).getCpf(), usuarios.get(i).getEmail(), usuarios.get(i).getLogin(), usuarios.get(i).getSenha(),
            usuarios.get(i).getCargo()});
            i++;
        }
    }//GEN-LAST:event_jBtPesquisarActionPerformed

    private void jComboBoxAutorLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAutorLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAutorLivroActionPerformed

    private void jComboBoxCategoriaLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaLivroActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBoxCategoriaLivroActionPerformed

    private void jTextFieldQualidadeLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQualidadeLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQualidadeLivroActionPerformed
    String capaDir;
    private void jButtonAddCapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCapaActionPerformed
        
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        int res = fc.showOpenDialog(null);

        if (res == fc.APPROVE_OPTION) {
            File arquivo = fc.getSelectedFile();

            try {
                BufferedImage imagemBanco = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 210, 260);
                BufferedImage imagemPreview = ManipularImagem.setImagemDimensao(arquivo.getAbsolutePath(), 160, 160);

                jLabelImagemCapa.setIcon(new ImageIcon(imagemPreview));
                this.selectedImage = imagemBanco;

            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "Erro: "+ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
            jLabelImagemCapa.setIcon(null);
            this.selectedImage = null;
        }

        
    }//GEN-LAST:event_jButtonAddCapaActionPerformed
        
    private void jTableCategoriasLivroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCategoriasLivroMouseClicked
        jTextFieldSelCatID.setText((String) jTableCategoriasLivro.getModel().getValueAt(jTableCategoriasLivro.getSelectedRow(), 0));
        jTextFieldSelCatNome.setText((String) jTableCategoriasLivro.getModel().getValueAt(jTableCategoriasLivro.getSelectedRow(), 1));
    }//GEN-LAST:event_jTableCategoriasLivroMouseClicked

    private void jButtonRemoverCatLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverCatLivroActionPerformed
        CategoriaLivro categoria = new CategoriaLivro();
        categoria.setNome(jTextFieldSelCatNome.getText());
        categoria.setId_Categoria(parseInt(jTextFieldSelCatID.getText()));
        if (categoria.getNome() != null){

         CategoriaDAO categoriaDAO = new CategoriaDAO();
         categoriaDAO.deletar(categoria);

         JOptionPane.showMessageDialog(null, "Categoria "+categoria.getNome()+" Deletada!");
         jTextFieldSelCatNome.setText("");
         jTextFieldSelCatID.setText("");
         listarTabelaCategorias();
        }
    }//GEN-LAST:event_jButtonRemoverCatLivroActionPerformed

    private void jButtonAddNovaCatLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddNovaCatLivroActionPerformed
       try{
           CategoriaLivro categoria = new CategoriaLivro();
           categoria.setNome(jTextFieldNovaCatLivro.getText());
           if (categoria.getNome() != null){
                
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.salvar(categoria);
            
            JOptionPane.showMessageDialog(null, "Categoria "+categoria.getNome()+" cadastrada!");
            jTextFieldNovaCatLivro.setText("");
            listarTabelaCategorias();
           }else {
               JOptionPane.showMessageDialog(null, "Valor Inválido!");
           
           }
           
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       } 
    }//GEN-LAST:event_jButtonAddNovaCatLivroActionPerformed

    private void jButtonAddNovoAutLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddNovoAutLivroActionPerformed
        try{
           AutorLivro autor = new AutorLivro();
           autor.setNome(jTextFieldNomeAutLivro.getText());
           if (autor.getNome() != null){
        
            AutorDAO autorDAO = new AutorDAO();
            autorDAO.salvar(autor);
            
            JOptionPane.showMessageDialog(null, "Autor "+autor.getNome()+" cadastrado!");
            this.listarTabelaAutores();
            jTextFieldNomeAutLivro.setText("");
           } else {
               JOptionPane.showMessageDialog(null, "Valor Inválido!");
           
           }
           
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);} 
    }//GEN-LAST:event_jButtonAddNovoAutLivroActionPerformed

    private void jButtonRemoverAutLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverAutLivroActionPerformed
        AutorLivro autor = new AutorLivro();
        autor.setNome(jTextFieldSelAutNome.getText());
        autor.setId_Autor(parseInt(jTextFieldSelAutID.getText()));
        if (autor.getNome() != null){

         AutorDAO autorDAO = new AutorDAO();
         autorDAO.deletar(autor);

         JOptionPane.showMessageDialog(null, "Autor "+autor.getNome()+" Deletado!");
         jTextFieldSelAutNome.setText("");
         jTextFieldSelAutID.setText("");
         listarTabelaAutores();
        }
    }//GEN-LAST:event_jButtonRemoverAutLivroActionPerformed

    private void jTableAutoresLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAutoresLivrosMouseClicked
        jTextFieldSelAutID.setText((String) jTableAutoresLivros.getModel().getValueAt(jTableAutoresLivros.getSelectedRow(), 0));
        jTextFieldSelAutNome.setText((String) jTableAutoresLivros.getModel().getValueAt(jTableAutoresLivros.getSelectedRow(), 1));
    }//GEN-LAST:event_jTableAutoresLivrosMouseClicked

    private void jBtCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtCadastrar1ActionPerformed
        Cliente clt = new Cliente();
        clt.setNome(jTxtNomeGerCliente.getText());
        clt.setCpf(jTxtCpfGerCliente.getText());
        clt.setEmail(jTxtEmailGerCliente.getText());
        clt.setData_nasc(jTxtDataNascGerCliente.getText());
        clt.setEndereco(jTxtEnderecoGerCliente.getText());
        clt.setLogin(jTxtLoginGerCliente.getText());
        clt.setSenha(jPassSenhaGerCliente.getText());
        clt.setQntd_livros_alugados(0);
        
        Usuario us = new Usuario();
        us.setId_usuario(sessao.getId_usuario());
        
        ClienteDAO cltDAO = new ClienteDAO();
        cltDAO.salvar(clt, us);
        
        limparCamposCadCliente();
        listarTabelaCliente();
        listarTabelaAlugClt();
    }//GEN-LAST:event_jBtCadastrar1ActionPerformed

    private void jBtLimpar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtLimpar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtLimpar1ActionPerformed

    private void jTxtCpfGerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtCpfGerClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtCpfGerClienteActionPerformed

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        jTxtAltIdGerClientes.setText((String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 0));
        jTxtAltNomeGerClientes.setText((String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 1));
        jTxtAltCpfGerClientes.setText((String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 2));
        jTxtAltEnderecoGerClientes.setText((String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 3));
        jTxtAltDataNascGerClientes.setText((String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 4));
        jTxtAltEmailGerClientes.setText((String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 5));
        jTxtAltLoginGerClientes.setText((String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 6));
        jTxtAltSenhaGerClientes.setText((String) jTableClientes.getModel().getValueAt(jTableClientes.getSelectedRow(), 7));
    }//GEN-LAST:event_jTableClientesMouseClicked

    private void jBtPesquisarGerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtPesquisarGerClienteActionPerformed
        ClienteDAO cltDAO = new ClienteDAO();
        List<Cliente> clientes = null;
        
        if(jRbIdGerCliente.isSelected()){
            clientes = cltDAO.pesquisa(1, jTxtPesqIdGerCliente.getText());
        } else if(jRbLoginGerCliente.isSelected()){
            clientes = cltDAO.pesquisa(2, jTxtPesqLoginGerCliente.getText());
        }
        
        DefaultTableModel valor = (DefaultTableModel) jTableClientes.getModel();
        valor.getDataVector().removeAllElements();

        int i = 0;
        while (clientes.size() > i){
            valor.addRow(new Object[]{String.valueOf(clientes.get(i).getId_cliente()), clientes.get(i).getNome(),
            clientes.get(i).getCpf(), clientes.get(i).getEndereco(), clientes.get(i).getData_nasc(),
            clientes.get(i).getEmail(), clientes.get(i).getLogin(), clientes.get(i).getSenha()});
            i++;
        }
    }//GEN-LAST:event_jBtPesquisarGerClienteActionPerformed

    private void jBtAlterarGerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAlterarGerClienteActionPerformed
        Cliente clt = new Cliente();
        clt.setId_cliente(Integer.parseInt(jTxtAltIdGerClientes.getText()));
        clt.setNome(jTxtAltNomeGerClientes.getText());
        clt.setCpf(jTxtAltCpfGerClientes.getText());
        clt.setEmail(jTxtAltEmailGerClientes.getText());
        clt.setData_nasc(jTxtAltDataNascGerClientes.getText());
        clt.setEndereco(jTxtAltEnderecoGerClientes.getText());
        clt.setLogin(jTxtAltLoginGerClientes.getText());
        clt.setSenha(jTxtAltSenhaGerClientes.getText());
        
        ClienteDAO cltDAO = new ClienteDAO();
        cltDAO.alterar(clt);
        
        limparCamposAltClientes();
        listarTabelaCliente();
        listarTabelaAlugClt();
        
    }//GEN-LAST:event_jBtAlterarGerClienteActionPerformed

    private void jBtExcluirGerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtExcluirGerClienteActionPerformed
        Cliente clt = new Cliente();
        clt.setId_cliente(Integer.parseInt(jTxtAltIdGerClientes.getText()));
        
        ClienteDAO cltDAO = new ClienteDAO();
        cltDAO.deletar(clt);
        
        limparCamposAltClientes();
        listarTabelaCliente();
        listarTabelaAlugClt();
    }//GEN-LAST:event_jBtExcluirGerClienteActionPerformed

    private void jTxtAltSenhaGerClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtAltSenhaGerClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtAltSenhaGerClientesActionPerformed

    private void jTxtPesqLoginGerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtPesqLoginGerClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtPesqLoginGerClienteActionPerformed
    
    //Quando clicado no Botão Gerenciar Usuarios, chama a TelaUsuarios
    private void jBtGerUsuarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtGerUsuarios1ActionPerformed
        CardLayout cl = (CardLayout) jPanelFather.getLayout();
        cl.show(jPanelFather, "TelaUsuarios");
    }//GEN-LAST:event_jBtGerUsuarios1ActionPerformed

    private void jTableRecomendadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRecomendadosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableRecomendadosMouseClicked

    private void jTablePesquisaRecoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePesquisaRecoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTablePesquisaRecoMouseClicked

    private void jTextFieldPesquisaRecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaRecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPesquisaRecoActionPerformed

    private void jTableLancamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLancamentosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableLancamentosMouseClicked

    private void jTablePesquisaLancaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePesquisaLancaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTablePesquisaLancaMouseClicked

    private void jTextFieldPesquisaLancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaLancaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPesquisaLancaActionPerformed

    private void jLabelImagemCapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImagemCapaMouseClicked
        
        javax.swing.ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource(capaDir)); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image imgEmEscala = image.getScaledInstance(105, 150,  java.awt.Image.SCALE_SMOOTH);
        jLabelImagemCapa.setIcon(new javax.swing.ImageIcon(imgEmEscala));
    }//GEN-LAST:event_jLabelImagemCapaMouseClicked

    private void jButtonCadastrarLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarLivroActionPerformed
        
        if(jTextFieldNomeLivro.getText().isEmpty() || jTextFieldQuantidadeLivro.getText().isEmpty()
                || this.selectedImage == null){
            
            JOptionPane.showMessageDialog(null, "Negado! Faltam Campos Essenciais a Serem Preenchidos!");
        
        }else{
        try{
        Livro livro = new biblioteca.Livro();
        livro.setNome(jTextFieldNomeLivro.getText());
        livro.setQuantidade( parseInt(jTextFieldQuantidadeLivro.getText()));
        livro.setData(jFormattedTextFieldDataLivro.getText());
        livro.setQualidade(jTextFieldQualidadeLivro.getText());
        livro.setAlugados(0);
        livro.setResumo(jTextAreaResumoLivro.getText());
        
        livro.setCapa(ManipularImagem.getImgBytes(this.selectedImage));
        livro.setCategoriaID(idsCat.get(jComboBoxCategoriaLivro.getSelectedIndex()));
        livro.setAutorID(idsAut.get(jComboBoxAutorLivro.getSelectedIndex()));
        
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.salvar(livro);
        
        JOptionPane.showMessageDialog(null, "Livro "+livro.getNome()+" Cadastrado!");
        
        listarTabelaAlugLivro();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+"AreaUsuario");
        }
        }
    }//GEN-LAST:event_jButtonCadastrarLivroActionPerformed

    private void jTextFieldNomeLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeLivroActionPerformed
    

    private void jButtonAlterarCatLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarCatLivroActionPerformed
        
        CategoriaLivro categoria = new CategoriaLivro();
        categoria.setNome(jTextFieldSelCatNome.getText());
        categoria.setId_Categoria(parseInt(jTextFieldSelCatID.getText()));
        if (categoria.getNome() != null){

         CategoriaDAO categoriaDAO = new CategoriaDAO();
         categoriaDAO.alterar(categoria);

         JOptionPane.showMessageDialog(null, "Categoria "+categoria.getNome()+" Alterada!");
         jTextFieldSelCatNome.setText("");
         jTextFieldSelCatID.setText("");
         listarTabelaCategorias();
        }
    }//GEN-LAST:event_jButtonAlterarCatLivroActionPerformed

    private void jButtonAlterarAutLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarAutLivroActionPerformed
        AutorLivro autor = new AutorLivro();
        autor.setNome(jTextFieldSelAutNome.getText());
        autor.setId_Autor(parseInt(jTextFieldSelAutID.getText()));
        if (autor.getNome() != null){

         AutorDAO autorDAO = new AutorDAO();
         autorDAO.alterar(autor);

         JOptionPane.showMessageDialog(null, "Autor "+autor.getNome()+" Alterado!");
         jTextFieldSelAutNome.setText("");
         jTextFieldSelAutID.setText("");
         listarTabelaAutores();
        }   
    
    }//GEN-LAST:event_jButtonAlterarAutLivroActionPerformed

    private void jTextFieldSelAutNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSelAutNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSelAutNomeActionPerformed

    private void jTableAlugCltMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlugCltMouseClicked
        Cliente clt = new Cliente();
        ClienteDAO cltDAO= new ClienteDAO();
        
        clt.setId_cliente(Integer.parseInt((String) jTableAlugClt.getModel().getValueAt(jTableAlugClt.getSelectedRow(), 0)));
        cltDAO.dadosClientesId(clt);
        Cliente cltSelecionado = clt;
        
        jTxtAlugCliente.setText(cltSelecionado.getNome());
        jTxtAlugCpf.setText(cltSelecionado.getCpf());
    }//GEN-LAST:event_jTableAlugCltMouseClicked

    private void jTableAlugLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlugLivrosMouseClicked
        jTxtAlugLivro.setText((String) jTableAlugLivros.getModel().getValueAt(jTableAlugLivros.getSelectedRow(), 1));
        jTxtAlugQntd.setText((String) jTableAlugLivros.getModel().getValueAt(jTableAlugLivros.getSelectedRow(), 2));
        jTxtAlugData.setText(String.valueOf(getDataAtual()));
    }//GEN-LAST:event_jTableAlugLivrosMouseClicked

    private void jBtLimpDadosAlugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtLimpDadosAlugActionPerformed
        jTxtAlugCliente.setText("");
        jTxtAlugCpf.setText("");
        jTxtAlugLivro.setText("");
        jTxtAlugQntd.setText("");
        jTxtAlugData.setText("");
    }//GEN-LAST:event_jBtLimpDadosAlugActionPerformed

    private void jBtAlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAlugarActionPerformed
        Aluguel alg = new Aluguel();
        Cliente clt = new Cliente();
        Livro liv = new Livro();
        
        alg.setData(jTxtAlugData.getText());
        alg.setStatus("Alugado");
        clt.setNome(jTxtAlugCliente.getText());
        clt.setCpf(jTxtAlugCpf.getText());
        liv.setNome(jTxtAlugLivro.getText());
        
        AluguelDAO algDAO = new AluguelDAO();
        ClienteDAO cltDAO = new ClienteDAO();
        LivroDAO livDAO = new LivroDAO();
        
        livDAO.dadosLivroAlug(liv); //Pega todos os dados do livro (utilizando o Nome do Livro)
        cltDAO.dadosClientesCpf(clt); //Pega todos os dados do cliente (utilizando CPF)
        
        livSessao = liv;
        cltSessao = clt;
        
        algDAO.alugar(alg, cltSessao, livSessao, sessao);
        livDAO.alugaLivro(livSessao);
        cltDAO.alugaLivroClt(cltSessao);
        
        JOptionPane.showMessageDialog(null, "Aluguel realizado com sucesso");
        
        listarTabelaCliente();
        listarTabelaAlugClt();
        listarTabelaAlugLivro();
    }//GEN-LAST:event_jBtAlugarActionPerformed

    private void jTablePedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePedidosMouseClicked
        id_selec = Integer.parseInt((String) jTablePedidos.getModel().getValueAt(jTablePedidos.getSelectedRow(), 0));
        clt_selec = (String) jTablePedidos.getModel().getValueAt(jTablePedidos.getSelectedRow(), 2);
        liv_selec = (String) jTablePedidos.getModel().getValueAt(jTablePedidos.getSelectedRow(), 4);
    }//GEN-LAST:event_jTablePedidosMouseClicked

    private void jBtAprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtAprovarActionPerformed
        Aluguel apAluguel = new Aluguel();
        Cliente apClt = new Cliente();
        Livro apLiv = new Livro();
        
        apAluguel.setStatus("Alugado");
        apClt.setCpf(clt_selec);
        apLiv.setNome(liv_selec);
                
        AluguelDAO apAlgDAO = new AluguelDAO();
        ClienteDAO apCltDAO = new ClienteDAO();
        LivroDAO apLivDAO = new LivroDAO();
        
        apCltDAO.dadosClientesCpf(apClt);
        apLivDAO.dadosLivroAlug(apLiv);
        apCltDAO.alugaLivroClt(apClt);
        apAlgDAO.aprovacaoAluguel(apAluguel, id_selec, sessao, getDataAtual());
        
        apLivDAO.alugaLivro(apLiv);
        
        listarTabelaPedidos();
        listarTabelaAlugClt();
        listarTabelaAlugLivro();
        
        CardLayout cl = (CardLayout) jPanelFather.getLayout();
        cl.show(jPanelFather, "TelaLivros");
        
        CardLayout cl2 = (CardLayout) jPanelFather.getLayout();
        cl.show(jPanelFather, "TelaAlugueis");
    }//GEN-LAST:event_jBtAprovarActionPerformed

    private void jBtReprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtReprovarActionPerformed
        Aluguel apAluguel = new Aluguel();
        apAluguel.setStatus("Reprovado");
        
        AluguelDAO apAlgDAO = new AluguelDAO();
        apAlgDAO.aprovacaoAluguel(apAluguel, id_selec, sessao, getDataAtual());
    }//GEN-LAST:event_jBtReprovarActionPerformed


    private void jComboBoxCategoriaLivroComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaLivroComponentAdded
        // TODO add your handling code here:
        jComboBoxCategoriaLivro.removeAllItems();
        CategoriaDAO cDAO = new CategoriaDAO();
        List<CategoriaLivro> categorias;
        categorias = cDAO.listarCategorias();
        Iterator i = categorias.iterator();
        while(i.hasNext()){
            jComboBoxCategoriaLivro.addItem(String.valueOf(i.next()));
            
        }
    }//GEN-LAST:event_jComboBoxCategoriaLivroComponentAdded


    private void jButtonLimparCamposLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparCamposLivroActionPerformed
        jTextFieldNomeLivro.setText(null);
        jTextFieldQuantidadeLivro.setText(null);
        jFormattedTextFieldDataLivro.setText(null);
        jTextFieldQualidadeLivro.setText(null);
        jTextAreaResumoLivro.setText(null);
        jLabelImagemCapa.setIcon(null);
        selectedImage = null;
        
    }//GEN-LAST:event_jButtonLimparCamposLivroActionPerformed

    private void jButtonPesquisaRecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisaRecoActionPerformed
        if(jTextFieldPesquisaReco.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Você Precisa Escrever o Nome do Livro para a Pesquisa!");
        }else{
            
            LivroDAO livroDAO = new LivroDAO();
            List<Livro> livros = livroDAO.pesquisaPorNome(jTextFieldPesquisaReco.getText());
            
            if (livros.isEmpty()){
                JOptionPane.showMessageDialog(null, "Sua Pesquisa não obteve Resultados. "
                        + "tente escrever de outra forma.");
            } else {
        
        DefaultTableModel valor = (DefaultTableModel) jTablePesquisaReco.getModel();
        valor.getDataVector().removeAllElements();
        
        int i = 0;
        while (livros.size() > i){
            
            AutorDAO autorDAO = new AutorDAO();
            AutorLivro autor = autorDAO.pesquisaPorID(livros.get(i).getAutorID());
            valor.addRow(new Object[]{String.valueOf(livros.get(i).getId_Livro()), livros.get(i).getNome(), autor.getNome()});
            i++;
        }
            }
        }
    }//GEN-LAST:event_jButtonPesquisaRecoActionPerformed

    private void jButtonPesquisarLancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarLancaActionPerformed
        if(jTextFieldPesquisaLanca.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Você Precisa Escrever o Nome do Livro para a Pesquisa!");
        }else{
            
            LivroDAO livroDAO = new LivroDAO();
            List<Livro> livros = livroDAO.pesquisaPorNome(jTextFieldPesquisaLanca.getText());
            
            if (livros.isEmpty()){
                JOptionPane.showMessageDialog(null, "Sua Pesquisa não obteve Resultados. "
                        + "tente escrever de outra forma.");
            } else {
        
        DefaultTableModel valor = (DefaultTableModel) jTablePesquisaLanca.getModel();
        valor.getDataVector().removeAllElements();
        
        int i = 0;
        while (livros.size() > i){
            
            AutorDAO autorDAO = new AutorDAO();
            AutorLivro autor = autorDAO.pesquisaPorID(livros.get(i).getAutorID());
            valor.addRow(new Object[]{String.valueOf(livros.get(i).getId_Livro()), livros.get(i).getNome(), autor.getNome()});
            i++;
        }
            }
        }
    }//GEN-LAST:event_jButtonPesquisarLancaActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(jTableLancamentos.getRowCount() >= 4){
            JOptionPane.showMessageDialog(null, "Você já selecionou 4 Livros, caso deseje trocar, "
                    + "Remova algum!");
        
        }else{
            String livroID = (String) jTablePesquisaLanca.getModel().getValueAt(jTablePesquisaLanca.getSelectedRow(), 0);
            LivroDAO livroDAO = new LivroDAO();
            
            Livro livro = livroDAO.pesquisaPorID(parseInt(livroID));
            
            livroDAO.salvarLancamento(livro);
            listarTabelaLancamentos();
            
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(jTableRecomendados.getRowCount() >= 4){
            JOptionPane.showMessageDialog(null, "Você já selecionou 4 Livros, caso deseje trocar, "
                    + "Remova algum!");
        
        }else{
            String livroID = (String) jTablePesquisaReco.getModel().getValueAt(jTablePesquisaReco.getSelectedRow(), 0);
            LivroDAO livroDAO = new LivroDAO();
            
            Livro livro = livroDAO.pesquisaPorID(parseInt(livroID));
            
            livroDAO.salvarRecomendado(livro);
            listarTabelaRecomendados();}
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(jTableLancamentos.getSelectedRow() > -1){
        String livroID = (String) jTableLancamentos.getModel().getValueAt(jTableLancamentos.getSelectedRow(), 0);
        LivroDAO livroDAO = new LivroDAO();
        Livro livro = livroDAO.pesquisaPorID(parseInt(livroID));
            
        livroDAO.removerLancamento(livro);
        listarTabelaLancamentos();
        } else {
        JOptionPane.showMessageDialog(null, "Ocorreu um Erro! Selecione um Livro para fazer isso!");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(jTableRecomendados.getSelectedRow() > -1){
        String livroID = (String) jTableRecomendados.getModel().getValueAt(jTableRecomendados.getSelectedRow(), 0);
        LivroDAO livroDAO = new LivroDAO();
        Livro livro = livroDAO.pesquisaPorID(parseInt(livroID));
            
        livroDAO.removerRecomendado(livro);
        listarTabelaRecomendados();
        } else {
        JOptionPane.showMessageDialog(null, "Ocorreu um Erro! Selecione um Livro para fazer isso!");}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        listarLivro ll = new listarLivro();
        ll.show();
    }//GEN-LAST:event_jButton1ActionPerformed
    

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AreaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AreaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AreaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AreaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AreaUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SideMenu;
    private javax.swing.ButtonGroup bGPesquisa;
    private javax.swing.JButton jBtAlterar;
    private javax.swing.JButton jBtAlterarGerCliente;
    private javax.swing.JButton jBtAlugar;
    private javax.swing.JButton jBtAprovar;
    private javax.swing.JButton jBtCadastrar;
    private javax.swing.JButton jBtCadastrar1;
    private javax.swing.JButton jBtExcluir;
    private javax.swing.JButton jBtExcluirGerCliente;
    private javax.swing.JButton jBtGerAlugueis;
    private javax.swing.JButton jBtGerAnuncios;
    private javax.swing.JButton jBtGerClientes;
    private javax.swing.JButton jBtGerLivros;
    private javax.swing.JButton jBtGerUsuarios1;
    private javax.swing.JButton jBtHome;
    private javax.swing.JButton jBtLimpDadosAlug;
    private javax.swing.JButton jBtLimpar;
    private javax.swing.JButton jBtLimpar1;
    private javax.swing.JButton jBtLogOut;
    private javax.swing.JButton jBtPesquisar;
    private javax.swing.JButton jBtPesquisarGerCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jBtReprovar;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonAddCapa;
    private javax.swing.JButton jButtonAddNovaCatLivro;
    private javax.swing.JButton jButtonAddNovoAutLivro;
    private javax.swing.JButton jButtonAlterarAutLivro;
    private javax.swing.JButton jButtonAlterarCatLivro;
    private javax.swing.JButton jButtonCadastrarLivro;
    private javax.swing.JButton jButtonLimparCamposLivro;
    private javax.swing.JButton jButtonPesquisaReco;
    private javax.swing.JButton jButtonPesquisarLanca;
    private javax.swing.JButton jButtonRemoverAutLivro;
    private javax.swing.JButton jButtonRemoverCatLivro;
    private javax.swing.JComboBox<String> jCbAltDelCargo;
    private javax.swing.JComboBox<String> jCbCargo;
    private javax.swing.JComboBox<String> jComboBoxAutorLivro;
    private javax.swing.JComboBox<String> jComboBoxCategoriaLivro;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataLivro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAltCPFGerClientes;
    private javax.swing.JLabel jLabelAltEnderecoGerCliente;
    private javax.swing.JLabel jLabelAutorLivro;
    private javax.swing.JLabel jLabelCPFGerCliente1;
    private javax.swing.JLabel jLabelCadastrarAutoresLivro;
    private javax.swing.JLabel jLabelCargo;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelCategoriaLivro;
    private javax.swing.JLabel jLabelDataLivro;
    private javax.swing.JLabel jLabelEnderecoGerCliente;
    private javax.swing.JLabel jLabelEnderecoGerCliente1;
    private javax.swing.JLabel jLabelEnderecoGerCliente2;
    private javax.swing.JLabel jLabelGerenciarCategorias;
    private javax.swing.JLabel jLabelGerenciarCategorias3;
    private javax.swing.JLabel jLabelGerenciarCategorias4;
    private javax.swing.JLabel jLabelGerenciarCategorias5;
    private javax.swing.JLabel jLabelIdGerClientes;
    private javax.swing.JLabel jLabelImagemCapa;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelNomeAlterarGerClientes;
    private javax.swing.JLabel jLabelNomeAutLivro;
    private javax.swing.JLabel jLabelNomeCatLivro;
    private javax.swing.JLabel jLabelNomeExib;
    private javax.swing.JLabel jLabelNomeGerCliente;
    private javax.swing.JLabel jLabelNomeLivro;
    private javax.swing.JLabel jLabelQualidadeLivro;
    private javax.swing.JLabel jLabelQuantidadeLivro;
    private javax.swing.JLabel jLabelResumoLivro;
    private javax.swing.JPanel jPaneCadastrarCat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAnuncios;
    private javax.swing.JPanel jPanelCadastrarAut;
    private javax.swing.JPanel jPanelCapaLivro;
    private javax.swing.JPanel jPanelFather;
    private javax.swing.JPanel jPanelGerAlugueis;
    private javax.swing.JPanel jPanelGerCliente;
    private javax.swing.JPanel jPanelGerLivros;
    private javax.swing.JPanel jPanelGerUsuarios;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPasswordField jPassSenha;
    private javax.swing.JPasswordField jPassSenhaGerCliente;
    private javax.swing.JRadioButton jRbId;
    private javax.swing.JRadioButton jRbIdGerCliente;
    private javax.swing.JRadioButton jRbLogin;
    private javax.swing.JRadioButton jRbLoginGerCliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSeparator jSeparatorCat;
    private javax.swing.JTable jTableAlugClt;
    private javax.swing.JTable jTableAlugLivros;
    private javax.swing.JTable jTableAutoresLivros;
    private javax.swing.JTable jTableCategoriasLivro;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableLancamentos;
    private javax.swing.JTable jTablePesquisaLanca;
    private javax.swing.JTable jTablePesquisaReco;
    private javax.swing.JTable jTableRecomendados;
    private javax.swing.JTable jTablePedidos;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JTextArea jTextAreaResumoLivro;
    private javax.swing.JTextField jTextFieldNomeAutLivro;
    private javax.swing.JTextField jTextFieldNomeLivro;
    private javax.swing.JTextField jTextFieldNovaCatLivro;
    private javax.swing.JTextField jTextFieldPesquisaLanca;
    private javax.swing.JTextField jTextFieldPesquisaReco;
    private javax.swing.JTextField jTextFieldQualidadeLivro;
    private javax.swing.JTextField jTextFieldQuantidadeLivro;
    private javax.swing.JTextField jTextFieldSelAutID;
    private javax.swing.JTextField jTextFieldSelAutNome;
    private javax.swing.JTextField jTextFieldSelCatID;
    private javax.swing.JTextField jTextFieldSelCatNome;
    private javax.swing.JFormattedTextField jTxtAltCpfGerClientes;
    private javax.swing.JFormattedTextField jTxtAltDataNascGerClientes;
    private javax.swing.JFormattedTextField jTxtAltDelCpf;
    private javax.swing.JTextField jTxtAltDelEmail;
    private javax.swing.JTextField jTxtAltDelId;
    private javax.swing.JTextField jTxtAltDelLogin;
    private javax.swing.JTextField jTxtAltDelNome;
    private javax.swing.JTextField jTxtAltDelSenha;
    private javax.swing.JTextField jTxtAltEmailGerClientes;
    private javax.swing.JTextField jTxtAltEnderecoGerClientes;
    private javax.swing.JTextField jTxtAltIdGerClientes;
    private javax.swing.JTextField jTxtAltLoginGerClientes;
    private javax.swing.JTextField jTxtAltNomeGerClientes;
    private javax.swing.JTextField jTxtAltSenhaGerClientes;
    private javax.swing.JTextField jTxtAlugCliente;
    private javax.swing.JTextField jTxtAlugCpf;
    private javax.swing.JTextField jTxtAlugData;
    private javax.swing.JTextField jTxtAlugLivro;
    private javax.swing.JTextField jTxtAlugQntd;
    private javax.swing.JFormattedTextField jTxtCpf;
    private javax.swing.JFormattedTextField jTxtCpfGerCliente;
    private javax.swing.JFormattedTextField jTxtDataNascGerCliente;
    private javax.swing.JTextField jTxtEmail;
    private javax.swing.JTextField jTxtEmailGerCliente;
    private javax.swing.JTextField jTxtEnderecoGerCliente;
    private javax.swing.JTextField jTxtLogin;
    private javax.swing.JTextField jTxtLoginGerCliente;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JTextField jTxtNomeGerCliente;
    private javax.swing.JTextField jTxtPesqId;
    private javax.swing.JTextField jTxtPesqIdGerCliente;
    private javax.swing.JTextField jTxtPesqLogin;
    private javax.swing.JTextField jTxtPesqLoginGerCliente;
    // End of variables declaration//GEN-END:variables
}
