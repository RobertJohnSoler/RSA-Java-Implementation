import java.math.BigInteger;
import java.util.Scanner;

public class encrypt {
	public static void main (String [] args) {
		BigInteger n;
		BigInteger d;
		BigInteger x;
		BigInteger y;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter n: ");
		n = input.nextBigInteger();
		System.out.print("Enter e: ");
		d = input.nextBigInteger();
		System.out.print("Enter plaintext: ");
		x = input.nextBigInteger();
		
		y = x.modPow(d, n);
		System.out.println("Ciphertext is: " + y);
		input.close();
	}
}
