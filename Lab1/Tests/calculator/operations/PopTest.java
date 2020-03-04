package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PopTest {

    @Test
    public void doOperation() throws CalculatorException {
        Context context = new Context();
        Pop pop = new Pop();
        context.pushValue(3.14);
        context.pushValue(5d);
        pop.doOperation(context, new String[0]);
        assertEquals(3.14, context.getValue(), 1e-10);
    }
}