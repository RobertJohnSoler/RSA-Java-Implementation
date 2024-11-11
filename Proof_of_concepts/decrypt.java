package Proof_of_concepts;
import java.math.BigInteger;
import java.util.Scanner;


/* This code is a simple proof-of-concept code that decrypts a given ciphertext using the RSA algorithm.
 * Since this is still a proof-of-concept, the code only outputs integer plaintexts. It cannot output strings yet.
 */

public class decrypt {
	public static void main (String [] args) {
		BigInteger n;
		BigInteger d;
		BigInteger x;
		BigInteger y;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter n: ");
		n = input.nextBigInteger();
		System.out.print("Enter d: ");
		d = input.nextBigInteger();
		System.out.print("Enter ciphertext: ");
		y = input.nextBigInteger();
		
		x = y.modPow(d, n);
		System.out.println("Plaintext is: " + x);
		input.close();
	}
}
