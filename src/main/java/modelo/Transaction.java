package modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private String currentState;
    private char letterConsumed;
    private char letterToUnstack;
    private char letterToStack;
    private String nextState;
}