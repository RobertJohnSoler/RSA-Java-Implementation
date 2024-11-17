package Actual_implementation;

import java.math.BigInteger;
import java.util.Scanner;

public class driver {
    public static void main(String[] args) {

        boolean debug = true;

        Scanner input = new Scanner(System.in);
        Scanner keysize = new Scanner(System.in);
        System.out.print("Enter key size in bits [default is 128 bits]: ");
        String key_size_input = keysize.nextLine();
        
        if (!key_size_input.isEmpty()) {
            int key_size = Integer.parseInt(key_size_input);
            System.out.println("Using key size " + key_size + "...");
            RSA rsa = new RSA(key_size);
            rsa.keyGen();
            BigInteger n = rsa.getPublicKeys().get("n");
            BigInteger e = rsa.getPublicKeys().get("e");
            BigInteger d = rsa.getPrivateKeys().get("d");
            if (debug == true){
                System.out.println("n = " + n);
                System.out.println("e = " + e);
                System.out.println("d = " + d);
            }

            while (true) {

                System.out.print("Input plaintext: ");
                String plaintext1 = input.nextLine();
                String ciphertext1 = rsa.RSAEncrypt(n, e, plaintext1);
                System.out.println("Ciphertext is: " + ciphertext1);

                System.out.print("Input ciphertext: ");
                String ciphertext2 = input.nextLine();
                String plaintext2 = rsa.RSADecrypt(ciphertext2);
                System.out.println("Plaintext is: " + plaintext2);

            }     
            
        } else if (key_size_input.isEmpty()){
            System.out.println("Using default key size of 128 bits...");
            RSA rsa = new RSA();
            rsa.keyGen();
            BigInteger n = rsa.getPublicKeys().get("n");
            BigInteger e = rsa.getPublicKeys().get("e");
            BigInteger d = rsa.getPrivateKeys().get("d");

            if (debug == true){
                System.out.println("n = " + n);
                System.out.println("e = " + e);
                System.out.println("d = " + d);
            }

            while (true) {

                System.out.print("Input plaintext: ");
                String plaintext1 = input.nextLine();
                String ciphertext1 = rsa.RSAEncrypt(n, e, plaintext1);
                System.out.println("Ciphertext is: " + ciphertext1);

                System.out.print("Input ciphertext: ");
                String ciphertext2 = input.nextLine();
                String plaintext2 = rsa.RSADecrypt(ciphertext2);
                System.out.println("Plaintext is: " + plaintext2);

            }  
        }
        input.close();
        keysize.close();
    }
}
