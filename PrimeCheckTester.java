import java.math.BigInteger;
import java.security.SecureRandom;

public class PrimeCheckTester {
	public static void main (String [] args) {
		
		int [] carmichaels = {561,625,637,729,841,891,961,1105,1125,1225,1331,1369,1377,1681,1729,1849,2025,2187,2197,2209,2401,2465,
				2809,2821,3125,3321,3481,3645,3721,3751,3825,4225,4489,4913,4961,5041,5329,5589,5625,6241,6517,6525,6561,6601,6859,6889,
				7381,7921,8125,8281,8625,8911,9409,9801,10125,10201,10585,10609,10625,11449,11881,12025,12167,12769,13357,13833,14161,
				14641,15625,15841,15925,16129,16807,17161,18225,18769,19321,19683,21141,22201,22801,23409,23805,24389,24649,25425,26353,
				26569,27889,28033,28125,28561,29341,29791,29929,30625,31213,32041,32761,32805};
		int falsePositives = 0;
		double runtimeTotal = 0;
		for (int j = 0; j < carmichaels.length; j++) {
			BigInteger number = BigInteger.valueOf(carmichaels[j]);
			boolean prime = false;
			
			float s = 150F;
			double start = System.nanoTime();
			
			for (int i = 1; i <= s; i++) {
				if (number.compareTo(BigInteger.valueOf(4)) == 0) {
					break;
				} else if (number.compareTo(BigInteger.valueOf(4)) < 0) {
					prime = true;
					break;
				}
				
				// The following few lines are the BigInteger way of doing the formula a = min + (Math.random() * ((max) - min))
				// Unfortunately, doing it in one line is cancer.
				BigInteger max = number.subtract(BigInteger.valueOf(2));
				BigInteger range = max.subtract(BigInteger.valueOf(2));
		        SecureRandom random = new SecureRandom();
		        // Generate a random BigInteger within the specified range
		        BigInteger randrange = new BigInteger(range.bitLength(), random).mod(range);
				BigInteger a = randrange.add(BigInteger.valueOf(2));
				BigInteger primecheck = a.modPow(number.subtract(BigInteger.valueOf(1)), number);
				if (primecheck.compareTo(BigInteger.valueOf(1)) != 0) {
					prime = false;
					break;
				}
				prime = true;
			 }
			
			if (prime == true) {
				System.out.print(number + " is prime!");
				falsePositives++;
			} else {
				System.out.print(number + " is not prime!");
			}
			double end = System.nanoTime();
			double runtime = (end-start)/1000F;
			System.out.printf(" ----- Runtime is: \"%.6f\" milliseconds. \n" , runtime);
			if (j != 0) {
				runtimeTotal = runtimeTotal+runtime;
			}
		}
		double avgRuntime = (runtimeTotal/100F);
		System.out.println("Carmichael Number Test summary: ");
		System.out.println("Average runtime: " + avgRuntime + " ms");
		System.out.println("False positives: " + falsePositives);
	}
}
