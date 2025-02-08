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
}