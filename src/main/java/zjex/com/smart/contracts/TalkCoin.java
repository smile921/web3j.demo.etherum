package zjex.com.smart.contracts;

import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.1.0.
 */
public final class TalkCoin extends Contract {
    private static final String BINARY = "60606040523415600b57fe5b5b5b5b60978061001c6000396000f300606060405263ffffffff60e060020a6000350416634018d9aa8114602a5780635197c7aa14603c575bfe5b3415603157fe5b603a600435605b565b005b3415604357fe5b60496064565b60408051918252519081900360200190f35b60008190555b50565b6000545b905600a165627a7a723058201c6cf9df4844bef20158f034c9b7ae0d8a9e4f32c97e03186173f3f6b2935d2e0029";

    private TalkCoin(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private TalkCoin(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> setX(Uint256 x) {
        Function function = new Function("setX", Arrays.<Type>asList(x), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> getX() {
        Function function = new Function("getX", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<TalkCoin> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(TalkCoin.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<TalkCoin> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(TalkCoin.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static TalkCoin load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TalkCoin(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static TalkCoin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TalkCoin(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
