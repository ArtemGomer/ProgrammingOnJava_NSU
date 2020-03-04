package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqrtTest {

    @Test
    public void doOperation() throws CalculatorException {
        Context context = new Context();
        Sqrt sqrt = new Sqrt();
        context.pushValue(9d);
        sqrt.doOperation(context, new String[0]);
        assertEquals(context.getValue(), 3d, 1e-10);
    }

}