package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrintTest {

    @Test
    public void doOperation(){
        Context context = new Context();
        Print print = new Print();
        context.pushValue(5d);
        try {
            print.doOperation(context, new String[0]);
        } catch (CalculatorException ignore){}
        assertEquals(5d, context.getValue(), 1e-10);
    }

}