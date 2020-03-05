package calculator.operations;

import calculator.calculatorExceptions.CalculatorException;
import calculator.calculatorExceptions.WrongArgumentsException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefineTest {

    @Test
    public void doOperation(){
        Context context = new Context();
        Define define = new Define();

        try {
            define.doOperation(context, new String[]{"a", "a"});
        } catch (CalculatorException exc) { }
        assertFalse(context.isDefined("a"));
        try {
            define.doOperation(context, new String[]{"6", "a"});
        } catch (CalculatorException exc) { }
        assertFalse(context.isDefined("6"));
        try {
            define.doOperation(context, new String[]{"6", "6"});
        } catch (CalculatorException exc) { }
        assertFalse(context.isDefined("6"));
        try {
            define.doOperation(context, new String[]{"a", "6"});
        } catch (CalculatorException exc) { }
        assertTrue(context.isDefined("a"));
        assertEquals(context.getVariableValue("a"), 6d, 1e-10);
    }
}