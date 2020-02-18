package calculator.operations;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdditionTest {

    @Test
    public void doOperation() {
        Double value1 = 5d;
        Double value2 = 11d;
        Context context = new Context();
        context.getNumbers().push(5d);
        Addition add = new Addition();
        add.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), value1);
        context.getNumbers().push(6d);
        add.doOperation(context, new String[0]);
        assertEquals(context.getNumbers().peek(), value2);
    }
}