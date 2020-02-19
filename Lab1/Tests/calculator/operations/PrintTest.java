package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrintTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        Print print = new Print();
        context.pushValue(5d);
        print.doOperation(context, new String[0]);
        assertEquals(5d, context.getValue(), 1e-10);
    }

    @Test
    public void isValid() {
        Context context = new Context();
        Print print = new Print();
        assertFalse(print.isValid(context, new String[0]));
        context.pushValue(6d);
        assertFalse(print.isValid(context, new String[]{"a"}));
        assertTrue(print.isValid(context, new String[0]));
    }
}