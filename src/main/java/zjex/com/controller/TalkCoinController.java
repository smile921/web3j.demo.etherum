package zjex.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.ChainId;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import zjex.com.smart.contracts.TalkCoin;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by frere921 on 2017/3/24.
 *  Replacing ConvertLib...
 ConvertLib: 0x4a9a76318b5a28ab3533534c72bc732681b9cdd9
 Linking ConvertLib to MetaCoin
 Replacing MetaCoin...
 MetaCoin: 0x66e86662c4eba84f1db420926a669fb3efc4ad19
 Deploying TalkCoin...
 TalkCoin: 0xc179d6411f6ef6ed6ea9cd7085b2de2e69b9cf4b

 UTC--2017-03-14T03-19-26.956607486Z--a50f4f3d0b5f9808c418d51b1017b7451220a737
 {"address":"a50f4f3d0b5f9808c418d51b1017b7451220a737",
  "crypto":{"cipher":"aes-128-ctr",
            "ciphertext":"31db33a750328e91736e5cb2756a0f5f09516f24c5f582b8ce60eb46b0f2e0ca",
             "cipherparams":{"iv":"bdd195c3e6afd5817a3ef985d9ec6f35"},
             "kdf":"scrypt",
             "kdfparams":{"dklen":32,"n":262144,"p":1,"r":8,
              "salt":"6f4e3a2e7a1211fb1867acb0ba26fac3a47c28087125f508f72c6df8380f18da"},
                "mac":"b599b3bf14af38417eae3cb7bb703097090a240944cf72b00edc564497e84f87"},
             "id":"d212f383-5914-4b6e-8bc5-47120849e19c",
                "version":3}

 */

@Controller
@RequestMapping("/smart/contract")
public class TalkCoinController {

    @Autowired
    private Web3j web3j;

    @RequestMapping(value = "/set",method = RequestMethod.GET)
    @ResponseBody
    public Map setX(@RequestParam String xToset){
        xToset= xToset==null? "0":xToset;
        xToset=xToset.trim().length()==0?"0":xToset;
        BigInteger bg = new BigInteger(xToset);
        Uint256 x = new Uint256(bg);
        BigInteger gasPrice = new BigInteger("1000");
        BigInteger gasLimit =  new BigInteger("10000");
        String privKey ="";
        String pubKey = "";
        Credentials credentials =   Credentials.create(privKey,pubKey);
        TransactionManager tm = new RawTransactionManager(web3j,credentials, ChainId.MAIN_NET);
        String contractAddress = "0xc179d6411f6ef6ed6ea9cd7085b2de2e69b9cf4b";
        TalkCoin  talk = TalkCoin.load(contractAddress,web3j,tm,gasPrice, gasLimit);
        talk.setX(x);
        Map map = new HashMap<>();
        map.put("contractAddress",contractAddress);
        map.put("Uint256 x",x);map.put("gasPrice",gasPrice);
        map.put("TransactionManager",tm);map.put("gasLimit",gasLimit);
        map.put("web3j",web3j.toString());map.put("TalkCoin",talk);

        return map;
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public Map getX(){
        BigInteger bg = new BigInteger("0");
        Uint256 x = new Uint256(bg);
        BigInteger gasPrice = new BigInteger("1000");
        BigInteger gasLimit =  new BigInteger("10000");
        TransactionManager tm = null;
        String contractAddress = "0xc179d6411f6ef6ed6ea9cd7085b2de2e69b9cf4b";
        TalkCoin  talk = TalkCoin.load(contractAddress,web3j,tm,gasPrice, gasLimit);
        try {
            x = talk.getX().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Map map = new HashMap<>();
        map.put("contractAddress",contractAddress);
        map.put("Uint256 x",x);
        map.put("gasPrice",gasPrice);
        map.put("TransactionManager",tm);
        map.put("gasLimit",gasLimit);
        map.put("web3j",web3j.toString());
        map.put("TalkCoin",talk);

        return map;
    }
}
