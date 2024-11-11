package Actual_implementation;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class driver {
    public static void main (String[] args){

        Scanner input = new Scanner(System.in);
        RSA rsa = new RSA(128);
        rsa.keyGen();
        BigInteger n = rsa.getPublicKeys().get("n");
        BigInteger e = rsa.getPublicKeys().get("e");
        BigInteger d = rsa.getPrivateKeys().get("d");
        // System.out.println(n);
        // System.out.println(e);
        // System.out.println(d);

        try {
            while (true){

                System.out.print("Input plaintext: ");
                String plaintext1 = input.nextLine();
                String ciphertext1 = RSA.RSAEncrypt(n, e, plaintext1);
                System.out.println("Ciphertext is: " + ciphertext1);

                System.out.print("Input ciphertext: ");
                String ciphertext2 = input.nextLine();
                String plaintext2 = RSA.RSADecrypt(n, d, ciphertext2);
                System.out.println("Plaintext is: " + plaintext2);

            }
        } finally {
            input.close();
        }
        // String s = input.nextLine();
        // System.out.println(RSA.StringToBinary(s));
        // String b = input.nextLine();
        // System.out.println(RSA.BinaryToString(b));
    }
}
