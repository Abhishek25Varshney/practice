import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

public class ObjectEncryptionDecryption {
//private static SecretKey key;
	private static SecretKeySpec secretKey;
private static byte[] key;
public static void setKey(String myKey)
{
    MessageDigest sha = null;
    try {
        key = myKey.getBytes("UTF-8");
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKey = new SecretKeySpec(key, "AES");
    }
    catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
}
	/*
	 * getCipherObject method is responsible for creating Cipher object for encryption and decryption.  
	 */
	private static Cipher getCipherObject(String type ) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		Cipher cipher;
//		cipher = Cipher.getInstance("DES");
		 cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		if(type.contentEquals("encryption"))
		{
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		}
		else if(type.contentEquals("decryption")){
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
		}
		return cipher;
		
	}
	
	/*
	 * This method is responsible for encrypt the object
	 */
	private static SealedObject encryptObject(Serializable o) throws InvalidKeyException, IllegalBlockSizeException, NoSuchAlgorithmException, NoSuchPaddingException, IOException{
		
		SealedObject sealed = new SealedObject(o, getCipherObject("encryption"));
		
		System.out.println("Object Encrypted");
		return sealed;
	}
	
	/*
	 * This method is responsible for decrypt the object
	 */
	private static <T> T decryptObject(SealedObject sealed) throws InvalidKeyException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		Staff encryptedObject = (Staff) sealed.getObject(getCipherObject("decryption"));
		System.out.println("Object Decrypted");
		return (T) encryptedObject;
		
	}

	public static void main(String[] args) throws ClassNotFoundException, BadPaddingException {
		try {

			// generate secret key using DES algorithm same for encryption and decryption
//			key = KeyGenerator.getInstance("DES").generateKey();
			setKey("Abhishek");
			/// the object so we will encrypt
//			EncriptThisClass so = new EncriptThisClass();
			Staff staff=new Staff("Abhishek", 24, new BigDecimal(434530000));
			System.out.println("Sealed Object"+staff);
			/// encryptedObject this encrypted object send this object to some one or through network
			/// no one can get the real object if they do not know the  key
			SealedObject encryptedObject =encryptObject(staff);
			System.out.println("Sealed Object"+encryptedObject);
			///use encryptedObject and get the real object that is nothing but decryption
			Staff etcObject=decryptObject(encryptedObject);
			
			
			/// now use the etcObject object after decrypt
			
			etcObject.Test();
			

		} catch (NoSuchAlgorithmException e) {

			System.out.println("No Algorithmn found:" + e.getMessage());

			return;

		} catch (NoSuchPaddingException e) {

			System.out.println("No Padding:" + e.getMessage());

			return;

		} catch (InvalidKeyException e) {

			System.out.println("Invalid Key:" + e.getMessage());

			return;

		} catch (IllegalBlockSizeException e) {

			System.out.println("Illegal Block:" + e.getMessage());

			return;

		} catch (IOException e) {

			System.out.println("I/O Error:" + e.getMessage());

			return;

		}

	}
	
	
	/*
	 * This class object we use to encrypt and decrypt
	 */

	public static class EncriptThisClass implements Serializable {

		private static final long serialVersionUID = 1L;

		public void Test() {

			System.out.println("Object encription decryption working correctly");

		}

	}

}
