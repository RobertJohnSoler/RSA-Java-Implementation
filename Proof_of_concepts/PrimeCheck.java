package Proof_of_concepts;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

/* 
This class is a simple proof-of-concept code that asks for any integer and returns whether it is prime or not. 
It uses Fermat's Primality test to check for primes.
*/

public class PrimeCheck {
	public static void main (String [] args) {
		
		Scanner input = new Scanner(System.in);
		try {
			while (true) {
				System.out.print("Enter an integer: ");
				BigInteger number = input.nextBigInteger();
				boolean prime = false;
				
				float s = 150F;  
				// s is what we call "security parameter". 
				// A higher value lessens the chances of the Fermality Primality test failing on a non-prime.

				long start = System.currentTimeMillis();
				
				// Fermat's Primality check starts here:
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
				} else {
					System.out.print(number + " is not prime!");
				}
				long end = System.currentTimeMillis();
				float runtime = (end-start)/1000F;
				System.out.println(" ----- Runtime is: " + runtime +" seconds." );
			}
			
		} finally {
			input.close();
		}
	}
}
