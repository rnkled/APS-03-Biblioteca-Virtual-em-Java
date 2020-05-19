/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomClass;

import biblioteca.Livro;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author kener_000
 */
public class ManipularLabels {
    
    public static void setLabels(List<Livro> livros, List<JLabel> labels, List<JLabel> titulos){
        
        int quantLivros = livros.size();
        int quantLabel = labels.size();
        int count = quantLivros;
        if (quantLivros>quantLabel){
            count = quantLabel;
        }
        if (quantLivros<quantLabel){
            count = quantLivros;
        }
        
        for(int i=0; i<count; i++){
            ManipularImagem.exibirImagemLabel(livros.get(i).getCapa(), labels.get(i), labels.get(i).getWidth(), labels.get(i).getHeight());
            titulos.get(i).setText(livros.get(i).getNome());
        }
    }
   
    
    public static void setLabelsPorPage(List<Livro> livros, List<JLabel> labels, List<JLabel> titulos, int page){
        
        int quantLabel = labels.size();
        int quantLivros = livros.size();
        int modPagina = quantLabel * page;
        
        int count = quantLivros;
        
        
        if (quantLivros<quantLabel-1){
            modPagina = 0;
        }
        
        if(quantLivros > quantLabel-1){
            if(quantLivros > quantLabel*(page+1)){
                count = quantLabel;
            } 
            if(quantLivros < quantLabel*(page+1)){
                count = quantLabel - (quantLabel*(page+1) - quantLivros);
            }
        }
        for(int i=0; i<count; i++){
            ManipularImagem.exibirImagemLabel(livros.get(i+modPagina).getCapa(), labels.get(i), labels.get(i).getWidth(), labels.get(i).getHeight());
            titulos.get(i).setText(livros.get(i+modPagina).getNome());

            
        }
        }
    
}
