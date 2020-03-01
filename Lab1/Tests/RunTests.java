import calculator.operations.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses( {AdditionTest.class, ContextTest.class, DefineTest.class, DivisionTest.class, MultiplicationTest.class,
PopTest.class, PrintTest.class, PushTest.class, SqrtTest.class, SubtractionTest.class})
@RunWith(Suite.class)
public class RunTests {
}
