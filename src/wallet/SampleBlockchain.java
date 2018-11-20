

import java.io.IOException;
import java.util.Arrays;

import io.blocko.coinstack.*;
import io.blocko.coinstack.exception.*;
import io.blocko.coinstack.model.*;

public class SampleBlockchain {

	public static void sampleBlockchainStatus(CoinStackClient client) throws IOException, CoinStackException {
		// 블록체인 상태 조회
		BlockchainStatus status = client.getBlockchainStatus();
		String bestBlockHash = status.getBestBlockHash();
		int bestHeight = status.getBestHeight();
		System.out.println("- bestBlockHash: "+bestBlockHash);
		System.out.println("  bestHeight: "+bestHeight);
	}
	
	public static void sampleRecentBlocks(CoinStackClient client) throws IOException, CoinStackException {
		// 최근 블록 조회
		int cnt = 5;
		BlockchainStatus status = client.getBlockchainStatus();
		String bestBlockHash = status.getBestBlockHash();
		int bestHeight = status.getBestHeight();
		
		String blockId = bestBlockHash;
		while (true) {
			if (cnt-- <= 0) {
				break;
			}
			Block block = client.getBlock(blockId);
			int height = block.getHeight();
			int confirmationCount = (height <= 0) ? 0 : bestHeight - height + 1;
			System.out.printf("- block: confirm=%d, height=%d, id=%s, time=%s\n",
					confirmationCount, block.getHeight(),
					blockId, block.getBlockConfirmationTime());
			blockId = block.getParentId();
		}
	}
	
	public static void sampleGetBlock(CoinStackClient client, String blockId) throws IOException, CoinStackException {
		// 블록 조회
		Block block = client.getBlock(blockId);
		int height = block.getHeight();
		String parentBlockId = block.getParentId();
		String[] childBlockIds = block.getChildIds();
		String[] txIds = block.getTransactionIds();
		System.out.printf("- block: height=%d, id=%s\n", height, block.getBlockId());
		System.out.println("  parentBlockId: "+parentBlockId);
		System.out.println("  childBlockIds: "+Arrays.toString(childBlockIds));
		System.out.println("  txIds: "+Arrays.toString(txIds));
	}
}