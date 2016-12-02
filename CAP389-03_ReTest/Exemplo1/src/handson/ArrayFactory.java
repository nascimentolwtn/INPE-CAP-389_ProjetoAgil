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

        if(somaArray(result) != 0) {
        	searchCandidates(r, result);
        }
        
        return result;
    }

    @SuppressWarnings("unused")
	private static void searchCandidates(Random r, int[] result) {
		int randomIndex = randomBetweenArrayLenght(r, result.length);
		int randomCandidate = randomBetweenMinus10And10(r);

		int[] copyOfResult = null;
    	Integer somaArray = null;
		int tries = 1;
		do {
			copyOfResult = Arrays.copyOf(result, result.length);
			copyOfResult[randomIndex] = randomCandidate;
			somaArray = somaArray(copyOfResult);
			
			if (somaArray != 0) {
				if (somaArray < 0 && randomCandidate < 0 || somaArray > 0 && randomCandidate > 0) {
					randomCandidate *= -1;
					result[randomIndex] = randomCandidate;
				} 
				randomCandidate = randomBetweenMinus10And10(r);
				randomIndex = randomBetweenArrayLenght(r, result.length);
				tries++;
			}
			
		} while (somaArray != 0);
		
//		System.out.println(result.length + "=" + tries);
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
