package Actual_implementation;

import java.math.BigInteger;
import java.util.Scanner;

public class Bob_sim {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        RSA rsa = new RSA();
        String plaintext;
        String ciphertext;

        while (true) {
            System.out.println("");
            System.out.println("You are Bob. What do you want to do?");
            System.out.println("[1] Generate keys");
            System.out.println("[2] Send an encrypted message");
            System.out.println("[3] Decrypt an encrypted message");

            String user_input1 = scan.nextLine();
            switch (user_input1) {

                case "1":
                    System.out.println("Select key generation option:");
                    System.out.println("[1] Choose default key length (128 bits)");
                    System.out.println("[2] Choose custom key length");
                    System.out.println("[3] I already have my own key pairs");
                    int user_input2 = scan.nextInt();
                    scan.nextLine();
                    rsa = createRSA(user_input2);
                    BigInteger n = rsa.getPrivateKeys().get("n");
                    BigInteger e = rsa.getPublicKeys().get("e");
                    BigInteger d = rsa.getPrivateKeys().get("d");
                    System.out.println("This is your public key. Publish this to the public.");
                    System.out.printf("Public key is: (n = %s, e = %s) \n", n.toString(), e.toString());
                    System.out.println("This is your private key. KEEP THIS TO YOURSELF ONLY.");
                    System.out.printf("Private key is: (n = %s, d = %s)\n", n.toString(), d.toString());
                    break;

                case "2":
                    System.out.println("Input message plaintext:");
                    plaintext = scan.nextLine();
                    System.out.println("Input recipient's modulus n:");
                    BigInteger recepient_n = scan.nextBigInteger();
                    scan.nextLine();
                    System.out.println("Input recipient's modulus e:");
                    BigInteger recepient_e = scan.nextBigInteger();
                    scan.nextLine();
                    ciphertext = rsa.RSAEncrypt(recepient_n, recepient_e, plaintext);
                    System.out.println("Send this ciphertext to your recipient:");
                    System.out.println(ciphertext);
                    break;

                case "3":
                    System.out.println("Input message ciphertext:");
                    ciphertext = scan.nextLine();
                    plaintext = rsa.RSADecrypt(ciphertext);
                    System.out.println("This is the decrypted message:");
                    System.out.println(plaintext);
                    break;

            }
        }
    }

    public static RSA createRSA(int option) {
        RSA rsa;
        Scanner rsa_input = new Scanner(System.in);
        int key_size;
        switch (option) {
            case 1: // for default key length
                rsa = new RSA();
                rsa.keyGen();
                break;
            case 2: // for given key length
                System.out.println("Enter the desired key length (in bits):");
                key_size = rsa_input.nextInt();
                rsa_input.nextLine();
                rsa = new RSA(key_size);
                rsa.keyGen();
                break;
            case 3: // for given key pairs
                System.out.print("Enter n: ");
                BigInteger n = rsa_input.nextBigInteger();
                System.out.print("Enter e: ");
                BigInteger e = rsa_input.nextBigInteger();
                System.out.print("Enter d: ");
                BigInteger d = rsa_input.nextBigInteger();
                rsa = new RSA(n, d, e);
                break;
            default:
                System.out.println("Invalid option. Generating key with default key size...");
                rsa = new RSA();
                break;
        }
        //rsa_input.close();
        return rsa;
    }
}
