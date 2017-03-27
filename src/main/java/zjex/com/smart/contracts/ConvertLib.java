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
public final class ConvertLib extends Contract {
    private static final String BINARY = "60606040523415600b57fe5b5b60748061001a6000396000f300606060405263ffffffff60e060020a60003504166396e4ee3d81146020575bfe5b602c600435602435603e565b60408051918252519081900360200190f35b8181025b929150505600a165627a7a72305820443d2180125083869356e6ac44d65ba67da480967e3f13f25369909ede4b8a910029";

    private ConvertLib(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private ConvertLib(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> convert(Uint256 amount, Uint256 conversionRate) {
        Function function = new Function("convert", Arrays.<Type>asList(amount, conversionRate), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<ConvertLib> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(ConvertLib.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<ConvertLib> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(ConvertLib.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static ConvertLib load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ConvertLib(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ConvertLib load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ConvertLib(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
