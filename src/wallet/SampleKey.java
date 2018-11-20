

import java.io.IOException;

import io.blocko.coinstack.*;
import io.blocko.coinstack.exception.*;

public class SampleKey {
	
	public static String[] sampleCreateKey() throws IOException, CoinStackException {
		// 개인 키 생성
		String privateKey = ECKey.createNewPrivateKey();
		
		// 주소 생성
		String address = ECKey.deriveAddress(privateKey);
		
		// System.out.println("- privateKey   : "+privateKey);
		// System.out.println("  Your address : "+address);

        return new String[] { privateKey, address };
	}
}