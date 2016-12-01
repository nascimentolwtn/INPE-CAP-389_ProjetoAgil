package test.exemplo;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.retest.ReTestRunner;
import org.retest.annotation.LoadTestFromDataFiles;
import org.retest.annotation.ReTest;
import org.retest.annotation.SaveBrokenTestDataFiles;
import org.retest.annotation.SaveSuccessTestDataFiles;
import org.retest.annotation.params.RandomParam;

@RunWith(ReTestRunner.class)
public class TestRunner {

	private static final String TEMP_DIR = ".\\temp";

	public TestRunner() {
    }

    @Test
    public void testA() throws Exception {
        int x = 8;
        int y = 2;
        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    public void testB() throws Exception {
        int x = 9;
        int y = 2;
        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    public void testC(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        System.out.println("X = " + x);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @ReTest(3)
    public void testD(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @ReTest(3)
    @SaveBrokenTestDataFiles(filePath = TEMP_DIR)
    public void testE(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @ReTest(3)
    @SaveBrokenTestDataFiles(filePath = TEMP_DIR)
    @LoadTestFromDataFiles(filePath = {TEMP_DIR + "\\testC_BrokenTest.csv"})
    public void testF(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @ReTest(3)
    @SaveBrokenTestDataFiles(filePath = TEMP_DIR)
    @SaveSuccessTestDataFiles(filePath = TEMP_DIR)
    @LoadTestFromDataFiles(filePath = {TEMP_DIR + "\\testG_BrokenTest.csv", TEMP_DIR + "\\testG_SuccessTest.csv"})
    public void testG(@RandomParam Random r1) throws Exception {
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }

    @Test
    @SaveBrokenTestDataFiles(filePath = TEMP_DIR)
    @SaveSuccessTestDataFiles(filePath = TEMP_DIR)
    //@LoadTestFromDataFiles(filePath = {TEMP_DIR + "\\testH_BrokenTest.csv", TEMP_DIR + "\\testH_SuccessTest.csv"})
    public void testH() throws Exception {
        Random r1 = new Random();
        int x = r1.nextInt(100);
        int y = 2;

        float z = DivisionCalc.calc(x, y);
        float j = z * y;
        assertEquals(j, x, 0);
    }
}
