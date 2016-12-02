package src.handson;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author andreivo
 */
public class ArrayFactory {

    public static int[] gerarArrayComSomatoriaZero(int n, Random r) {
        int[] result = new int[n];
        
        for (int i : result) {
        	result[i] = randomBetweenMinus10And10(r);
		}
        
        for (int i = 0; i < result.length; i++) {
        	int randomIndex = randomBetweenArrayLenght(r, result.length);
        	int tries = 1;
			searchCandidates(r, result, randomIndex, tries);
		}
        
        return result;
    }

	private static void searchCandidates(Random r, int[] result, int randomIndex, int tries) {
		int randomCandidate = randomBetweenMinus10And10(r);
    	int[] copyOfResult = Arrays.copyOf(result, result.length);
		int somaArray = somaArray(copyOfResult);
		if(somaArray == 0)
			return;
		while(somaArray != 0) {
			randomCandidate = randomBetweenMinus10And10(r);
			copyOfResult[randomIndex] = randomCandidate;
			
			somaArray = somaArray(copyOfResult);
			System.out.println(tries++);
//			if(somaArray != 0) {
//				searchCandidates(r, result, randomIndex, ++tries);
//			}
		}
		result[randomIndex] = randomCandidate;
	}

	private static int randomBetweenMinus10And10(Random r) {
		return r.nextInt(21) - 10;
	}

	private static int randomBetweenArrayLenght(Random r, int arrayLenght) {
		return r.nextInt(arrayLenght);
	}

	private static int somaArray(int[] result) {
		int soma = 0;
		for (int i : result) {
			soma+=i;
		}
		return soma;
	}
}
