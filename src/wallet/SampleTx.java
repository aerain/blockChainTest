

import java.io.IOException;
import java.util.Arrays;

import io.blocko.coinstack.*;
import io.blocko.coinstack.exception.*;
import io.blocko.coinstack.model.*;
import io.blocko.coinstack.util.*;

public class SampleTx {
		
	public static void sampleGetTx(CoinStackClient client, String txId) throws IOException, CoinStackException {
		// 트랜잭션 조회
		System.out.println("- tx: id="+txId);
		Transaction tx = client.getTransaction(txId);
		
		// block
		String[] blockIds = tx.getBlockIds();
		int[] blockHeights = tx.getBlockHeights();
		System.out.println("  - blockIds: "+Arrays.toString(blockIds));
		System.out.println("  - blockHeights: "+Arrays.toString(blockHeights));
		
		// confirmation count
		int bestHeight = client.getBlockchainStatus().getBestHeight();
		int blockHeight = (blockHeights.length == 0) ? 0 : blockHeights[0];
		int confirmationCount = (blockHeight <= 0) ? 0 : bestHeight - blockHeight + 1;
		System.out.println("  - confirmationCount: "+confirmationCount);
		
		
		// is coinbase
		boolean isCoinbase = tx.isCoinbase();
		if (isCoinbase) {
			System.out.println("  - isCoinbaseTx: "+isCoinbase);
		}
		
		// inputs
		Input[] inputs = tx.getInputs();
		System.out.println("  - inputs: cnt="+inputs.length);
		long sumOfInputValues = 0;
		for (int index=0, len=inputs.length; index<len; index++) {
			Input input = inputs[index];
			sumOfInputValues += input.getValue();
			System.out.printf("    inputs[%d]: txId=%s, outputIdx=%d, addr=%s, value=%d\n",
					index,
					input.getOutputTransactionId(), input.getOutputIndex(),
					input.getOutputAddress(), input.getValue());
		}
		System.out.println("    sumOfInputValues: "+sumOfInputValues);
		
		// outputs
		Output[] outputs = tx.getOutputs();
		System.out.println("  - outputs: cnt="+outputs.length);
		long sumOfOutputValues = 0;
		for (Output output : outputs) {
			sumOfOutputValues += output.getValue();
			byte[] data = output.getData();
			String hexData = (data == null) ? null : Codecs.HEX.encode(data);
			String strData = (data == null) ? null : new String(data);
			System.out.printf("    outputs[%d]: addr=%s, value=%d, data=(hex)%s,(str)%s\n",
					output.getIndex(),
					output.getAddress(), output.getValue(), hexData, strData);
		}
		System.out.println("    sumOfOutputValues: "+sumOfOutputValues);
		
		// fee
		long fee = (sumOfInputValues == 0)
				? 0 : (sumOfInputValues - sumOfOutputValues);
		System.out.println("  - fee="+fee);
	}
}