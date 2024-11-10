package Proof_of_concepts;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

/* 
This class is a simple proof-of-concept code that generates prime numbers depending on the size defined by the user.
It follows the following logic:
    1. Ask the user for the size of the number (in bits)
    2. Generate a random number of the given size
    3. Use Fermat's primality check on the generated number. If it is prime, return it. If not, add 1.
    4. Repeat step 3 until the number becomes prime.
*/

public class PrimeGen {
	
	public static void main (String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of bits: ");
		int numbits = input.nextInt();
		SecureRandom rand = new SecureRandom();
		BigInteger bigRand = new BigInteger(numbits, rand);
		while (primeCheck(bigRand) != true) {
			bigRand = bigRand.add(BigInteger.valueOf(1));
		}
		System.out.println(bigRand);
		input.close();
	}
	

    // Static function to check if a number is prime using Fermat's Primality test.
	public static boolean primeCheck (BigInteger number) {

        boolean prime = false;
		float s = 150F;
        // s is what we call "security parameter". 
        // A higher value lessens the chances of the Fermality Primality test failing on a non-prime.
    
		for (int i = 1; i <= s; i++) {
			if (number.compareTo(BigInteger.valueOf(4)) == 0) {
				break;
			} else if (number.compareTo(BigInteger.valueOf(4)) < 0) {
				prime = true;
				break;
			}
			
			// The following few lines are the BigInteger way of doing the formula a = 2 + (Math.random() * ((number - 2) - 2))
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
		
		return prime;
	}
}
