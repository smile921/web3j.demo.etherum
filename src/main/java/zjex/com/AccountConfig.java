package zjex.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.web3j.protocol.Web3j;

import static zjex.com.AccountConfig.ACCOUNT_PREFIX;

/**
 * Created by frere921 on 2017/3/28.
 */

@ConfigurationProperties(prefix = ACCOUNT_PREFIX)
public class AccountConfig {

    public static final  String ACCOUNT_PREFIX ="account";

    private String privateKey ;

    private String publicKey ;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
