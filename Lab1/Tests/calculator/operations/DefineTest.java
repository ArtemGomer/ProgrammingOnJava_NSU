package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class DefineTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        Define define = new Define();
        define.doOperation(context, new String[]{"a", "a"});
        assertFalse(context.isDefined("a"));
        define.doOperation(context, new String[]{"6", "a"});
        assertFalse(context.isDefined("6"));
        define.doOperation(context, new String[]{"6", "6"});
        assertFalse(context.isDefined("6"));
        define.doOperation(context, new String[]{"a", "6"});
        assertTrue(context.isDefined("a"));
        assertEquals(context.getVariableValue("a"), 6d, 1e-10);
    }
}