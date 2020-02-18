package calculator.operations;

import java.util.HashMap;

import java.util.Stack;

public class Context {
    private Stack<Double> numbers = new Stack<>();
    private HashMap<String, Double> variables = new HashMap<>();
    public Stack<Double> getNumbers(){
        return numbers;
    }
    public HashMap<String, Double> getVariables(){
        return variables;
    }
}
