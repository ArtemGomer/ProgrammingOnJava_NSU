package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MultiplicationTest {

    @Test
    public void doOperation() throws CalculatorException {
        Context context = new Context();
        Multiplication multiplication = new Multiplication();
        context.pushValue(5d);
        multiplication.doOperation(context, new String[0]);
        assertEquals(5d, context.getValue(), 1e-10);
        context.pushValue(6d);
        multiplication.doOperation(context, new String[0]);
        assertEquals(30d, context.getValue(), 1e-10);
    }
}