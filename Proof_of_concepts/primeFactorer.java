package Proof_of_concepts;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class primeFactorer {
    public static void main (String [] args){
        
        for (int j=1; j<=1079; j++){
            
            if (1079 % j == 0){
                BigInteger n = BigInteger.valueOf(1079);
                BigInteger num = BigInteger.valueOf(j);
                if (primeCheck(num)){
                    BigInteger p = n.divide(num);
                    if (primeCheck(p)){
                        System.out.println("p found!");
                        System.out.println(p.toString());
                    }
                }
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.print("Enter p: ");
		BigInteger p = input.nextBigInteger();
        BigInteger pMinus1 = p.subtract(new BigInteger("1"));
        System.out.print("Enter q: ");
		BigInteger q = input.nextBigInteger();
		BigInteger qMinus1 = q.subtract(new BigInteger("1"));
		BigInteger phi = pMinus1.multiply(qMinus1);
        BigInteger e = BigInteger.valueOf(43);
        BigInteger d = e.modInverse(phi);
        System.out.println("d is "+ d.toString());
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
}