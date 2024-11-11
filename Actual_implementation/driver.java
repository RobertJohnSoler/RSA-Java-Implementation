package Actual_implementation;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class driver {
    public static void main (String[] args){

        Scanner input = new Scanner(System.in);
        try {
            while (true){
                System.out.print("Input size of prime number: ");
                int size = input.nextInt();
                PrimeGen generator = new PrimeGen(size);
                BigInteger prime = generator.getPrime();
                System.out.println(prime);
            }
        } finally {
            input.close();
        }
        
    }
}
