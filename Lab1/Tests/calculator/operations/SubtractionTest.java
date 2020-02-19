package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubtractionTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        context.getNumbers().push(5d);
        Subtraction subtraction = new Subtraction();
        subtraction.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), 5d, 1e-10);
        context.getNumbers().push(6d);
        subtraction.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), 1d, 1e-10);
    }
}