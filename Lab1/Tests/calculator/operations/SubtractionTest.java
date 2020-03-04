package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubtractionTest {

    @Test
    public void doOperation() throws CalculatorException {
        Context context = new Context();
        context.pushValue(5d);
        Subtraction subtraction = new Subtraction();
        subtraction.doOperation(context, new String[0]);
        assertEquals(context.getValue(), 5d, 1e-10);
        context.pushValue(6d);
        subtraction.doOperation(context, new String[0]);
        assertEquals(context.getValue(), 1d, 1e-10);
    }
}