
package interfaces;

import java.security.PrivateKey;

/**
 *
 * @author lyndemberg
 */
public interface PrivateKeyDao {

    PrivateKey getPrivateKey(String email) throws Exception;

    boolean save(String email, PrivateKey chavePrivada);
    
}
