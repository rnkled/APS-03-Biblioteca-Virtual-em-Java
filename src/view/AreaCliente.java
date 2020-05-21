/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import CustomClass.ManipularImagem;
import CustomClass.ManipularLabels;
import DAO.AluguelDAO;
import DAO.ClienteDAO;
import DAO.LivroDAO;
import biblioteca.Aluguel;
import biblioteca.Cliente;
import biblioteca.Livro;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;


public class AreaCliente extends javax.swing.JFrame {
    
    String SelCat = null;
    String PesquisaTexto = null;
    int currentPage = 0;
    private Cliente clt_sessao;

    /**
     * Creates new form AreaCliente
     */
    public AreaCliente() {
        initComponents();
        setListas();
        setLancamentos();
        setRecomendados();
        
    }
    
    LivroDAO livro = new LivroDAO();
    List<JLabel> recomendados = new ArrayList<JLabel>();
    List<JLabel> titrecomendados = new ArrayList<JLabel>();
    
    List<JLabel> lancamentos = new ArrayList<JLabel>();
    List<JLabel> titlancamentos = new ArrayList<JLabel>();
    
    List<JLabel> categorias = new ArrayList<JLabel>();
    List<JLabel> titcategorias = new ArrayList<JLabel>();
    
    List<JLabel> pesquisa = new ArrayList<JLabel>();
    List<JLabel> titpesquisa = new ArrayList<JLabel>();
    
    private void setRecomendados(){
        List<Livro> livros = new ArrayList<Livro>();
        livros = livro.getRecomendados();
        ManipularLabels.setLabels(livros, recomendados, titrecomendados);
    }
    
    private void setLancamentos(){
        List<Livro> livros = new ArrayList<Livro>();
        livros = livro.getLancamentos();
        ManipularLabels.setLabels(livros, lancamentos, titlancamentos);
    }
    
    private void setListas(){
        //Capas
        this.lancamentos.add(jLabelLivro1);
        this.lancamentos.add(jLabelLivro2);
        this.lancamentos.add(jLabelLivro3);
        this.lancamentos.add(jLabelLivro4);
        
        this.recomendados.add(jLabelLivro5);
        this.recomendados.add(jLabelLivro6);
        this.recomendados.add(jLabelLivro7);
        this.recomendados.add(jLabelLivro8);
        
        this.categorias.add(jLabelLivro9);
        this.categorias.add(jLabelLivro10);
        this.categorias.add(jLabelLivro11);
        this.categorias.add(jLabelLivro12);
        this.categorias.add(jLabelLivro13);
        this.categorias.add(jLabelLivro14);
        this.categorias.add(jLabelLivro15);
        this.categorias.add(jLabelLivro16);
        
        this.pesquisa.add(jLabelLivro17);
        this.pesquisa.add(jLabelLivro18);
        this.pesquisa.add(jLabelLivro19);
        this.pesquisa.add(jLabelLivro20);
        this.pesquisa.add(jLabelLivro21);
        this.pesquisa.add(jLabelLivro22);
        this.pesquisa.add(jLabelLivro23);
        this.pesquisa.add(jLabelLivro24);
        
        
        //Titulos
        this.titlancamentos.add(jLabelTitLivro1);
        this.titlancamentos.add(jLabelTitLivro2);
        this.titlancamentos.add(jLabelTitLivro3);
        this.titlancamentos.add(jLabelTitLivro4);
        
        this.titrecomendados.add(jLabelTitLivro5);
        this.titrecomendados.add(jLabelTitLivro6);
        this.titrecomendados.add(jLabelTitLivro7);
        this.titrecomendados.add(jLabelTitLivro8);
        
        this.titcategorias.add(jLabelTitLivro9);
        this.titcategorias.add(jLabelTitLivro10);
        this.titcategorias.add(jLabelTitLivro11);
        this.titcategorias.add(jLabelTitLivro12);
        this.titcategorias.add(jLabelTitLivro13);
        this.titcategorias.add(jLabelTitLivro14);
        this.titcategorias.add(jLabelTitLivro15);
        this.titcategorias.add(jLabelTitLivro16);
        
        this.titpesquisa.add(jLabelTitLivro17);
        this.titpesquisa.add(jLabelTitLivro18);
        this.titpesquisa.add(jLabelTitLivro19);
        this.titpesquisa.add(jLabelTitLivro20);
        this.titpesquisa.add(jLabelTitLivro21);
        this.titpesquisa.add(jLabelTitLivro22);
        this.titpesquisa.add(jLabelTitLivro23);
        this.titpesquisa.add(jLabelTitLivro24);
    
    
    }
    
    
    public void popularCategoria(){
        
        for (int i=0; i<categorias.size(); i++){
            categorias.get(i).setIcon(null);
            titcategorias.get(i).setText(null);
        }
        
        List<Livro> livros = new ArrayList<Livro>();
        livros = livro.pesquisaPorCategoria(SelCat);
        
        ManipularLabels.setLabelsPorPage(livros, categorias, titcategorias, currentPage);
        
    }
    
    public void popularPesquisa(){
        
        for (int i=0; i<pesquisa.size(); i++){
            pesquisa.get(i).setIcon(null);
            titpesquisa.get(i).setText(null);
        }
        
        
        List<Livro> livros = new ArrayList<Livro>();
        livros = livro.pesquisaPorNome(this.PesquisaTexto);
        
        ManipularLabels.setLabelsPorPage(livros, pesquisa, titpesquisa, currentPage);
        
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaPesquisa");
    
    }
    
    public void setCltLogin(Cliente cliente){
        ClienteDAO clt = new ClienteDAO();
        clt.dadosCliente(cliente);
        clt_sessao = cliente;
        }
    
    public void bemVindo(){
        JOptionPane.showMessageDialog(null, "Seja bem-vindo "+clt_sessao.getNome());
    }
    
    public void listarTabelaHistorico(){
	jCbStatus.removeAllItems();
	jCbStatus.addItem("Todos");
	DefaultTableModel valor = (DefaultTableModel) jTableHistorico.getModel();
	valor.getDataVector().removeAllElements();
	
	AluguelDAO algDAO = new AluguelDAO();
	List<Aluguel> alugueis = algDAO.listarAlugueis(clt_sessao);
	int i = 0;
	while(alugueis.size() > 0){
		valor.addRow(new Object[] {alugueis.get(i).getNmLivroAlugado(), alugueis.get(i).getData(), alugueis.get(i).getDataDevolucao(), alugueis.get(i).getStatus()});
		jCbStatus.addItem(alugueis.get(i).getStatus());
		i++;
        }
    }


    public void listarTabelaHistoricoStatus(){

	DefaultTableModel valor = (DefaultTableModel) jTableHistorico.getModel();
	valor.getDataVector().removeAllElements();
	
	AluguelDAO algDAO = new AluguelDAO();
	List<Aluguel> alugueis = algDAO.listarAlugueisStatus(clt_sessao, String.valueOf(jCbStatus.getSelectedItem())); 
	int i = 0;
	while(alugueis.size() > 0){
		valor.addRow(new Object[] {alugueis.get(i).getLivroAlugou().getNome(), alugueis.get(i).getData(), /*alugueis.get(i).getDataDevolucao(),*/ alugueis.get(i).getStatus()});
		i++;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator4 = new javax.swing.JSeparator();
        SideMenu = new javax.swing.JPanel();
        jLabelGerenciamento = new javax.swing.JLabel();
        jSeparatorGer = new javax.swing.JSeparator();
        jTextFieldPesquisa = new javax.swing.JTextField();
        jLabelPesquisa = new javax.swing.JLabel();
        jSeparatorPes = new javax.swing.JSeparator();
        jButtonConteudoHistorico = new javax.swing.JButton();
        jButtonHome = new javax.swing.JButton();
        jButtonSuspense = new javax.swing.JButton();
        jButtonTerror = new javax.swing.JButton();
        jButtonRomance = new javax.swing.JButton();
        jButtonEducacional = new javax.swing.JButton();
        jButtonComedia = new javax.swing.JButton();
        jLabelCategoria = new javax.swing.JLabel();
        jSeparatorCat = new javax.swing.JSeparator();
        jButtonPerfil = new javax.swing.JButton();
        jButtonCarrinho = new javax.swing.JButton();
        jButtonHistorico = new javax.swing.JButton();
        jButtonLogout = new javax.swing.JButton();
        jButtonAventura = new javax.swing.JButton();
        Screen = new javax.swing.JPanel();
        jPanelTelaInicial = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelLivro1 = new javax.swing.JLabel();
        jLabelTitLivro1 = new javax.swing.JLabel();
        jLabelLivro2 = new javax.swing.JLabel();
        jLabelTitLivro2 = new javax.swing.JLabel();
        jLabelLivro3 = new javax.swing.JLabel();
        jLabelTitLivro3 = new javax.swing.JLabel();
        jLabelLivro4 = new javax.swing.JLabel();
        jLabelTitLivro4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabelLivro5 = new javax.swing.JLabel();
        jLabelTitLivro5 = new javax.swing.JLabel();
        jLabelLivro6 = new javax.swing.JLabel();
        jLabelTitLivro6 = new javax.swing.JLabel();
        jLabelLivro7 = new javax.swing.JLabel();
        jLabelTitLivro7 = new javax.swing.JLabel();
        jLabelLivro8 = new javax.swing.JLabel();
        jLabelTitLivro8 = new javax.swing.JLabel();
        jPanelTelaCategoria = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabelLivro9 = new javax.swing.JLabel();
        jLabelTitLivro9 = new javax.swing.JLabel();
        jLabelLivro10 = new javax.swing.JLabel();
        jLabelTitLivro10 = new javax.swing.JLabel();
        jLabelLivro11 = new javax.swing.JLabel();
        jLabelTitLivro11 = new javax.swing.JLabel();
        jLabelLivro12 = new javax.swing.JLabel();
        jLabelTitLivro12 = new javax.swing.JLabel();
        jLabelTituloCategoria = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabelLivro13 = new javax.swing.JLabel();
        jLabelTitLivro13 = new javax.swing.JLabel();
        jLabelLivro14 = new javax.swing.JLabel();
        jLabelTitLivro14 = new javax.swing.JLabel();
        jLabelLivro15 = new javax.swing.JLabel();
        jLabelTitLivro15 = new javax.swing.JLabel();
        jLabelLivro16 = new javax.swing.JLabel();
        jLabelTitLivro16 = new javax.swing.JLabel();
        jButtonProximaPagina = new javax.swing.JButton();
        jButtonPaginaAnterior = new javax.swing.JButton();
        jLabelTituloCategoriaTexto = new javax.swing.JLabel();
        jPanelTelaLivro = new javax.swing.JPanel();
        jLabelTituloLivro = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabelTituloCategoriaTexto2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonRetornar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonAdcCarrinho = new javax.swing.JButton();
        jLabelLAutor = new javax.swing.JLabel();
        jLabelLData = new javax.swing.JLabel();
        jLabelLId = new javax.swing.JLabel();
        jLabelLCat = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabellNome = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabelLDisponiveis = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanelTelaPerfil = new javax.swing.JPanel();
        jLabelTituloCategoria4 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabelTituloCategoriaTexto4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButtonRetornar3 = new javax.swing.JButton();
        jPanelTelaCarrinho = new javax.swing.JPanel();
        jLabelTituloCategoria3 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabelTituloCategoriaTexto3 = new javax.swing.JLabel();
        jButtonRetornar2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanelHistórico = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jCbStatus = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHistorico = new javax.swing.JTable();
        jPanelTelaPesquisa = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabelLivro17 = new javax.swing.JLabel();
        jLabelTitLivro17 = new javax.swing.JLabel();
        jLabelLivro18 = new javax.swing.JLabel();
        jLabelTitLivro18 = new javax.swing.JLabel();
        jLabelLivro19 = new javax.swing.JLabel();
        jLabelTitLivro19 = new javax.swing.JLabel();
        jLabelLivro20 = new javax.swing.JLabel();
        jLabelTitLivro20 = new javax.swing.JLabel();
        jLabelTituloCategoria1 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel12 = new javax.swing.JPanel();
        jLabelLivro21 = new javax.swing.JLabel();
        jLabelTitLivro21 = new javax.swing.JLabel();
        jLabelLivro22 = new javax.swing.JLabel();
        jLabelTitLivro22 = new javax.swing.JLabel();
        jLabelLivro23 = new javax.swing.JLabel();
        jLabelTitLivro23 = new javax.swing.JLabel();
        jLabelLivro24 = new javax.swing.JLabel();
        jLabelTitLivro24 = new javax.swing.JLabel();
        jButtonProximaPagina1 = new javax.swing.JButton();
        jButtonPaginaAnterior1 = new javax.swing.JButton();
        jLabelTituloCategoriaTexto1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(57, 57, 57));
        setResizable(false);

        SideMenu.setBackground(new java.awt.Color(67, 67, 67));
        SideMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelGerenciamento.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelGerenciamento.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGerenciamento.setText("Gerenciamento");
        SideMenu.add(jLabelGerenciamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 283, 170, 30));
        SideMenu.add(jSeparatorGer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 170, 10));

        jTextFieldPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisaKeyPressed(evt);
            }
        });
        SideMenu.add(jTextFieldPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, 170, -1));

        jLabelPesquisa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelPesquisa.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPesquisa.setText("Pesquisa");
        SideMenu.add(jLabelPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 170, 30));
        SideMenu.add(jSeparatorPes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, 170, 10));

        jButtonConteudoHistorico.setForeground(new java.awt.Color(90, 95, 98));
        jButtonConteudoHistorico.setText("Fantasia");
        jButtonConteudoHistorico.setBorderPainted(false);
        jButtonConteudoHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConteudoHistoricoActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonConteudoHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 170, 20));

        jButtonHome.setForeground(new java.awt.Color(90, 95, 98));
        jButtonHome.setText("Destaques de Hoje");
        jButtonHome.setBorderPainted(false);
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 170, 20));

        jButtonSuspense.setForeground(new java.awt.Color(90, 95, 98));
        jButtonSuspense.setText("Suspense");
        jButtonSuspense.setBorderPainted(false);
        jButtonSuspense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuspenseActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonSuspense, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 170, 20));

        jButtonTerror.setForeground(new java.awt.Color(90, 95, 98));
        jButtonTerror.setText("Terror");
        jButtonTerror.setBorderPainted(false);
        jButtonTerror.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerrorActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonTerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 170, 20));

        jButtonRomance.setForeground(new java.awt.Color(90, 95, 98));
        jButtonRomance.setText("Romance");
        jButtonRomance.setBorderPainted(false);
        jButtonRomance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRomanceActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonRomance, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 170, 20));

        jButtonEducacional.setForeground(new java.awt.Color(90, 95, 98));
        jButtonEducacional.setText("Educacional");
        jButtonEducacional.setBorderPainted(false);
        jButtonEducacional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEducacionalActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonEducacional, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, 20));

        jButtonComedia.setForeground(new java.awt.Color(90, 95, 98));
        jButtonComedia.setText("Comédia");
        jButtonComedia.setBorderPainted(false);
        jButtonComedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComediaActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonComedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 170, 20));

        jLabelCategoria.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelCategoria.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCategoria.setText("Categorias");
        SideMenu.add(jLabelCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 170, 30));
        SideMenu.add(jSeparatorCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 170, 10));

        jButtonPerfil.setForeground(new java.awt.Color(90, 95, 98));
        jButtonPerfil.setText("Meu Perfil");
        jButtonPerfil.setBorderPainted(false);
        jButtonPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPerfilActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 170, 20));

        jButtonCarrinho.setForeground(new java.awt.Color(90, 95, 98));
        jButtonCarrinho.setText("Carrinho");
        jButtonCarrinho.setActionCommand("");
        jButtonCarrinho.setBorderPainted(false);
        jButtonCarrinho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCarrinhoActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonCarrinho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 170, 20));

        jButtonHistorico.setForeground(new java.awt.Color(90, 95, 98));
        jButtonHistorico.setText("Histórico");
        jButtonHistorico.setBorderPainted(false);
        jButtonHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistoricoActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonHistorico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 170, 20));

        jButtonLogout.setForeground(new java.awt.Color(90, 95, 98));
        jButtonLogout.setText("Logout");
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 70, 30));

        jButtonAventura.setForeground(new java.awt.Color(90, 95, 98));
        jButtonAventura.setText("Aventura");
        jButtonAventura.setBorderPainted(false);
        jButtonAventura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAventuraActionPerformed(evt);
            }
        });
        SideMenu.add(jButtonAventura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 170, 20));

        Screen.setLayout(new java.awt.CardLayout());

        jPanelTelaInicial.setBackground(new java.awt.Color(204, 204, 255));
        jPanelTelaInicial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLivro1.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro1.setToolTipText("");
        jLabelLivro1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro1MouseClicked(evt);
            }
        });

        jLabelTitLivro1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro1MouseClicked(evt);
            }
        });

        jLabelLivro2.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro2.setToolTipText("");
        jLabelLivro2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro2MouseClicked(evt);
            }
        });

        jLabelTitLivro2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro2MouseClicked(evt);
            }
        });

        jLabelLivro3.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro3.setToolTipText("");
        jLabelLivro3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro3MouseClicked(evt);
            }
        });

        jLabelTitLivro3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro3MouseClicked(evt);
            }
        });

        jLabelLivro4.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro4.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro4.setToolTipText("");
        jLabelLivro4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro4MouseClicked(evt);
            }
        });

        jLabelTitLivro4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelLivro4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelLivro3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelLivro2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelLivro1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelTelaInicial.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 500, 170));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Recomendado para Você!");
        jPanelTelaInicial.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 30));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanelTelaInicial.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 310, 10));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanelTelaInicial.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 310, 10));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Lançamentos");
        jPanelTelaInicial.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 320, 30));

        jLabelLivro5.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro5.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro5.setToolTipText("");
        jLabelLivro5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro5MouseClicked(evt);
            }
        });

        jLabelTitLivro5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro5MouseClicked(evt);
            }
        });

        jLabelLivro6.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro6.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro6.setToolTipText("");
        jLabelLivro6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro6MouseClicked(evt);
            }
        });

        jLabelTitLivro6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro6MouseClicked(evt);
            }
        });

        jLabelLivro7.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro7.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro7.setToolTipText("");
        jLabelLivro7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro7MouseClicked(evt);
            }
        });

        jLabelTitLivro7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro7MouseClicked(evt);
            }
        });

        jLabelLivro8.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro8.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro8.setToolTipText("");
        jLabelLivro8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro8MouseClicked(evt);
            }
        });

        jLabelTitLivro8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelTitLivro5, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabelLivro5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelLivro8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelLivro7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelLivro6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabelLivro5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelTelaInicial.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 500, 170));

        Screen.add(jPanelTelaInicial, "TelaInicial");

        jPanelTelaCategoria.setBackground(new java.awt.Color(204, 204, 255));
        jPanelTelaCategoria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLivro9.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro9.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro9.setToolTipText("");
        jLabelLivro9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro9MouseClicked(evt);
            }
        });

        jLabelTitLivro9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro9MouseClicked(evt);
            }
        });

        jLabelLivro10.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro10.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro10.setToolTipText("");
        jLabelLivro10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro10MouseClicked(evt);
            }
        });

        jLabelTitLivro10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro10MouseClicked(evt);
            }
        });

        jLabelLivro11.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro11.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro11.setToolTipText("");
        jLabelLivro11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro11MouseClicked(evt);
            }
        });

        jLabelTitLivro11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro11MouseClicked(evt);
            }
        });

        jLabelLivro12.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro12.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro12.setToolTipText("");
        jLabelLivro12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro12MouseClicked(evt);
            }
        });

        jLabelTitLivro12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelLivro12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelLivro11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelLivro10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelLivro9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelTelaCategoria.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 59, 500, 170));

        jLabelTituloCategoria.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloCategoria.setText("Undefined");
        jPanelTelaCategoria.add(jLabelTituloCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 10, 380, 30));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanelTelaCategoria.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 360, 10));

        jLabelLivro13.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro13.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro13.setToolTipText("");
        jLabelLivro13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro13MouseClicked(evt);
            }
        });

        jLabelTitLivro13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro13MouseClicked(evt);
            }
        });

        jLabelLivro14.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro14.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro14.setToolTipText("");
        jLabelLivro14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro14MouseClicked(evt);
            }
        });

        jLabelTitLivro14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro14MouseClicked(evt);
            }
        });

        jLabelLivro15.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro15.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro15.setToolTipText("");
        jLabelLivro15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro15MouseClicked(evt);
            }
        });

        jLabelTitLivro15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro15MouseCli(evt);
            }
        });

        jLabelLivro16.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro16.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro16.setToolTipText("");
        jLabelLivro16.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro16MouseClicked(evt);
            }
        });

        jLabelTitLivro16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro13, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro15, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro16, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabelLivro16, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabelLivro15, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabelLivro14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabelLivro13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelTelaCategoria.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 500, 170));

        jButtonProximaPagina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-chevron-right-30.png"))); // NOI18N
        jButtonProximaPagina.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonProximaPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProximaPaginaActionPerformed(evt);
            }
        });
        jPanelTelaCategoria.add(jButtonProximaPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 414, -1, -1));

        jButtonPaginaAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-chevron-esquerda-30.png"))); // NOI18N
        jButtonPaginaAnterior.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPaginaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPaginaAnteriorActionPerformed(evt);
            }
        });
        jPanelTelaCategoria.add(jButtonPaginaAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 414, -1, -1));

        jLabelTituloCategoriaTexto.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloCategoriaTexto.setText("Livros de ");
        jPanelTelaCategoria.add(jLabelTituloCategoriaTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        Screen.add(jPanelTelaCategoria, "TelaCategoria");

        jPanelTelaLivro.setBackground(new java.awt.Color(204, 204, 255));
        jPanelTelaLivro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloLivro.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloLivro.setText("Undefined");
        jPanelTelaLivro.add(jLabelTituloLivro, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 10, 500, 30));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jPanelTelaLivro.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 360, 10));

        jLabelTituloCategoriaTexto2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jPanelTelaLivro.add(jLabelTituloCategoriaTexto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("RESUMO\n");
        jScrollPane1.setViewportView(jTextArea1);

        jPanelTelaLivro.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 490, -1));

        jButtonRetornar.setText("Voltar");
        jButtonRetornar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetornarActionPerformed(evt);
            }
        });
        jPanelTelaLivro.add(jButtonRetornar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 90, 40));

        jLabel1.setText("Autor:");
        jPanelTelaLivro.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        jLabel4.setText("Prublicado em:");
        jPanelTelaLivro.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, -1));

        jLabel5.setText("Genero:");
        jPanelTelaLivro.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        jLabel6.setText("ID:");
        jPanelTelaLivro.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, -1, -1));

        jButtonAdcCarrinho.setText("Adicionar ao Carrinho");
        jPanelTelaLivro.add(jButtonAdcCarrinho, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 140, 40));

        jLabelLAutor.setText("jLabel10");
        jPanelTelaLivro.add(jLabelLAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 120, -1));

        jLabelLData.setText("jLabel11");
        jPanelTelaLivro.add(jLabelLData, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 130, -1));

        jLabelLId.setText("jLabel12");
        jPanelTelaLivro.add(jLabelLId, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 170, -1));

        jLabelLCat.setText("jLabel13");
        jPanelTelaLivro.add(jLabelLCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 120, -1));

        jLabel14.setText("Nome:");
        jPanelTelaLivro.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, -1, -1));

        jLabellNome.setText("jLabel15");
        jPanelTelaLivro.add(jLabellNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 120, -1));

        jLabel16.setText("Disponiveis:");
        jPanelTelaLivro.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, -1, -1));

        jLabelLDisponiveis.setText("jLabel23");
        jPanelTelaLivro.add(jLabelLDisponiveis, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 140, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelTelaLivro.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelTelaLivro.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));
        jPanelTelaLivro.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 210, 270));

        Screen.add(jPanelTelaLivro, "TelaLivro");

        jPanelTelaPerfil.setBackground(new java.awt.Color(204, 204, 255));
        jPanelTelaPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloCategoria4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloCategoria4.setText("Undefined");
        jPanelTelaPerfil.add(jLabelTituloCategoria4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 380, 30));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jPanelTelaPerfil.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 360, 10));

        jLabelTituloCategoriaTexto4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloCategoriaTexto4.setText("Perfil de");
        jPanelTelaPerfil.add(jLabelTituloCategoriaTexto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        jPanelTelaPerfil.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, 120));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setText("Nome:");
        jPanelTelaPerfil.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setText("Endereço:");
        jPanelTelaPerfil.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setText("Data de Nascimento:");
        jPanelTelaPerfil.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setText("Email:");
        jPanelTelaPerfil.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, -1, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel21.setText("Alugados rescentemente:");
        jPanelTelaPerfil.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));

        jLabel22.setText("Lugar do icone");
        jPanelTelaPerfil.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        jPanelTelaPerfil.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 520, 110));

        jButtonRetornar3.setText("Voltar");
        jButtonRetornar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetornar3ActionPerformed(evt);
            }
        });
        jPanelTelaPerfil.add(jButtonRetornar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 90, 40));

        Screen.add(jPanelTelaPerfil, "TelaPerfil");

        jPanelTelaCarrinho.setBackground(new java.awt.Color(204, 204, 255));
        jPanelTelaCarrinho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloCategoria3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloCategoria3.setText("Undefined");
        jPanelTelaCarrinho.add(jLabelTituloCategoria3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 380, 30));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jPanelTelaCarrinho.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 360, 10));

        jLabelTituloCategoriaTexto3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloCategoriaTexto3.setText("Carrinho de");
        jPanelTelaCarrinho.add(jLabelTituloCategoriaTexto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jButtonRetornar2.setText("Voltar");
        jButtonRetornar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetornar2ActionPerformed(evt);
            }
        });
        jPanelTelaCarrinho.add(jButtonRetornar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 90, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setText("Livros no carrinho");
        jPanelTelaCarrinho.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        jPanelTelaCarrinho.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 500, 190));

        jButton3.setText("Excluir Item");
        jPanelTelaCarrinho.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, -1, 40));

        jButton1.setText("Alugar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelTelaCarrinho.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 90, 40));

        Screen.add(jPanelTelaCarrinho, "TelaCarrinho");

        jPanelHistórico.setBackground(new java.awt.Color(204, 204, 255));
        jPanelHistórico.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setText("Meus Pedidos");
        jPanelHistórico.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 400, -1));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanelHistórico.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, 360, 10));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Filtrar por:");
        jPanelHistórico.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 93, -1, -1));

        jCbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Aprovados", "Em Análise", "Recusados" }));
        jPanelHistórico.add(jCbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 96, 111, -1));

        jTableHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome Livro", "Data Aluguel", "Data Devolucao", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableHistorico);

        jPanelHistórico.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 480, 250));

        Screen.add(jPanelHistórico, "TelaHistorico");

        jPanelTelaPesquisa.setBackground(new java.awt.Color(204, 204, 255));
        jPanelTelaPesquisa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLivro17.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro17.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro17.setToolTipText("");
        jLabelLivro17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro17MouseClicked(evt);
            }
        });

        jLabelTitLivro17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelLivro18.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro18.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro18.setToolTipText("");
        jLabelLivro18.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro18MouseClicked(evt);
            }
        });

        jLabelTitLivro18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelTitLivro18MouseClicked(evt);
            }
        });

        jLabelLivro19.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro19.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro19.setToolTipText("");
        jLabelLivro19.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro19MouseClicked(evt);
            }
        });

        jLabelTitLivro19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro19MouseClicked(evt);
            }
        });

        jLabelLivro20.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro20.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro20.setToolTipText("");
        jLabelLivro20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro20MouseClicked(evt);
            }
        });

        jLabelTitLivro20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro20MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro17, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro18, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro19, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro20, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabelLivro20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabelLivro19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabelLivro18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabelLivro17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelTelaPesquisa.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 59, 500, 170));

        jLabelTituloCategoria1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloCategoria1.setText("Undefined");
        jPanelTelaPesquisa.add(jLabelTituloCategoria1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 330, 30));

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanelTelaPesquisa.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 360, 10));

        jLabelLivro21.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro21.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro21.setToolTipText("");
        jLabelLivro21.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro21MouseClicked(evt);
            }
        });

        jLabelTitLivro21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro21MouseClicked(evt);
            }
        });

        jLabelLivro22.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro22.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro22.setToolTipText("");
        jLabelLivro22.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro22MouseClicked(evt);
            }
        });

        jLabelTitLivro22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro22MouseClicked(evt);
            }
        });

        jLabelLivro23.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro23.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro23.setToolTipText("");
        jLabelLivro23.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro23MouseClicked(evt);
            }
        });

        jLabelTitLivro23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro23MouseClicked(evt);
            }
        });

        jLabelLivro24.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLivro24.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLivro24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLivro24.setToolTipText("");
        jLabelLivro24.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelLivro24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro24MouseClicked(evt);
            }
        });

        jLabelTitLivro24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitLivro24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLivro24MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro21, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro22, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLivro24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitLivro24, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabelLivro24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro24, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabelLivro23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabelLivro22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabelLivro21, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTitLivro21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelTelaPesquisa.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 500, 170));

        jButtonProximaPagina1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-chevron-right-30.png"))); // NOI18N
        jButtonProximaPagina1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonProximaPagina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProximaPagina1ActionPerformed(evt);
            }
        });
        jPanelTelaPesquisa.add(jButtonProximaPagina1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 414, -1, -1));

        jButtonPaginaAnterior1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-chevron-esquerda-30.png"))); // NOI18N
        jButtonPaginaAnterior1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPaginaAnterior1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPaginaAnterior1ActionPerformed(evt);
            }
        });
        jPanelTelaPesquisa.add(jButtonPaginaAnterior1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 414, -1, -1));

        jLabelTituloCategoriaTexto1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelTituloCategoriaTexto1.setText("Resultado de: ");
        jPanelTelaPesquisa.add(jLabelTituloCategoriaTexto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        Screen.add(jPanelTelaPesquisa, "TelaPesquisa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Screen, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Screen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(SideMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPerfilActionPerformed
       CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaPerfil");
    }//GEN-LAST:event_jButtonPerfilActionPerformed

    private void jButtonCarrinhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCarrinhoActionPerformed
        {
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCarrinho");
        }
    }//GEN-LAST:event_jButtonCarrinhoActionPerformed

    private void jButtonPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPaginaAnteriorActionPerformed
        if (this.currentPage > 0){
            this.currentPage -= 1;
            popularCategoria();
        }
    }//GEN-LAST:event_jButtonPaginaAnteriorActionPerformed

    private void jButtonProximaPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProximaPaginaActionPerformed
        if (jLabelTitLivro16.getText() != null){
        this.currentPage += 1;
        popularCategoria();}
    }//GEN-LAST:event_jButtonProximaPaginaActionPerformed

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaInicial");
        this.currentPage = 0;
        this.SelCat = null;
        jLabelTituloCategoria.setText("Undefined");
        
    }//GEN-LAST:event_jButtonHomeActionPerformed

    private void jButtonSuspenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuspenseActionPerformed
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCategoria");
        this.currentPage = 0;
        this.SelCat = "Suspense";
        jLabelTituloCategoria.setText(SelCat);
        popularCategoria();
    }//GEN-LAST:event_jButtonSuspenseActionPerformed

    private void jButtonTerrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerrorActionPerformed
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCategoria");
        this.currentPage = 0;
        this.SelCat = "Terror";
        jLabelTituloCategoria.setText(SelCat);
        popularCategoria();
    }//GEN-LAST:event_jButtonTerrorActionPerformed

    private void jButtonRomanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRomanceActionPerformed
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCategoria");
        this.currentPage = 0;
        this.SelCat = "Romance";
        jLabelTituloCategoria.setText(SelCat);
        popularCategoria();
    }//GEN-LAST:event_jButtonRomanceActionPerformed

    private void jButtonEducacionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEducacionalActionPerformed
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCategoria");
        this.currentPage = 0;
        this.SelCat = "Educacional";
        jLabelTituloCategoria.setText(SelCat);
        popularCategoria();
    }//GEN-LAST:event_jButtonEducacionalActionPerformed

    private void jButtonComediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComediaActionPerformed
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCategoria");
        this.currentPage = 0;
        this.SelCat = "Comédia";
        jLabelTituloCategoria.setText(SelCat);
        popularCategoria();
    }//GEN-LAST:event_jButtonComediaActionPerformed

    private void jButtonConteudoHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConteudoHistoricoActionPerformed
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCategoria");
        this.currentPage = 0;
        this.SelCat = "Fantasia";
        jLabelTituloCategoria.setText(SelCat);
        popularCategoria();
    }//GEN-LAST:event_jButtonConteudoHistoricoActionPerformed

    private void jButtonAventuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAventuraActionPerformed
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCategoria");
        this.currentPage = 0;
        this.SelCat = "Aventura";
        jLabelTituloCategoria.setText(SelCat);
        popularCategoria();
        
    }//GEN-LAST:event_jButtonAventuraActionPerformed

    private void jButtonRetornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetornarActionPerformed
        
        if (SelCat != null){
        
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCategoria");
        }
        
        if (PesquisaTexto != null){
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaPesquisa");    
        } else{
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaInicial");
        }
        
        
        
        
    }//GEN-LAST:event_jButtonRetornarActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        Login lg = new Login();
        lg.show();
        lg.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jButtonRetornar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetornar2ActionPerformed
       if (SelCat != null){
        
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaCategoria");
        } else {
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaInicial");
        }
    }//GEN-LAST:event_jButtonRetornar2ActionPerformed

    private void jButtonRetornar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetornar3ActionPerformed
        {
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaInicial");
        }
    }//GEN-LAST:event_jButtonRetornar3ActionPerformed

    private void jButtonHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistoricoActionPerformed
        
        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaHistorico");
        listarTabelaHistorico();
    }//GEN-LAST:event_jButtonHistoricoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        listarTabelaHistorico();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabelLivro9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro9MouseClicked
        String nome = jLabelTitLivro9.getText();
        setLivro(nome);
        
    }//GEN-LAST:event_jLabelLivro9MouseClicked

    private void jLabelLivro10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro10MouseClicked
        String nome = jLabelTitLivro10.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro10MouseClicked

    private void jLabelLivro11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro11MouseClicked
        String nome = jLabelTitLivro11.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro11MouseClicked

    private void jLabelLivro12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro12MouseClicked
        String nome = jLabelTitLivro12.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro12MouseClicked

    private void jLabelLivro13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro13MouseClicked
        String nome = jLabelTitLivro13.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro13MouseClicked

    private void jLabelLivro14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro14MouseClicked
        String nome = jLabelTitLivro14.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro14MouseClicked

    private void jLabelLivro15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro15MouseClicked
        String nome = jLabelTitLivro15.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro15MouseClicked

    private void jLabelLivro16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro16MouseClicked
        String nome = jLabelTitLivro16.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro16MouseClicked

    private void jLabelLivro15MouseCli(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro15MouseCli
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLivro15MouseCli

    private void jLabelLivro5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro5MouseClicked
        String nome = jLabelTitLivro5.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro5MouseClicked

    private void jLabelLivro6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro6MouseClicked
        String nome = jLabelTitLivro6.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro6MouseClicked

    private void jLabelLivro7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro7MouseClicked
        String nome = jLabelTitLivro7.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro7MouseClicked

    private void jLabelLivro8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro8MouseClicked
        String nome = jLabelTitLivro8.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro8MouseClicked

    private void jLabelLivro2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro2MouseClicked
        String nome = jLabelTitLivro2.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro2MouseClicked

    private void jLabelLivro3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro3MouseClicked
        String nome = jLabelTitLivro3.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro3MouseClicked

    private void jLabelLivro4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro4MouseClicked
        String nome = jLabelTitLivro4.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro4MouseClicked

    private void jLabelLivro1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro1MouseClicked
        String nome = jLabelTitLivro1.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro1MouseClicked

    private void jLabelLivro17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro17MouseClicked
        String nome = jLabelTitLivro17.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro17MouseClicked

    private void jLabelLivro18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro18MouseClicked
        String nome = jLabelTitLivro18.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro18MouseClicked

    private void jLabelLivro19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro19MouseClicked
        String nome = jLabelTitLivro19.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro19MouseClicked

    private void jLabelLivro20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro20MouseClicked
        String nome = jLabelTitLivro20.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro20MouseClicked

    private void jLabelLivro21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro21MouseClicked
        String nome = jLabelTitLivro21.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro21MouseClicked

    private void jLabelLivro22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro22MouseClicked
        String nome = jLabelTitLivro22.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro22MouseClicked

    private void jLabelLivro23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro23MouseClicked
        String nome = jLabelTitLivro23.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro23MouseClicked

    private void jLabelLivro24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLivro24MouseClicked
        String nome = jLabelTitLivro24.getText();
        setLivro(nome);
    }//GEN-LAST:event_jLabelLivro24MouseClicked

    private void jButtonProximaPagina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProximaPagina1ActionPerformed
         if (jLabelTitLivro24.getText() != null){
        this.currentPage += 1;
        popularPesquisa();}
    }//GEN-LAST:event_jButtonProximaPagina1ActionPerformed

    private void jButtonPaginaAnterior1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPaginaAnterior1ActionPerformed
        if (this.currentPage > 0){
            this.currentPage -= 1;
            popularPesquisa();
        }
    }//GEN-LAST:event_jButtonPaginaAnterior1ActionPerformed

    private void jTextFieldPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            
            this.PesquisaTexto = jTextFieldPesquisa.getText();
            this.SelCat = null;
            this.currentPage = 0;
            this.popularPesquisa();
        
        }
    }//GEN-LAST:event_jTextFieldPesquisaKeyPressed

    private void jLabelTitLivro18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelTitLivro18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelTitLivro18MouseClicked

    private void setLivro(String nome){
        Livro livro = this.livro.pesquisaPorNome(nome).get(0);
        jLabelTituloLivro.setText(livro.getNome());
        ManipularImagem.exibirImagemLabel(livro.getCapa(), jLabel10);
        jTextArea1.setText(livro.getResumo());
        jLabellNome.setText(livro.getNome());
        jLabelLAutor.setText(livro.getOPT_nm_autor());
        jLabelLData.setText(livro.getData());
        jLabelLCat.setText(livro.getOPT_nm_categoria());
        jLabelLId.setText(String.valueOf(livro.getId_Livro()));
        jLabelLDisponiveis.setText(String.valueOf(livro.getQuantidade()-livro.getAlugados()));

        CardLayout cl = (CardLayout) Screen.getLayout();
        cl.show(Screen, "TelaLivro");
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AreaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AreaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AreaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AreaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AreaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Screen;
    private javax.swing.JPanel SideMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAdcCarrinho;
    private javax.swing.JButton jButtonAventura;
    private javax.swing.JButton jButtonCarrinho;
    private javax.swing.JButton jButtonComedia;
    private javax.swing.JButton jButtonConteudoHistorico;
    private javax.swing.JButton jButtonEducacional;
    private javax.swing.JButton jButtonHistorico;
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonPaginaAnterior;
    private javax.swing.JButton jButtonPaginaAnterior1;
    private javax.swing.JButton jButtonPerfil;
    private javax.swing.JButton jButtonProximaPagina;
    private javax.swing.JButton jButtonProximaPagina1;
    private javax.swing.JButton jButtonRetornar;
    private javax.swing.JButton jButtonRetornar2;
    private javax.swing.JButton jButtonRetornar3;
    private javax.swing.JButton jButtonRomance;
    private javax.swing.JButton jButtonSuspense;
    private javax.swing.JButton jButtonTerror;
    private javax.swing.JComboBox<String> jCbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelGerenciamento;
    private javax.swing.JLabel jLabelLAutor;
    private javax.swing.JLabel jLabelLCat;
    private javax.swing.JLabel jLabelLData;
    private javax.swing.JLabel jLabelLDisponiveis;
    private javax.swing.JLabel jLabelLId;
    private javax.swing.JLabel jLabelLivro1;
    private javax.swing.JLabel jLabelLivro10;
    private javax.swing.JLabel jLabelLivro11;
    private javax.swing.JLabel jLabelLivro12;
    private javax.swing.JLabel jLabelLivro13;
    private javax.swing.JLabel jLabelLivro14;
    private javax.swing.JLabel jLabelLivro15;
    private javax.swing.JLabel jLabelLivro16;
    private javax.swing.JLabel jLabelLivro17;
    private javax.swing.JLabel jLabelLivro18;
    private javax.swing.JLabel jLabelLivro19;
    private javax.swing.JLabel jLabelLivro2;
    private javax.swing.JLabel jLabelLivro20;
    private javax.swing.JLabel jLabelLivro21;
    private javax.swing.JLabel jLabelLivro22;
    private javax.swing.JLabel jLabelLivro23;
    private javax.swing.JLabel jLabelLivro24;
    private javax.swing.JLabel jLabelLivro3;
    private javax.swing.JLabel jLabelLivro4;
    private javax.swing.JLabel jLabelLivro5;
    private javax.swing.JLabel jLabelLivro6;
    private javax.swing.JLabel jLabelLivro7;
    private javax.swing.JLabel jLabelLivro8;
    private javax.swing.JLabel jLabelLivro9;
    private javax.swing.JLabel jLabelPesquisa;
    private javax.swing.JLabel jLabelTitLivro1;
    private javax.swing.JLabel jLabelTitLivro10;
    private javax.swing.JLabel jLabelTitLivro11;
    private javax.swing.JLabel jLabelTitLivro12;
    private javax.swing.JLabel jLabelTitLivro13;
    private javax.swing.JLabel jLabelTitLivro14;
    private javax.swing.JLabel jLabelTitLivro15;
    private javax.swing.JLabel jLabelTitLivro16;
    private javax.swing.JLabel jLabelTitLivro17;
    private javax.swing.JLabel jLabelTitLivro18;
    private javax.swing.JLabel jLabelTitLivro19;
    private javax.swing.JLabel jLabelTitLivro2;
    private javax.swing.JLabel jLabelTitLivro20;
    private javax.swing.JLabel jLabelTitLivro21;
    private javax.swing.JLabel jLabelTitLivro22;
    private javax.swing.JLabel jLabelTitLivro23;
    private javax.swing.JLabel jLabelTitLivro24;
    private javax.swing.JLabel jLabelTitLivro3;
    private javax.swing.JLabel jLabelTitLivro4;
    private javax.swing.JLabel jLabelTitLivro5;
    private javax.swing.JLabel jLabelTitLivro6;
    private javax.swing.JLabel jLabelTitLivro7;
    private javax.swing.JLabel jLabelTitLivro8;
    private javax.swing.JLabel jLabelTitLivro9;
    private javax.swing.JLabel jLabelTituloCategoria;
    private javax.swing.JLabel jLabelTituloCategoria1;
    private javax.swing.JLabel jLabelTituloCategoria3;
    private javax.swing.JLabel jLabelTituloCategoria4;
    private javax.swing.JLabel jLabelTituloCategoriaTexto;
    private javax.swing.JLabel jLabelTituloCategoriaTexto1;
    private javax.swing.JLabel jLabelTituloCategoriaTexto2;
    private javax.swing.JLabel jLabelTituloCategoriaTexto3;
    private javax.swing.JLabel jLabelTituloCategoriaTexto4;
    private javax.swing.JLabel jLabelTituloLivro;
    private javax.swing.JLabel jLabellNome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelHistórico;
    private javax.swing.JPanel jPanelTelaCarrinho;
    private javax.swing.JPanel jPanelTelaCategoria;
    private javax.swing.JPanel jPanelTelaInicial;
    private javax.swing.JPanel jPanelTelaLivro;
    private javax.swing.JPanel jPanelTelaPerfil;
    private javax.swing.JPanel jPanelTelaPesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSeparator jSeparatorCat;
    private javax.swing.JSeparator jSeparatorGer;
    private javax.swing.JSeparator jSeparatorPes;
    private javax.swing.JTable jTableHistorico;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
