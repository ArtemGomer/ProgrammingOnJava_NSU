package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class DefineTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        Define define = new Define();
        Double value = 6d;
        define.doOperation(context, new String[]{"a", "a"});
        assertFalse(context.getVariables().containsKey("a"));
        define.doOperation(context, new String[]{"6", "a"});
        assertFalse(context.getVariables().containsKey("6"));
        define.doOperation(context, new String[]{"6", "6"});
        assertFalse(context.getVariables().containsKey("6"));
        define.doOperation(context, new String[]{"a", "6"});
        assertTrue(context.getVariables().containsKey("a"));
        assertEquals(context.getVariables().get("a"), value);
    }
}