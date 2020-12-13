package put.io.testing.junit;

import org.junit.jupiter.api.*;

public class FailureOrErrorTest {
    @Test
    public void test1(){
        assert(false);
    }
    @Test
    public void test3() {
        try {
            assert (false);
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2() throws Exception {
        throw new Exception("abc");
    }

}
