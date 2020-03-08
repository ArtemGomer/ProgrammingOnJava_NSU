package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubtractionTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        context.pushValue(5d);
        Subtraction subtraction = new Subtraction();
        try {
            subtraction.doOperation(context, new String[0]);
        } catch (CalculatorException ignore){}
        assertEquals(context.getValue(), 5d, 1e-10);
        context.pushValue(6d);
        try {
            subtraction.doOperation(context, new String[0]);
        } catch (CalculatorException ignore){}
        assertEquals(context.getValue(), 1d, 1e-10);
    }
}