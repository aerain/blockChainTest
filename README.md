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
- > javac -version
- > javac 1.8.0_192

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