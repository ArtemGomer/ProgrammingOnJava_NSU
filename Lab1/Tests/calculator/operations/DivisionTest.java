package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class DivisionTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        context.pushValue(5d);
        Division division = new Division();
        division.doOperation(context, new String[0]);
        assertEquals(5d, context.getValue(), 1e-10);
        context.pushValue(6d);
        division.doOperation(context, new String[0]);
        assertEquals(6/5d, context.getValue(), 1e-10);
        context.pushValue(0d);
        context.pushValue(5d);
        division.doOperation(context, new String[0]);
        assertEquals(5d, context.popValue(), 1e-10);
        assertEquals(0d, context.popValue(), 1e-10);
    }
}