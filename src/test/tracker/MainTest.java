package tracker;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.Main.*;


class MainTest {

    @ParameterizedTest(name = "{index} => check valid name {0}")
    @ValueSource(strings = {"ab", "O'Tony"})
    void testValidName(String s) {
        assertTrue(verifyFirstName(s));
    }

    @ParameterizedTest(name = "{index} => check invalid name {0}")
    @ValueSource(strings = {"a", "ab1", "Антон", "Tony?", "Tony--", "Tony''", "'Tony", "-Tony",
            "Tony-", "O-'Tony", "O'-Tony"})
    void testInvalidName(String s) {
        assertFalse(verifyFirstName(s));
    }

    @ParameterizedTest(name = "{index} => check valid name {0}")
    @ValueSource(strings = {"tony@google.com", "to1ny22@google.com", "tony@google.com22", "tony.true@google.com"})
    void testEmailValid(String s) {
        assertTrue(verifyEmail(s));
    }

    @ParameterizedTest(name = "{index} => check invalid name {0}")
    @ValueSource(strings = {"@google.com", "tonygoogle.com", "tony@.com", "tony@google.", "tony@google"})
    void testEmailInvalid(String s) {
        assertFalse(verifyEmail(s));
    }

    @ParameterizedTest(name = "{index} => check valid points")
    @ValueSource(strings = {"1 1 1 1 1", "asd 11 12 13 0"})
    void testPointsValid(String s) {
        assertTrue(isValidPoints(s));
    }

    @ParameterizedTest(name = "{index} => check invalid points")
    @ValueSource(strings = {"1 -1 1 1 1", "asd asd 12 13 0"})
    void testPointsInvalid(String s) {
        assertFalse(isValidPoints(s));
    }
}