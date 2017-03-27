package zjex.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * Created by frere921 on 2017/3/23.
 */

@Controller
@RequestMapping("/")
public class DemoController {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Parity parity;

    @RequestMapping(value = "version/demo",method = RequestMethod.GET )
    @ResponseBody
    public String demoWeb(){
        return "{demo:v1,web3j:1.0}";
    }
    @RequestMapping(value = "version/async",method = RequestMethod.GET )
    @ResponseBody
    public String demoWeb3jCallAsync(){
        // defaults to http://localhost:8545/
        Web3j web3 = Web3j.build(new HttpService());
        web3 = web3j;
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();

        return  clientVersion;
    }

    @RequestMapping(value = "version/sync",method = RequestMethod.GET )
    @ResponseBody
    public String demoWeb3jCallSync(){
        // defaults to http://localhost:8545/
        Web3j web3 = Web3j.build(new HttpService());
        web3 = web3j;
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3.web3ClientVersion().send();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();

        return  clientVersion;
    }

    @RequestMapping(value = "block/sync",method = RequestMethod.GET )
    @ResponseBody
    public EthBlock.Block demoWeb3jCallSync(@RequestParam String number){

        EthBlock block = null;
        try {
            BigInteger bn = new BigInteger(number);
            DefaultBlockParameterNumber dbpn = new DefaultBlockParameterNumber(bn);
            block = web3j.ethGetBlockByNumber(dbpn,true ).send();

        } catch (IOException e) {
            e.printStackTrace();
        }
        block.getBlock();
        return  block.getBlock();
    }


    public void unlockAccount(){
          // defaults to http://localhost:8545/
        PersonalUnlockAccount personalUnlockAccount = null;
        try {
            personalUnlockAccount = parity.personalUnlockAccount("a50f4f3d0b5f9808c418d51b1017b7451220a737",
                    "frere921",new BigInteger("30000")).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (personalUnlockAccount.accountUnlocked()) {
            // send a transaction, or
            // use parity.personalSignAndSendTransaction() to do it all in one
            System.out.println("account a50f4f3d0b5f9808c418d51b1017b7451220a737 unloacked");
        }
    }

}
