package main;

public class Main {
    public static void main(String[] args) {
        Automato linguagemAABB = new Automato();

        if (linguagemAABB.verificaSentenca("ababababa")) {
            System.out.println("Aceita");
        } else {
            System.out.println("Rejeita");
        }
    }
}