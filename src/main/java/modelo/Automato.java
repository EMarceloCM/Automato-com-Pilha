
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose
 */
public class Automato {

    private String sentenca;
    private MatrizTransicao matriz;
    private int estadoInicial;
    private List<Integer> estadosFinais;

    public Automato() {
        this.sentenca = "";
        this.estadoInicial = 0;        
        this.estadosFinais = new ArrayList<>();
        this.estadosFinais.add(3);
        this.matriz = new MatrizTransicao();
        this.matriz.loadMatrizTransicao();
    }

    public boolean verificaSentenca(String sentenca) {
        this.sentenca = sentenca;
        int estadoAtual = this.estadoInicial;

        //Lembrar de converter sentenca!
        String nova = this.converterSentenca();
        
        int i = 0;
        while (i <= this.sentenca.length() - 1 && estadoAtual != -1) {
            int col = Integer.parseInt(nova.charAt(i)+"");
            estadoAtual = this.matriz.getCelula(estadoAtual, col);

            i++;
        }

        return estadoAtual != -1 && isFinal(estadoAtual);
    }
    
    public String converterSentenca(){
        StringBuilder aux = new StringBuilder();
        
        for(int i=0; i<=this.sentenca.length()-1;i++){
            aux.append(this.alfabe2Index(this.sentenca.charAt(i)));
        }
        return aux.toString();
    }            

    private boolean isFinal(int estado) {
        return this.estadosFinais.contains(estado);               
    }

    private char alfabe2Index(char m) {
        return switch (m) {
            case 'a' -> '0';
            case 'b' -> '1';
            default -> '-';
        };
    }
    
    //Não estamos usando este método
    public int estados2Index(String estado) {
        char aux = '-';
        return switch (estado) {
            case "q0" -> 0;
            case "q1" -> 1;
            default -> -1;
        };
    }
}
