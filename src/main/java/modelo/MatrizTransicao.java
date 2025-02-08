
package modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class MatrizTransicao {
    
    private int [][] matriz;
    private int cols, rows;

    
    public MatrizTransicao() {
        this.loadMatrizTransicao();
        this.cols = 2;
        this.rows = 4;  
        this.matriz = new int [][] {{1,2},{3,2},{1,3}, {3,3}};        
    }
    
    public void loadMatrizTransicao(){
        try {
            FileReader file = new FileReader("MatrizTransicao.txt");
            Scanner arquivo = new Scanner(file);
            
            arquivo.useDelimiter("\n");
            String primeiraLinha = arquivo.next();            
            String[] vet = primeiraLinha.split(";");
            this.rows =  Integer.parseInt(vet[0]);
            this.cols =  Integer.parseInt(vet[1]);
            this.matriz = new int[this.rows][this.cols];
            
            ///Daqui pra baixo e dados da matriz
            String linhaCorrente = "";
            int linha = 0;
            while(arquivo.hasNext()){
                linhaCorrente = arquivo.next();
                vet = linhaCorrente.split(";");
                                
                for(int j=0; j < this.cols; j++){
                    int valor = Integer.parseInt(vet[j]);
                    this.matriz[linha][j] = valor;
                }
                
                linha++;
            }
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MatrizTransicao.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public int getCelula(int l, int c){        
        return this.matriz[l][c];                    
    }
    
    public void setCelula(int l, int c, int valor){
        this.matriz[l][c] = valor;
    }
    
    
    /**
     * @return the cols
     */
    public int getCols() {
        return cols;
    }

    /**
     * @param cols the cols to set
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    
    
    
    
    
}
