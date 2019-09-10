import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseToDoTest() throws Exception {
        Parser pa = new Parser("todo watching anime");
        assertEquals(pa.parseWord(), "todo");
        assertEquals(pa.parseToDo().toString(), "[\u2717]watching anime");
    }
}