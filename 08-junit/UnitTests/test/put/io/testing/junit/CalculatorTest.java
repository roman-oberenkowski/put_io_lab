package put.io.testing.junit;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static Calculator tester;
    @BeforeEach //beforeallteż zadziała, bo klasa kalkulator jest bezstanowa
    static void setup() {
        tester = new Calculator();
    }

    @Test
    public void testAdding(){
        assertEquals(8, tester.add(3,5));
        assertEquals(4,tester.add(6,-2));
    }

    @Test
    public void testMul(){
        assertEquals(25,tester.multiply(5,5));
        assertEquals(0,tester.multiply(0,693));
    }
    @Test
    public void testAddPosNum(){
        assertThrows(IllegalArgumentException.class,()->{
            tester.addPositiveNumbers(5,-5);
        });
        assertEquals(12,tester.addPositiveNumbers(5,7));
    }


}