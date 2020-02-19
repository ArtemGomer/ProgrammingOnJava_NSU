package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class SqrtTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        Sqrt sqrt = new Sqrt();
        context.pushValue(9d);
        sqrt.doOperation(context, new String[0]);
        assertEquals(context.getValue(), 3d, 1e-10);
    }

    @Test
    public void isValid() {
        Context context = new Context();
        Sqrt sqrt = new Sqrt();
        context.pushValue(-9d);
        assertFalse(sqrt.isValid(context, new String[0]));
        assertFalse(sqrt.isValid(context, new String[]{"a", "b"}));
        context.pushValue(9d);
        sqrt.doOperation(context, new String[0]);
        assertEquals(context.getValue(), 3d, 1e-10);
    }
}