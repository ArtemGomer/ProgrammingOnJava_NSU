package calculator.operations;

import java.util.HashMap;

import java.util.Stack;

public class Context {
    private Stack<Double> numbers = new Stack<>();
    private HashMap<String, Double> variables = new HashMap<>();
    public boolean isDefined(String variable){
        return variables.containsKey(variable);
    }
    public Double popValue(){
        return numbers.pop();
    }
    public void pushValue(Double value){
        numbers.push(value);
    }
    public Double getVariableValue(String variable){
        return variables.get(variable);
    }
    public void setVariable(String variable, Double value){
        if (variables.containsKey(variable)) {
            variables.replace(variable, value);
        } else {
            variables.put(variable, value);
        }
    }
    public int getStackSize(){
        return numbers.size();
    }
    public Double getValue(){
        return numbers.peek();
    }
}
