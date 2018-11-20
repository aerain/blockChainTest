

import java.io.IOException;
import java.security.PublicKey;

import io.blocko.coinstack.*;
import io.blocko.coinstack.exception.*;
import io.blocko.coinstack.model.*;

/**
 * Hello world!
 *
 */
public class Testnet 
{

    public static CoinStackClient createNewClient() {
		// Client 객체 생성
		CredentialsProvider credentials = null;
		AbstractEndpoint endpoint = new AbstractEndpoint() {
			@Override
			public String endpoint() {
				return "http://testchain.blocko.io";
			}
			@Override
			public boolean mainnet() {
				return true;
			}
			@Override
			public PublicKey getPublicKey() {
				return null;
			}
		};
		CoinStackClient client = new CoinStackClient(credentials, endpoint);
		return client;
    }
    
}
