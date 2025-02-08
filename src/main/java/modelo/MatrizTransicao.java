
package modelo;

import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrizTransicao {

    private int [][] matriz;

    @Getter
    @Setter
    private int cols, rows;
    
    public MatrizTransicao() {
        this.loadMatrizTransicao();
        this.cols = 2;
        this.rows = 4;
        this.matriz = new int [][] {};
    }
    
    public void loadMatrizTransicao() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("MatrizTransicao.txt");

            if (inputStream == null) {
                throw new FileNotFoundException("Arquivo MatrizTransicao.txt não encontrado no classpath!");
            }

            Scanner arquivo = new Scanner(inputStream);

            arquivo.useDelimiter("\n");
            String primeiraLinha = arquivo.next();
            String[] vet = primeiraLinha.split(";");
            this.rows = Integer.parseInt(vet[0]);
            this.cols = Integer.parseInt(vet[1]);
            this.matriz = new int[this.rows][this.cols];

            String linhaCorrente = "";
            int linha = 0;
            while (arquivo.hasNext()) {
                linhaCorrente = arquivo.next();
                vet = linhaCorrente.split(";");

                for (int j = 0; j < this.cols; j++) {
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
}
