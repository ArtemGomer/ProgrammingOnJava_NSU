package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class MultiplicationTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        Multiplication multiplication = new Multiplication();
        context.getNumbers().push(5d);
        multiplication.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), 5d, 1e-10);
        context.getNumbers().push(6d);
        multiplication.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), 30d, 1e-10);
    }
}