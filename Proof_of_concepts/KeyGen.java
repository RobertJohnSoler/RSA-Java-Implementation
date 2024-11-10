package Proof_of_concepts;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

	/**
    This code generates a public and private key pair using the RSA algorithm and Fermat's Primality test. 
    Refer to PrimeCheck.java and PrimeGen.java for more details on checking and generating prime numbers.
    The RSA algorithm is as follows:
        1. Choose two primes p and q.
        2. Compute n = p*q
        3. Compute phi(n) = (p-1)(q-1)
        4. Select the public exponent e element of {1, 2, 3, …, phi(n)-1} such that gcd (e, phi(n)) = 1.
        5. Compute private key d such that d*e = 1 mod phi(n).
	**/

public class KeyGen {
	
	public static void main (String[] args) {
		Scanner input = new Scanner (System.in);
		BigInteger p = new BigInteger("0");
		BigInteger q = new BigInteger("0");
		BigInteger n;
		BigInteger phi;
		BigInteger e;
		BigInteger d;
		System.out.println("Enter mode of operation (a/m): ");
		String MoP = input.nextLine();
		
		// 1. Choose two primes p and q.
		switch (MoP) 
		{
			case "a":
				System.out.print("Enter the number of bits: ");
				int numbits = input.nextInt();
				p = primeGen(numbits);
				q = primeGen(numbits);
				break;
			case "m":
				System.out.print("Enter first prime: ");
				p = input.nextBigInteger();
				System.out.print("Enter second prime: ");
				q = input.nextBigInteger();
				break;
		}
		
		// 2. Compute n = p*q
		n = p.multiply(q);
		
		// 3. Compute phi(n) = (p-1)(q-1)
		BigInteger pMinus1 = p.subtract(new BigInteger("1"));
		BigInteger qMinus1 = q.subtract(new BigInteger("1"));
		phi = pMinus1.multiply(qMinus1);
		
		// 4. Select the public exponent e element of {1, 2, 3, …, phi(n)-1} such that gcd (e, phi(n))) = 1.
		// The following few lines are the BigInteger way of doing the formula a = min + (Math.random() * ((max) - min))
		// Unfortunately, doing it in one line is cancer.
		BigInteger max = phi.subtract(BigInteger.valueOf(1));
		BigInteger range = max.subtract(BigInteger.valueOf(1));
        SecureRandom random = new SecureRandom();
        // Generate a random BigInteger within the specified range
        BigInteger randrange = new BigInteger(range.bitLength(), random).mod(range);
		e = randrange.add(BigInteger.valueOf(2));
		while (isRelativelyPrime(e, phi) != true) {
			e = e.add(BigInteger.valueOf(1));
		}
		
		// 5. Compute private key d such that d*e = 1 mod phi(n).
		d = e.modInverse(phi);
		System.out.printf("Public key is: (n = %s, e = %s)", n.toString(), e.toString());
		System.out.println();
		System.out.printf("Private key is: (n = %s, d = %s)", n.toString(), d.toString());
		input.close();
	}
	
	
	public static BigInteger primeGen(int numBits) {
		int numbits = numBits;
		SecureRandom rand = new SecureRandom();
		BigInteger bigRand = new BigInteger(numbits, rand);
		while (primeCheck(bigRand) != true) {
			bigRand = bigRand.add(BigInteger.valueOf(1));
		}
		return bigRand;
	}
	

	public static boolean primeCheck(BigInteger number) {
		boolean prime = false;
		float s = 150F;
		for (int i = 1; i <= s; i++) {
			if (number.compareTo(BigInteger.valueOf(4)) == 0) {
				break;
			} else if (number.compareTo(BigInteger.valueOf(4)) < 0) {
				prime = true;
				break;
			}

			// The following few lines are the BigInteger way of doing the formula a = 2 +
			// (Math.random() * ((number - 2) - 2))
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
	
	
	public static boolean isRelativelyPrime(BigInteger r0, BigInteger r1) {
		BigInteger r2;
		r2 = r0.mod(r1);
		while (!r2.equals(BigInteger.valueOf(0))) {
			r0 = r1;
			r1 = r2;
			r2 = r0.mod(r1);
		}
		if (r1.equals(BigInteger.valueOf(1))) {
			return true;
		} else {
			return false;
		}
	}
}
