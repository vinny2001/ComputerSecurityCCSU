//Vincenzo D'Aria
//CS 492-01 - Homework 2: TEA Implementation in Java
//19 February 2023

package teaAlgorithm;

/*
 Part 3:
 
 To perform this algorithm with a set of plaintext longer than 64 bits using CBC,
 I could create a support class (Which I will refer to as CBCEncryptDecrypt). This would first break the
 plaintext into smaller 'blocks', while performing the TEA encryption arithmetic (as
 done in encryptTea()) on each block.
 
 After such operations are performed, CBC encrypt would chain the longer blocks of
 newly-produced ciphertext together and the result is able to successfully be returned.
 
 As for decryption, the process is essentially the same, but the TEA decryption
 arithmetic (as seen in decryptTEA()) would be performed after CBCEncryptDecrypt() breaks the
 ciphertext into smaller blocks. After the decryption, the smaller blocks would be chained back together
 to form the same plaintext as before.
 
 */


public class TeaAlgorithm {

  //Test Case from online
  //static final long[] key = {0xa56babcdL, 0xffabffffL, 0xffffffffL, 0xabcdef01L};
  //static final long[] plainText = {0x01234567L, 0x89abcdefL};


  static final long[] key = {0xBF6BABCDL,0xEF00F000L,0xFEAFAFAFL,0xACCDEF01L};
  static final long plainText[] = {0x0ACA4567L,0x0CABCDEAL};
  static int delta = 0x9e3779b9;
  static long L, R;
  static int sum = 0x0;
  static long mask = 0xffffffffL;

  // Encryption Method
  static protected void encryptTEA() {
	//Split plainText into left and right halves
    L = plainText[0];
    R = plainText[1];

    //TEA Encrypt Arithmetic
    //Mask each side so unsigned arithmetic can occur
    for (int i = 0; i < 32; i++) {
      sum += delta;
      L += (((R << 4) + (key[0])) ^ (R + sum) ^ ((R >>> 5) + (key[1])));
      L &= mask;
      R += (((L << 4) + (key[2])) ^ (L + sum) ^ ((L >>> 5) + (key[3])));
      R &= mask;
    }

    //Start with left half
    long cipherText = L;
    //drop leading zeros
    cipherText = cipherText << 32;
    //Combine both halves before returning result
    cipherText = cipherText | R;
    //Return ciphertext
    System.out.printf("Ciphertext: 0x" + String.format("%x", cipherText));
  }

  // Decryption Method
  static protected void decryptTEA() {

    sum = (delta << 5);
    
    //TEA Decrypt Arithmetic
    //Mask each side so unsigned arithmetic can occur
    for (int i = 0; i < 32; i++) {
      R -= (((L << 4) + (key[2])) ^ (L + sum) ^ ((L >>> 5) + (key[3])));
      R &= mask;
      L -= (((R << 4) + (key[0])) ^ (R + sum) ^ ((R >>> 5) + (key[1])));
      L &= mask;

      sum -= delta;
    }

    //Start with left half
    long plainText = L;
    //drop leading zeros
    plainText = plainText << 32;
    //Combine both halves before returning result
    plainText = plainText | R;
    //Return plaintext
    System.out.printf("Plaintext: 0x" + String.format("%x", plainText));
  }

  // Main
  public static void main(String[] args) {
	//Running both static encrypt & decrypt methods
    encryptTEA();
    System.out.println("");
    decryptTEA();
  }
}
