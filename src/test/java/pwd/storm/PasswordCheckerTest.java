package pwd.storm;


import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class PasswordCheckerTest {
    @Autowired
    private PasswordChecker passwordChecker;

    @Test
    public void testPassword() {

        // Positive tests
        SUCCESS("abcd1");
        SUCCESS("1234z");
        SUCCESS("wordbyword9");

        // Negative tests
        FAIL("");
        FAIL("a");
        FAIL("1");
        FAIL("abc1");
        FAIL("123a");
        FAIL("aabced1");
        FAIL("abcde11");
        FAIL("wordwordletter1");
        FAIL("@abcjef123");
        FAIL("ABCabc12");
    }

    private void SUCCESS(String input) {
        String result = passwordChecker.doTask(input);
        assertTrue(PasswordChecker.PASSED.equals(result));
    }

    private void FAIL(String input) {
        String result = passwordChecker.doTask(input);
        assertTrue(!PasswordChecker.PASSED.equals(result));
    }
}
