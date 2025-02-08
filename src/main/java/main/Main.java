package main;

import modelo.Automaton;
import modelo.Transaction;

public class Main {
    public static void main(String[] args) {
        Automaton a = new Automaton();

        System.out.println(a.getFinalStates());

        for(Transaction t : a.getTransactionList()) {
            System.out.println(t.getCurrentState() + ";" + t.getLetterConsumed()+ ";" + t.getLetterToUnstack()+ ";" + t.getLetterToStack()+ ";" + t.getNextState());
        }

        System.out.println(a.acceptSentence("ab"));
    }
}