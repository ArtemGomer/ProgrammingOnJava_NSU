package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class PushTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        Push push = new Push();
        context.setVariable("a", 3.14);
        push.doOperation(context, new String[]{"ab"});
        assertEquals(0, context.getStackSize());
        push.doOperation(context, new String[]{"a"});
        assertEquals(3.14, context.getValue(), 1e-10);
        push.doOperation(context, new String[]{"123"});
        assertEquals(123d, context.getValue(), 1e-10);
    }
}