package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DivisionTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        context.pushValue(5d);
        Division division = new Division();
        try {
            division.doOperation(context, new String[0]);
        } catch (CalculatorException ignore) { }
        assertEquals(5d, context.getValue(), 1e-10);
        context.pushValue(6d);
        try {
            division.doOperation(context, new String[0]);
        } catch (CalculatorException ignore) { }
        assertEquals(6 / 5d, context.getValue(), 1e-10);
        context.pushValue(0d);
        context.pushValue(5d);
        try {
            division.doOperation(context, new String[0]);
        } catch (CalculatorException ignore) { }
        assertEquals(5d, context.popValue(), 1e-10);
        assertEquals(0d, context.popValue(), 1e-10);
    }
}