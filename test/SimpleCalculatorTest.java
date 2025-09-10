import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    @Test
    void sixPlusSevenEqualsThirteen(){
        SimpleCalculator calculator = new SimpleCalculator();
        assertEquals(13, calculator.add(6, 7));
    }
    @Test
    void sixPlusSevenISNot67(){
        SimpleCalculator calculator = new SimpleCalculator();
        assertNotEquals(67, calculator.add(6, 7));
    }
}