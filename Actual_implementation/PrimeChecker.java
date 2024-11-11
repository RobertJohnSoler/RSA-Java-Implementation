package Actual_implementation;
import java.math.BigInteger;
import java.security.SecureRandom;

public class PrimeChecker {
     
    private BigInteger num;
    private boolean prime;

    public PrimeChecker (BigInteger n){
        num = n;
        prime = checkIfPrime(num);
    }

    
    public boolean checkIfPrime(BigInteger number){

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


    public BigInteger getNum(){
        return num;
    }


    public boolean isPrime(){
        return prime;
    }
}
