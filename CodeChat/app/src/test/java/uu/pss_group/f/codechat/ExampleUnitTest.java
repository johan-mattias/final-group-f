package uu.pss_group.f.codechat;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    SignUpActivity a;

    private void passwordReturnsNull(String entry)
    {
        assertFalse(a.isValidate(entry));
    }

    private void isOK(String entry){
        assertTrue(a.isValidate(entry)); // password is OK
    }

    @Test
    public void testPassword(){
        a = new SignUpActivity();

        // these passwords are  NOT OK
        passwordReturnsNull("qwertyuiopa");         // only lowercases
        passwordReturnsNull("12345678901");         // only lowercases
        passwordReturnsNull("QWERTYUIOPL");         // only uppercases
        passwordReturnsNull("userQwerty");          // no digits
        passwordReturnsNull("user");                // length
        passwordReturnsNull("usert12345678");       // no uppercase
        passwordReturnsNull("USER123456789");       // no lowercase

        // these passwords are oK
        isOK("Qwertyu90CD");
        isOK("12345678Cd");
    }

}