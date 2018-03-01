
package util;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import org.bson.Document;
import org.bson.types.Binary;

/**
 * @author lyndemberg
 */
public class MongoPrivate {
    private static final MongoClient cliente = new MongoClient("localhost",27017);
    private static final MongoDatabase banco = cliente.getDatabase("banco");
    private static final MongoCollection<Document> colecao = banco.getCollection("keys");

    public static boolean save(String email, PrivateKey chavePrivada){
        try{
            Document doc = new Document().append("email", email).append("chavePrivada", chavePrivada.getEncoded());
            colecao.insertOne(doc);
            return true;
        }catch(MongoException ex){
            return false;
        }
    }
    public static PrivateKey getPrivateKey(String email) throws Exception{
        Document doc = colecao.find(eq("email",email)).first();
        Binary bin = doc.get("chavePrivada",Binary.class);     
        KeyFactory factory = KeyFactory.getInstance("RSA");
        EncodedKeySpec specPrivate = new PKCS8EncodedKeySpec(bin.getData());
        PrivateKey privadaFinal = factory.generatePrivate(specPrivate);
        return privadaFinal;
    }
    
}
