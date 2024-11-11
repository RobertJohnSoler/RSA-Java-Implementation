package Proof_of_concepts;
import java.math.BigInteger;
import java.util.Scanner;

/*
    Very basic code for checking prime numbers. 
    It works and is easy to implement, but its runtime is absolutely terrible.
 */

public class PrimeCheckBasic {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		try{
			while (true) {
				System.out.print("Enter an integer: ");
				BigInteger number = input.nextBigInteger();

				long start = System.currentTimeMillis();
				boolean prime = true;
				BigInteger mid = number.sqrt();

				for (BigInteger i = new BigInteger("2"); i.compareTo(mid) <= 0; i=i.add(BigInteger.valueOf(1))) {
					BigInteger mod = number.mod(i);
					if (mod.compareTo(BigInteger.valueOf(0)) != 0) { // if number is not divisible by i, do nothing
						;
					} else if (mod.compareTo(BigInteger.valueOf(0)) == 0) { // else if number is divisible by i, it's not a prime
						prime = false;
						break;
					}
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
