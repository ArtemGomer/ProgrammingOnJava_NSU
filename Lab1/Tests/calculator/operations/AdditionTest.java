package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdditionTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        context.getNumbers().push(5d);
        Addition add = new Addition();
        add.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), 5d, 1e-10);
        context.getNumbers().push(6d);
        add.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), 11d, 1e-10);
    }
}