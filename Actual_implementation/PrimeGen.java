package Actual_implementation;
import java.math.BigInteger;
import java.security.SecureRandom;

public class PrimeGen {
    
    private int prime_size;
    private SecureRandom rand;

    public PrimeGen(int size){
        rand = new SecureRandom();
        prime_size = size;
    }

    public PrimeGen(){
        rand = new SecureRandom();
        prime_size = 64;
    }

    public boolean primeCheck(BigInteger number){
        boolean prime = false;
		float s = 150F;
    
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

    public BigInteger generatePrime(){
        BigInteger bigRand = new BigInteger(prime_size, rand);
        while (primeCheck(bigRand) != true) {
			bigRand = bigRand.add(BigInteger.valueOf(1));
		}
        return bigRand;
    }
}
