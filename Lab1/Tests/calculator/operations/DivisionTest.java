package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class DivisionTest {

    @Test
    public void doOperation() {
        Context context = new Context();
        context.getNumbers().push(5d);
        Division division = new Division();
        division.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), new Double(5));
        context.getNumbers().push(6d);
        division.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), new Double(6/5d));
        context.getNumbers().push(0d);
        context.getNumbers().push(5d);
        division.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().pop(), new Double(5));
        assertEquals(context.getNumbers().pop(), new Double(0));
    }
}