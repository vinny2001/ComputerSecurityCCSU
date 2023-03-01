The Cryptography.jar file contains code to generate statistics on an encypted file.  It also contains the ability to either encrypt or decrypt a file using substitution.

To get the statistics of an encrypted file enter
java -jar Cryptography.jar GetStatistics ciphertext.txt stats.txt

the ciphertext.txt file will be read and the frequency of characters A-Z will be output to stats.txt


cipher.txt is the key for encrypting or decrypting a file.  It contains 2 lines. Line 1 contains the plaintext mapping and line 2 contains the encrypted mapping.

If you want to create your own cipher you would start with line 1 equaling the alphabet and then would map each letter to some other letter.  So your file will look something like this:
abcdefghijklmnopqrstuvwxyz
CBAFEDIGHLJKOMNQPRSUTWVYXZ

For reverse engineering the cipher you would start with line 2 equaling the upper case alphabet and line 1 equal to the same.  To try mappings you would then substitute the capital letter in line 1 to the lower case letter it should be mapped to.  Example:
ABCDEFamIeKfMNOPQRSTUVWXYZ
ABCDEFGHIJKLMNOPQRSTUVWXYZ

To encrypt a file you would then have:
java -jar Cryptography.jar encrypt cipher.txt plaintext.txt ciphertext.txt

Where the ciphertext.txt file will be overwritten with the new encryption


To decrypt a file you would have:
java -jar Cryptography.jar decrypt cipher.txt plaintext.txt ciphertext.txt

Where the plaintext.txt file will be overwritten with the new decrypted text