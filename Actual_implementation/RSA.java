package Actual_implementation;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Dictionary;
import java.util.Hashtable;

public class RSA {
    
    private int key_size;
    private BigInteger n;
     BigInteger e;
    private BigInteger d;
    private PrimeGen prime_generator;

    // Default Constructor
    public RSA() {
        key_size = 128;
        prime_generator = new PrimeGen(key_size);
    }

    // Constructor that generates the keys for you given key size
    public RSA(int s) {
        key_size = s;
        prime_generator = new PrimeGen(key_size);
    }

    // Constructor for when the user already has his own pivate and public keys
    public RSA(BigInteger n, BigInteger d, BigInteger e){
        this.n = n;
        this.d = d;
        this.e = e;
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

    private static String StringToHex(String s){
        return String.format("%040x", new BigInteger(1, s.getBytes()));
    }

    private static String HexToString(String h){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i <h.length(); i += 2) {
            String hexPair = h.substring(i, i + 2);
            int decimal = Integer.parseInt(hexPair, 16);
            output.append((char) decimal);
        }
        return output.toString();
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
        BigInteger hex_plaintext = new BigInteger(StringToHex(plaintext), 16);
        BigInteger hex_ciphertext = hex_plaintext.modPow(E, N);
        return hex_ciphertext.toString(16);
    }

    public static String RSADecrypt (BigInteger N, BigInteger D, String ciphertext){
        BigInteger hex_ciphertext = new BigInteger(ciphertext, 16);
        BigInteger hex_plaintext = hex_ciphertext.modPow(D, N);
        return HexToString(hex_plaintext.toString(16));
    }

}
