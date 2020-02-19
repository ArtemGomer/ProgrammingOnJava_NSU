package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class PushTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        Push push = new Push();
        context.getVariables().put("a", 3.14);
        push.doOperation(context, new String[]{"ab"});
        assertTrue(context.getNumbers().isEmpty());
        push.doOperation(context, new String[]{"a"});
        assertEquals(context.getNumbers().peek(), 3.14, 1e-10);
        push.doOperation(context, new String[]{"123"});
        assertEquals(context.getNumbers().peek(), 123d, 1e-10);
    }
}