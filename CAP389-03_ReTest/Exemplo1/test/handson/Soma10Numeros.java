package test.handson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.retest.ReTestRunner;
import org.retest.annotation.LoadTestFromDataFiles;
import org.retest.annotation.ReTest;
import org.retest.annotation.SaveBrokenTestDataFiles;
import org.retest.annotation.params.RandomParam;

import src.handson.ArrayFactory;

@RunWith(ReTestRunner.class)
public class Soma10Numeros {

	private static final String TEMP_DIR = ".\\temp";

	@Test
    public void test1(@RandomParam Random r) throws InterruptedException {
        int[] result = ArrayFactory.gerarArrayComSomatoriaZero(1, r);
		assertArrayComSomatoriaZero(1, result);
    }

    @Test
    @ReTest(10)
    @SaveBrokenTestDataFiles(filePath = TEMP_DIR)
    @LoadTestFromDataFiles(filePath = {TEMP_DIR + "\\testArrayDeTamanho2.csv"})
    public void testArrayDeTamanho2(@RandomParam Random r) throws InterruptedException {
        int tamanhoArray = 2;
		int[] result = ArrayFactory.gerarArrayComSomatoriaZero(tamanhoArray, r);
		assertArrayComSomatoriaZero(tamanhoArray, result);
    }

    @Test
    @ReTest(10)
    @SaveBrokenTestDataFiles(filePath = TEMP_DIR)
    @LoadTestFromDataFiles(filePath = {TEMP_DIR + "\\testArrayDeTamanho3.csv"})
    public void testArrayDeTamanho3(@RandomParam Random r) throws InterruptedException {
        int tamanhoArray = 3;
		int[] result = ArrayFactory.gerarArrayComSomatoriaZero(tamanhoArray, r);
		assertArrayComSomatoriaZero(tamanhoArray, result);
    }

    @Test
    @ReTest(10)
    @SaveBrokenTestDataFiles(filePath = TEMP_DIR)
    @LoadTestFromDataFiles(filePath = {TEMP_DIR + "\\testArrayDeTamanho10.csv"})
    public void testArrayDeTamanho10(@RandomParam Random r) throws InterruptedException {
        int tamanhoArray = 10;
		int[] result = ArrayFactory.gerarArrayComSomatoriaZero(tamanhoArray, r);
		assertArrayComSomatoriaZero(tamanhoArray, result);
    }

    @Test
    @ReTest(10)
    @SaveBrokenTestDataFiles(filePath = TEMP_DIR)
    @LoadTestFromDataFiles(filePath = {TEMP_DIR + "\\testArrayDeTamanho100.csv"})
    public void testArrayDeTamanho100(@RandomParam Random r) throws InterruptedException {
        int tamanhoArray = 100;
		int[] result = ArrayFactory.gerarArrayComSomatoriaZero(tamanhoArray, r);
		assertArrayComSomatoriaZero(tamanhoArray, result);
    }

	private void assertArrayComSomatoriaZero(int tamanhoArray, int[] result) {
		assertEquals(tamanhoArray, result.length);
		int soma = 0;
		for (int i : result) {
			assertTrue(i > -10 && i < 10);
			soma  += i;
		}
		assertEquals(0, soma);
	}
	
}
