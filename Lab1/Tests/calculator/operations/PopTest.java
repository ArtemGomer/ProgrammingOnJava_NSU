package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class PopTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        Pop pop = new Pop();
        context.getNumbers().push(3.14);
        context.getNumbers().push(5d);
        pop.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), 3.14, 1e-10);
    }

    @Test
    public void isValid() {
        Context context = new Context();
        Pop pop = new Pop();
        assertFalse(pop.isValid(context, new String[0]));
        context.getNumbers().push(6d);
        assertFalse(pop.isValid(context, new String[]{"a"}));
        assertTrue(pop.isValid(context, new String[0]));
    }
}