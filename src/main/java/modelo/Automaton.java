package modelo;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class Automaton {
    private Set<String> finalStates;
    private List<Transaction> transactionList;

    public Automaton() {
        finalStates = new HashSet<>();
        transactionList = new ArrayList<>();

        try {
            readTransactionFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readTransactionFile() throws IOException {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("TransactionList.txt");

            if (inputStream == null) {
                throw new FileNotFoundException("Arquivo TransactionList.txt não encontrado no classpath!");
            }

            Scanner arquivo = new Scanner(inputStream);

            arquivo.useDelimiter(System.lineSeparator());
            finalStates.addAll(Arrays.asList(arquivo.next().split(";")));

            String[] vet;
            String linhaCorrente = "";
            while (arquivo.hasNext()) {
                linhaCorrente = arquivo.next();
                vet = linhaCorrente.split(";");

                transactionList.add(new Transaction(vet[0], vet[1].charAt(0), vet[2].charAt(0), vet[3].charAt(0), vet[4]));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Automaton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean acceptSentence(String palavra) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        for (char c : palavra.toCharArray()) queue.add(c);

        return process("q0", queue, stack);
    }

    private boolean process(String currentState, Queue<Character> queue, Stack<Character> stack) {
        if (queue.isEmpty() && finalStates.contains(currentState)) {
            return true; // aceita palavra se estiver vazia e o estado atual for final
        }

        for (Transaction transaction : transactionList) {
            if (!transaction.getCurrentState().equals(currentState)) // verificar se o estado atual é o mesmo da linha da lista
                continue;

            boolean canConsume = transaction.getLetterConsumed() == '-' ||
                    (!queue.isEmpty() && transaction.getLetterConsumed() == queue.peek()) ||
                    (transaction.getLetterConsumed() == '?' && queue.isEmpty());

            boolean canUnstack = transaction.getLetterToUnstack() == '-' ||
                    (!stack.isEmpty() && transaction.getLetterToUnstack() == stack.peek()) ||
                    (transaction.getLetterToUnstack() == '?' && stack.isEmpty());

            if (!canConsume || !canUnstack)
                continue;

            Queue<Character> newQueue = new LinkedList<>(queue);
            Stack<Character> newStack = (Stack<Character>) stack.clone();

            if (transaction.getLetterConsumed() != '-' && transaction.getLetterConsumed() != '?')
                newQueue.poll();

            if (transaction.getLetterToUnstack() != '-' && transaction.getLetterToUnstack() != '?')
                newStack.pop();

            if (transaction.getLetterToStack() != '-')
                newStack.push(transaction.getLetterToStack());

            if (process(transaction.getNextState(), newQueue, newStack)) {
                return true;
            }

        }

        return false;
    }
}