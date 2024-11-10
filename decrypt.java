import java.math.BigInteger;
import java.util.Scanner;

public class decrypt {
	public static void main (String [] args) {
		BigInteger n;
		BigInteger e;
		BigInteger x;
		BigInteger y;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter n: ");
		n = input.nextBigInteger();
		System.out.print("Enter d: ");
		e = input.nextBigInteger();
		System.out.print("Enter ciphertext: ");
		y = input.nextBigInteger();
		
		x = y.modPow(e, n);
		System.out.println("Plaintext is: " + x);
		input.close();
	}
}
