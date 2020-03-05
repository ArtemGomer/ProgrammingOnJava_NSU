package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import calculator.calculatorExceptions.WrongArgumentsException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PushTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        Push push = new Push();
        context.setVariable("a", 3.14);
        try {
            push.doOperation(context, new String[]{"ab"});
        } catch (CalculatorException exc){}
        assertEquals(0, context.getStackSize());
        try {
            push.doOperation(context, new String[]{"a"});
        } catch (CalculatorException exc){}
        assertEquals(3.14, context.getValue(), 1e-10);
        try {
            push.doOperation(context, new String[]{"123"});
        } catch (CalculatorException exc){}
        assertEquals(123d, context.getValue(), 1e-10);
    }
}