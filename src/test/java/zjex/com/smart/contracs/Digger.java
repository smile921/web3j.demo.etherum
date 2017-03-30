package zjex.com.smart.contracs;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version null.
 */
public final class Digger extends Contract {
    private static final String BINARY = "606060405234610000575b610182806100196000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806321835af61461003e575b610000565b34610000576100596004808035906020019091905050610073565b604051808215151515815260200191505060405180910390f35b600060008214156100875760019050610151565b3073ffffffffffffffffffffffffffffffffffffffff1660405180807f5f5f6469672875696e7432353629000000000000000000000000000000000000815250600e01905060405180910390207c01000000000000000000000000000000000000000000000000000000009004600184036040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018082815260200191505060006040518083038160008760325a03f292505050151561015057610000565b5b9190505600a165627a7a723058205892cf640d42095f30e11e1db1c130683e0d5d4253fc9b5d2e605ffa907089230029";

    private Digger(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Digger(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<Bool> __dig(Uint256 n) {
        Function function = new Function("__dig", 
                Arrays.<Type>asList(n), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<Digger> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(Digger.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<Digger> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(Digger.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Digger load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Digger(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Digger load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Digger(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
