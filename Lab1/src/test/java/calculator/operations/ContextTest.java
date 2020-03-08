package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContextTest {

    Context context = new Context();

    @Test
    public void setVariable() {
        context.setVariable("a", 5d);
        assertTrue(context.isDefined("a"));
        assertFalse(context.isDefined("b"));
        assertEquals(5d, context.getVariableValue("a"), 1e-10);
    }

    @Test
    public void getStackSize() {
        context.pushValue(7d);
        assertEquals(1, context.getStackSize());
        Double value = context.popValue();
        assertEquals(7d, value, 1e-10);
        assertEquals(0, context.getStackSize());
    }

    @Test
    public void getValue() {
        context.pushValue(5d);
        assertEquals(5d, context.getValue(), 1e-10);
        assertEquals(1, context.getStackSize());
    }
}