# RSA-Java-Implementation

This projects implements the entire RSA cryptosystem from scratch. It goes from using Fermat's primality test to generate prime numbers, to encrypting and decrypting messages using RSA.

There are two main folders in this repo. The `/Proof_of_concepts` folder contains basic proof-of-concept files for basic functions that make up the RSA implementation. These codes serve as the building blocks to the actual implementation of the RSA algorithm in Java. Basic explanations of the program logic are also in these files.

However, if you want to see the actual implementation of the RSA cryptosystem, then the folder `/Actual_implementation` is the right one for you. There are a few use cases and an actual simulation in this folder, all of which showcase how the RSA algorithm is used in keeping a conversation private.
</br>

## Running the Simulation
For this section, all the simulation files are in the `Actual_implementation` folder. Here is how you use them:

### `driver.java`
This code serves as a standalone showcase for this cryptosystem's features. It asks the user for a key size, and then creates public and private keys based on that. From that point, the driver is able to repeatedly encrypt and decrypt messages using the same keys that were generated beforehand. Here's how it looks like:

```
Enter key size in bits [default is 128 bits]: 256
Using key size 256...
n = 510698787541438407272468639554658265622401658094944336424510109757486254319826641056522483077311235825462658727814911495157917922807386113087813951694883
e = 401392986616867306403237710416144528343278563449945545504929473573334879776830710229666410120712039901965225702193580379785354819007264840821085469750327
d = 422602444357876223529260433191292685619135713310193752329322234368799705195248544712084463832654847371150879130472608500235750517948619313825498403831111  

Input plaintext: I hope my code is working...
Ciphertext is: 3d0f420182dd8df00ff296bb6f5e64155eb238cfa20eeb2537042eb1c453e96c1891b2ef974971c3c899ba691589e5c35456758f0adc0ca4b8e30cf61bddc3a
Input ciphertext: 3d0f420182dd8df00ff296bb6f5e64155eb238cfa20eeb2537042eb1c453e96c1891b2ef974971c3c899ba691589e5c35456758f0adc0ca4b8e30cf61bddc3a
Plaintext is: I hope my code is working...

Input plaintext: It's working!!! Nice!
Ciphertext is: 1fae00f1f1c277d5a92c28ca992dfa78a5f18bde81996005da58a07f47eabcc57c01f4606ace3e530f4ca0e78c77f7727086a89c41f913b206736cc2ea579f2
Input ciphertext: 1fae00f1f1c277d5a92c28ca992dfa78a5f18bde81996005da58a07f47eabcc57c01f4606ace3e530f4ca0e78c77f7727086a89c41f913b206736cc2ea579f2
Plaintext is: It's working!!! Nice!

Input plaintext:
```

### `Alice_sim.java` and `Bob_sim.java`
These files are the codes that simulate secure communication using RSA. Both of them act as two people using the RSA cryptosystem to encrypt and decrypt the messages they send to each other. Each file can either create keys from scratch or accept predefined keys. Then, using those keys, encryption and decryption are made possible.

Here's how the RSA cryptosystem works. Suppose Bob wants to send a secret message to Alice. 
1. First, Alice needs create her own public and private key pairs. Run `Alice_sim.java` and do so.
```
You are Alice. What do you want to do?
[1] Generate keys
[2] Send an encrypted message   
[3] Decrypt an encrypted message
1
Select key generation option:
[1] Choose default key length (128 bits)
[2] Choose custom key length
[3] I already have my own key pairs
1
This is your public key. Publish this to the public.
Public key is: (n = 11752815732272781423150876550732385912838457337754682706453725758156108001043, e = 2017790073541119105497276237314559979645144821736037143612129459744389458855)
This is your private key. KEEP THIS TO YOURSELF ONLY.
Private key is: (n = 11752815732272781423150876550732385912838457337754682706453725758156108001043, d = 4975631516422386342524718590426320019589125071571467080793040262487433351791)
```
2. Now, on a separate terminal, run `Bob_sim.java` so Bob can send an encrypted message. Here, Bob will encrypt his secret message "Hello, Alice! I'm Bob." using Alice's public key. A ciphertext will be created.
```
You are Bob. What do you want to do?
[1] Generate keys
[2] Send an encrypted message       
[3] Decrypt an encrypted message    
2
Input message plaintext:
Hello, Alice! I'm Bob.
Input recipient's modulus n:
11752815732272781423150876550732385912838457337754682706453725758156108001043
Input recipient's modulus e:
2017790073541119105497276237314559979645144821736037143612129459744389458855
Send this ciphertext to your recipient:
145a1b64ed213fc41aa8726ed33912ea91c080701d2dc9ccfea8b259e7e4886f
```
3. Back on Alice's side, you can choose to decrypt an encrypted message. Let's make Alice decrypt the ciphertext that Bob made for her.
```
You are Alice. What do you want to do?
[1] Generate keys
[2] Send an encrypted message
[3] Decrypt an encrypted message
3
Input message ciphertext:
145a1b64ed213fc41aa8726ed33912ea91c080701d2dc9ccfea8b259e7e4886f
This is the decrypted message:
Hello, Alice! I'm Bob.
```
4. Nice! That proves that Bob is able to encrypt something with Alice's public key and Alice is able to decrypt it with her public key, which is the whole point of RSA. If you want Alice to respond, simply follow steps 1-3, except this time Bob generates his keys, Alice encrypts her message, and then Bob decrypts it.

## Proof of Concepts
This section provides an overview of the files in the `Proof_of_concepts` folder. The concepts tested in these files are what makes the simulation work.
* `PrimeCheckBasic.java` - Very primitive way of checking if a number is prime or not. If `n` is the number you're checking, loops through every number from 2 to `n-1` and checks if they are divisible by `n`. Though intuitive, it's very slow and impossible to use for large numbers, as RSA requires large primes.
* `PrimeCheck.java` - Checks if a number is prime or not using Fermat's Primality Test. It's a mathematical algorithim that works a lot faster than the one in `PrimeCheckBasic.java`.
* `PrimeCheckTester.java` - Tests the code in `PrimeCheck.java`. Also serves as a stress test for Fermat's Primality Test.
* `PrimeGen.java` - Generates random prime numbers using Java's built-in SecureRandom() object plus our implementation of Fermat's Primality Test.
* `KeyGen.java` - Uses the logic in `PrimeGen.java` to generate large prime numbers, and then make private and public keys out of them. It's follows RSA's key generation algorithm.
* `encrypt.java` - Given a plaintext message and the public key created by `PrimeGen.java`, this file encrypts the plaintext message and outputs the hex (base-16) representation of the resulting ciphertext.
* `decrypt.java` - Given the ciphertext (in hex) and the private key generated by `PrimeGen.java`, this file decrypts the ciphertext message and outputs the original plaintext message. IMPORTANT NOTE: the private key to be used here has to be the one that corresponds to the public key generated beforehand.