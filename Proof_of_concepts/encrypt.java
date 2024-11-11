package Proof_of_concepts;
import java.math.BigInteger;
import java.util.Scanner;


/* This code is a simple proof-of-concept code that encrypts a given plaintext using the RSA algorithm.
 * Since this is still a proof-of-concept, the code only accepts integer inputs. It cannot encrypt strings yet.
 */

public class encrypt {
	public static void main (String [] args) {
		BigInteger n;
		BigInteger e;
		BigInteger x;
		BigInteger y;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter n: ");
		n = input.nextBigInteger();
		System.out.print("Enter e: ");
		e = input.nextBigInteger();
		System.out.print("Enter plaintext: ");
		x = input.nextBigInteger();
		
		y = x.modPow(e, n);
		System.out.println("Ciphertext is: " + y);
		input.close();
	}
}
