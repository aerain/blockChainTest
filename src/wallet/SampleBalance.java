

import java.io.IOException;

import io.blocko.coinstack.*;
import io.blocko.coinstack.exception.*;
import io.blocko.coinstack.model.*;

public class SampleBalance {

	public static void sampleGetBalance(CoinStackClient client, String address) throws IOException, CoinStackException {
		// 잔고 조회
		long balance = client.getBalance(address); // 1 BTC == 100,000,000 satoshi
		double result  = balance /100000000.0;
		System.out.println("- balance: "+balance+" (satoshi)");
		System.out.printf("- my bitCoin: %.8f",result);
		System.out.println("");
	}	

	public static void sampleGetUtxo(CoinStackClient client, String address) throws IOException, CoinStackException {
		// UTXO 조회
		Output[] utxos = client.getUnspentOutputs(address);
		System.out.println("- utxo: cnt="+utxos.length);
		
		long sumOfUtxoValues = 0;
		for (Output utxo : utxos) {
			String txIdReverseOrdered = utxo.getTransactionId(); // Caution: reverse byte order
			int index = utxo.getIndex();
			long value = utxo.getValue();
			sumOfUtxoValues += value;
      		System.out.printf("  utxo[] txId.r=%s, index=%d, value=%d\n",txIdReverseOrdered, index, value);
		}
		
		System.out.println("  sumOfUtxoValues: "+sumOfUtxoValues);
	}
	
	public static void sampleGetHistory(CoinStackClient client, String address) throws IOException, CoinStackException {
		// 거래내역 조회
		String[] txIds = client.getTransactions(address);
		System.out.println("- txIds: cnt="+txIds.length);
		
		for (int i=0; i<txIds.length; i++) {
			if (i >= 5) {
				System.out.println("  ...");
				break;
			}
			SampleTx.sampleGetTx(client, txIds[i]);
		}
	}

}