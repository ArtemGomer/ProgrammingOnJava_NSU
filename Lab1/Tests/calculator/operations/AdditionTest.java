package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdditionTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        context.pushValue(5d);
        Addition add = new Addition();
        try{
            add.doOperation(context, new String[0]);
        }
        catch(CalculatorException exc){}
        assertEquals(5d, context.getValue(), 1e-10);
        context.pushValue(6d);
        try{
            add.doOperation(context, new String[0]);
        }
        catch(CalculatorException exc){}
        assertEquals(11d, context.getValue(), 1e-10);
    }
}