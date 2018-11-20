# 블록체인 공부

블록체인에 자바를 곁들입니다.

## 블록체인 구조 분석
---
### 블록구조
> 블록구조(블록크기: 바이트 단위의 크기)

> 블록체인이라는 것은 블록들이 체인형태로 연결되어 있는 것을 말함.

> 블록은 헤더(header)와 바디(body)로 이루어짐.

## 직거래 서비스 개발
---
1. SDK API
2. Create Account and Transaction

## Development Environment
---
> JAVA JDK 설치

- 환경변수 등록

> 프로그램 버전 확인

```bash
> javac -version
javac 1.8.0_192
```

## SDK API
> Block
- 특정 블록의 정보를 얻기 위해서는 블록의 ID인 블록 해시 값이 필요함
- CoinStackClient 객체의 getBlock 메소드를 호출하면 입력한 블록 ID에 해당하는 블록의 정보가 담겨 있는 Block 객체를 반환함.
- Block 객체의 getBlockId, getParentId, getHeight 메소드를 통해 각각 블록 해시, 이전 블록의 해시, 블록 번호를 확인할 수 있음.

> BlockChain Status
- getBestBlockHash 메소드를 통해 블록 체인의 최신 상태 정보를 조회할 수 있음.
- JAVA code

```java
String[] blockChainStatus = client.getBlockchainStatus();
```
- 결과 값은 JSON으로 반환.
```json
{
    "best_block_hash": "0000.......",
    "best_height": 370353
}
```

> Block
- JAVA code

```java
Block block = client.getBlock("BLOCK_ID");
System.out.println("blockId: " + block.getBlockId());
System.out.println("parentId: " + block.getParentId());
System.out.println("height: " + block.getHeight());
System.out.println("time: " + block.getBlockConfirmationTime());
```

- 수행 결과
```
blockId: 000000000.......
time: Fri Oct 07 15:25:34 KST 2016
```

> Address- Address Balance
- Address Balance는 현재 특정 비트코인 주소에서 다른 주소로 송금 가능한 잔액을 사토시 단위로 나타내는 객체

|Attribute|Type|Description|
|:---:|:---:|:---:|
|balance|number|주소의 사용가능한 잔고 (사토시 단위)

> Address - Private Key
- 개인키 생성
- 1에서 2^256 사이의 숫자중 무작위로 정수 하나를 고르게 되는데 이것이 바로 개인키가 됨.
- 개인키는 보통 Base58Check으로 인코딩하여 사용하는데, 이것을 비트코인에서는 Private Key WIF (Wallet Import Format)라고 부름.
- WIF 형으로 변화하면 그 길이가 짧아지고 혼동하기 쉬운 문자열 (1 또는 i)이 제거되어 편리한 형태가 됨.

> Address - Address Balance
- 주소는 비트코인을 담고 있거나 OP_RETURN data를 담고 있음.
- 흩어져 있는 UTXO(Unspent Output)의 비트코인의 수량을 합산하여 보여주는 기능.
    -  e.g. 0.0001 BTC = 10000 satoshi

- JAVA Code
```java
// get a remaining balance
long balance = client.getBalance(your_wallet_address);
System.out.println("balance: " + balance);
```

```bash
balance: 56638820
```

> Address - Private Key
- Java code
```java
// create a new private key
String newPrivateKeyWIF = ECKey.createNewPrivateKey();
System.out.println("private key: " + newPrivateKeyWIF);
```

- 결과 값
```bash
private key: L3nWh......
```

> Address - Bitcoin address
- RIPEMD160(SHA256(K)) 함수로 다시 인코딩하여 생성함
- JAVA Code
```java
// derive an address
String your_wallet_address = ECKey.deriveAddress(newPrivateKeyWIF);
System.out.println("address: " + your_wallet_address);
```
- 결과 값
```bash
address: 1MG4Y8...
```

> Transaction - 생성 및 서명
- JAVA code
```java
// create a target address to send
String toPrivateKeyWIF = ECKey.createNewPrivateKey();
String toAddress = ECKey.deriveAddress(toPrivateKeyWIF);

// create a transaction
long amount = io.blocko.coinstack.Math.converToSatoshi("0.0002");
long fee = io.bloco.coinstack.Math.convertToSatoshi("0.0001");

TransactionBuilder builder = new TransactionBuilder();
builder.addOutput(toAddress, amount);
builder.setFee(fee);

// sign the transaction using the private key
String signedTx = client.createSignedTransaction(builder. "YOUR_PRIVATE_KEY");
System.out.println(signedTx);
```

- Transaction은 블록에 저장된 주소 간의 거래 정보인 트랜잭션을 나타내는 객체

|Attribute|Type|Description|
|---|---|---|
|transaction_hash|string|트랜잭션의 해시|
|block_hash|list[string]|트랜잭션이 포함된 블록의 해시
|block_hash.block_hash|string|트랜잭션이 포함된 블록의 해시
|block_hash.block_height|number|트랜잭션이 포함된 블록의 높이
|coinbase|boolean|Coinbase 트랜잭션 여부
|inputs|array[object]|Transaction Input 목록
|outputs|array[object]|Transaction Output 목록
|timestamp|string|트랜잭션이 포함된 블록이 최초 승인된 시간 - date 참조
|initialtimestamp|string|트랜잭션이 broadcast된 시간 - date 참조
|addresses|array[string]|트랜잭션과 관련된 주소 목록

> Transaction - 생성 및 서명
- 결과 값: 문자열로 반환.
```
010000.....
```

> Transaction - 트랜잭션 전송
- 서명된 트랜잭션은 이제 네트워크에 전송될 수 있음.
- 만약 전송 과정에서 문제가 발생하거나, 트랜잭션 자체에 오류가 있으면 관련된 오류 코드로 원인을 확인할 수 있음.
- 하지만 트랜잭션 자체에 오류가 없는 경우에도 블록체인 네트워크의 특성상 일시적으로 트랜잭션이 거부되는 경우가 있음.
- 이 때는 네트워크에 성공적으로 전파될 때까지 재전송하면 됨.

```java
// send the signed transaction
client.sendTransaction(signedTx);
```

## 실습
---
> 전자지갑 생성
```bash
$ rm class/*.class
$ javac -encoding UTF-8 -cp "class/*" -d class/ *.java
$ java -cp "class/*:class" App
```

> App.java 소스 수정
```java
public class App 
{
    public static void main(String[] args) throws IOEsception, CoinStackException {
        System.out.println("# SampleMain");
        CoinStackClient client = Testnet.createNewClient();
        
        // .. code
    }
}
```