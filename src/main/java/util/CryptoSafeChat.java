package util;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/** 
 * @author lyndemberg
 */
public class CryptoSafeChat {
    
    public static KeyPair getParDeChaves() throws NoSuchAlgorithmException{
        final int tamanho = 2048;
        KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");
        gerador.initialize(tamanho);
        return gerador.genKeyPair();
    }
    public static byte[] criptografar(byte[] mensagem, PublicKey chave) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        return cipher.doFinal(mensagem);
    }
    public static byte[] descriptografar(byte[] mensagem, PrivateKey chave) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        return cipher.doFinal(mensagem);
    }
}
