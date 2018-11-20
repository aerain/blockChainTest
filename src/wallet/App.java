import java.io.IOException;
import java.security.PublicKey;
import io.blocko.coinstack.*;
import io.blocko.coinstack.exception.*;
import io.blocko.coinstack.model.*;

/**
 * Hello world!
 *
 */
public class App 
{

	public static void main(String[] args) throws IOException, CoinStackException {
		System.out.println("# SampleMain");
		
		CoinStackClient client = Testnet.createNewClient();

		/**
		 * blockchain status
 		 *
 		*/
		System.out.println("### blockchain status");
		SampleBlockchain.sampleBlockchainStatus(client);


		/**
		 * blockchain create key
 		 *
 		*/
		// System.out.println("### create key");
		// String result[] = SampleKey.sampleCreateKey();

		// String yourPrivateKey = result[0];
		// String yourAddress = result[1];

		// System.out.println("- privateKey   : "+yourPrivateKey);
		// System.out.println("  Your address : "+yourAddress);

		// String address = "";
		// String privateKey = "";


		/**
		 * blockchain GetBalance
 		 *
 		*/
		// System.out.println("## Sample GetBalance");
		
		// System.out.println("### get balance: "+address);
		// SampleBalance.sampleGetBalance(client, address);
		
		// System.out.println("### get utxo: "+address);
		// SampleBalance.sampleGetUtxo(client, address);
		
		// System.out.println("### get history: "+address);
		// SampleBalance.sampleGetHistory(client, address); 
		
		
		/**
		 * blockchain send tx
 		 *
 		*/
		// String SAMPLE_PRIVKEY = privateKey;
	    // String SAMPLE_ADDRESS = address;
	
    	// String SAMPLE_RECEIVER_ADDR1 = "1B854qBra6X9eFcjhdEv1trctaaHKvw7L5";
	    // String SAMPLE_RECEIVER_ADDR2 = "13FE4AQcr6AhWgZLiQ8YLwLAkF7hLjADD7";
	
	    // byte[] SAMPLE_TX_DATA = "SAMPLE_TEXT".getBytes();
		// String privateKeyWIF = SAMPLE_PRIVKEY;
		// String receiverAddr1 = SAMPLE_RECEIVER_ADDR1;
		// String receiverAddr2 = SAMPLE_RECEIVER_ADDR2;
		// byte[] txdata = SAMPLE_TX_DATA;

		// System.out.println("### send simple tx");
		// SampleTxBuild.sampleSendSimpleTx(client, privateKeyWIF, receiverAddr1, "0.0001");
		
		// // System.out.println("### send data tx");
		// // SampleTxBuild.sampleSendDataTx(client, privateKeyWIF, receiverAddr2, "0.0001", txdata);

		// System.out.println("### My balance: " + address);
		// SampleBalance.sampleGetBalance(client, address);

		// System.out.println("### Your balance: " + receiverAddr2);
		// SampleBalance.sampleGetBalance(client, receiverAddr2);
		
	}
}
