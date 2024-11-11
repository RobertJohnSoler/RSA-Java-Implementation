package Actual_implementation;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Dictionary;
import java.util.Hashtable;

public class RSA {
    
    private int key_size;
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private PrimeGen prime_generator;

    // Default Constructor
    public RSA() {
        key_size = 128;
        prime_generator = new PrimeGen(key_size);
    }

    // Constructor that generates the keys for you
    public RSA(int s) {
        key_size = s;
        prime_generator = new PrimeGen(key_size);
    }

    // Constructor for when the user already has his own pivate key
    public RSA(BigInteger n, BigInteger d){
        this.n = n;
        this.d = d;
    }

    public void keyGen(){
        BigInteger p = prime_generator.generatePrime();
        BigInteger q = prime_generator.generatePrime();
        BigInteger phi;
        n = p.multiply(q);
        BigInteger pMinus1 = p.subtract(new BigInteger("1"));
		BigInteger qMinus1 = q.subtract(new BigInteger("1"));
		phi = pMinus1.multiply(qMinus1);
        BigInteger max = phi.subtract(BigInteger.valueOf(1));
		BigInteger range = max.subtract(BigInteger.valueOf(1));
        SecureRandom random = new SecureRandom();
        BigInteger randrange = new BigInteger(range.bitLength(), random).mod(range);
		e = randrange.add(BigInteger.valueOf(2));
		while (isRelativelyPrime(e, phi) != true) {
			e = e.add(BigInteger.valueOf(1));
		}
        d = e.modInverse(phi);
    }

    public boolean isRelativelyPrime(BigInteger r0, BigInteger r1) {
		BigInteger r2;
		r2 = r0.mod(r1);
		while (!r2.equals(BigInteger.valueOf(0))) {
			r0 = r1;
			r1 = r2;
			r2 = r0.mod(r1);
		}
		if (r1.equals(BigInteger.valueOf(1))) {
			return true;
		} else {
			return false;
		}
	}
    
    public Dictionary<String, BigInteger> getPublicKeys(){
        Dictionary <String, BigInteger> keys = new Hashtable<>();
        keys.put("n", n);
        keys.put("e", e);
        return keys;
    }

    public Dictionary<String, BigInteger> getPrivateKeys(){
        Dictionary <String, BigInteger> keys = new Hashtable<>();
        keys.put("n", n);
        keys.put("d", d);
        return keys;
    }

    public static String StringToBinary (String s){
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
           int val = b;
           for (int i = 0; i < 8; i++)
           {
              binary.append((val & 128) == 0 ? 0 : 1);
              val <<= 1;
           }
        //    binary.append(' ');
        }
        return binary.toString();
    }

    public static String BinaryToString (String b){
        while (b.length() % 8 != 0){
            b = '0' + b;
        }
        String string = " ";
        for(int index = 0; index < b.length(); index+=8) {
            String temp = b.substring(index, index+8);
            int num = Integer.parseInt(temp,2);
            char letter = (char) num;
            string = string + letter;
        }
        return string;
    }

    public static String RSAEncrypt (BigInteger N, BigInteger E, String plaintext){
        BigInteger binary_plaintext = new BigInteger(StringToBinary(plaintext), 2);
        BigInteger binary_ciphertext = binary_plaintext.modPow(E, N);
        return binary_ciphertext.toString(2);
    }

    public static String RSADecrypt (BigInteger N, BigInteger D, String ciphertext){
        BigInteger binary_ciphertext = new BigInteger(ciphertext, 2);
        BigInteger binary_plaintext = binary_ciphertext.modPow(D, N);
        return BinaryToString(binary_plaintext.toString(2));
    }

}
