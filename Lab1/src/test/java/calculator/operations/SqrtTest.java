package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqrtTest {

    @Test
    public void doOperation(){
        Context context = new Context();
        Sqrt sqrt = new Sqrt();
        context.pushValue(9d);
        try {
            sqrt.doOperation(context, new String[0]);
        } catch (CalculatorException ignore){}
        assertEquals(context.getValue(), 3d, 1e-10);
    }

}