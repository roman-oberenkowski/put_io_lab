package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {
 static AudiobookPriceCalculator tester;
 static Audiobook audiobook10;
 static Audiobook audiobook22;
    @BeforeAll
    static void setup(){
        tester =new AudiobookPriceCalculator();
        audiobook10=new Audiobook("test1",10.0);
        audiobook22=new Audiobook("test2",22.0);
    }
    @Test
    void testCalculateSub() {
        Customer con=new Customer("abc", Customer.LoyaltyLevel.STANDARD,true);
        assertEquals(tester.calculate(con,audiobook10),0.0,0.05);
        assertEquals(tester.calculate(con,audiobook22),0.0,0.05);
    }
    @Test
    void testCalculateStd() {
        Customer con=new Customer("abc", Customer.LoyaltyLevel.STANDARD,false);
        assertEquals(tester.calculate(con,audiobook10),10.0,0.05);
        assertEquals(tester.calculate(con,audiobook22),22.0,0.05);
    }
    @Test
    void testCalculateSilver() {
        Customer con=new Customer("abc", Customer.LoyaltyLevel.SILVER,false);
        assertEquals(tester.calculate(con,audiobook10),10.0*0.9,0.05);
        assertEquals(tester.calculate(con,audiobook22),22.0*0.9,0.05);
    }
    @Test
    void testCalculateGold() {
        Customer con=new Customer("abc", Customer.LoyaltyLevel.GOLD,false);
        assertEquals(tester.calculate(con,audiobook10),10.0*0.8,0.05);
        assertEquals(tester.calculate(con,audiobook22),22.0*0.8,0.05);
    }

}