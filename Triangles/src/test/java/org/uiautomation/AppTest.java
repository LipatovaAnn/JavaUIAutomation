package org.uiautomation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.uiautomation.App.getSquareOfTriangle;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @ParameterizedTest
    @CsvSource({"3, 3, 3", "4, 5, 3", "3, 3, 4"})
    public void shouldReturnValue(int a, int b, int c) {
        try {
            double v = getSquareOfTriangle(a, b, c);
            Assert.assertTrue("S should bew greater than zero",v > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @ParameterizedTest
    @CsvSource({"1,2,3", "1,1,2", "2,2,4"})
    public void shouldThrowException(int a, int b, int c) {
        Throwable exception = Assert.assertThrows(NotTrinagleException.class, () -> {
            getSquareOfTriangle(a, b, c);
        });

        Assert.assertEquals(exception.getMessage(),"Can't create triangle");
    }
}
