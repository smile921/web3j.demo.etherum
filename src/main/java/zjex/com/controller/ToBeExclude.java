package zjex.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.web3j.crypto.*;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by frere921 on 2017/3/24.
 */
public class ToBeExclude {

    /**
     *
     * Address a50f4f3d0b5f9808c418d51b1017b7451220a737 is valid = true
     Address 0xc179d6411f6ef6ed6ea9cd7085b2de2e69b9cf4b is valid = true
     Private key 31db33a750328e91736e5cb2756a0f5f09516f24c5f582b8ce60eb46b0f2e0ca is valid = true
     public key a50f4f3d0b5f9808c418d51b1017b7451220a737 is valid = false


     * @param args
     * @throws CipherException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public static void  main(String[] args) throws CipherException,
            InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, NoSuchProviderException, IOException {
        ToBeExclude.checkAddressAndPrivateKey();
        ToBeExclude.explorToLearning();
//        ToBeExclude.parse4Keys();
    }


    public static void parse4Keys() throws IOException, CipherException {
        String password = "";
        String json = " {\"address\":\"a50f4f3d0b5f9808c418d51b1017b7451220a737\",\n" +
                "            \"crypto\":{\"cipher\":\"aes-128-ctr\",\n" +
                "            \"ciphertext\":\"31db33a750328e91736e5cb2756a0f5f09516f24c5f582b8ce60eb46b0f2e0ca\",\n" +
                "            \"cipherparams\":{\"iv\":\"bdd195c3e6afd5817a3ef985d9ec6f35\"},\n" +
                "            \"kdf\":\"scrypt\",\n" +
                "            \"kdfparams\":{\"dklen\":32,\"n\":262144,\"p\":1,\"r\":8,\n" +
                "            \"salt\":\"6f4e3a2e7a1211fb1867acb0ba26fac3a47c28087125f508f72c6df8380f18da\"},\n" +
                "            \"mac\":\"b599b3bf14af38417eae3cb7bb703097090a240944cf72b00edc564497e84f87\"},\n" +
                "            \"id\":\"d212f383-5914-4b6e-8bc5-47120849e19c\",\n" +
                "            \"version\":3}";
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        WalletFile walletFile = objectMapper.readValue(json, WalletFile.class);
        Credentials cr = Credentials.create(Wallet.decrypt(password, walletFile));
        System.out.print(cr.getEcKeyPair().getPrivateKey().toString(16)+"\n");
        System.out.print(cr.getEcKeyPair().getPublicKey().toString(16));
    }

    /**
     *  Wallet File is UTC--2017-03-28T03-00-13.545000000Z--630c60f3586ea98ddec9baab13b1efc91a77cc05.json  full path is :D:\back-end\idea_works\UTC--2017-03-28T03-00-13.545000000Z--630c60f3586ea98ddec9baab13b1efc91a77cc05.json
     address is : 0x630c60f3586ea98ddec9baab13b1efc91a77cc05
     public key is : ce1bff900a334817243435b723874240e8362be1f95fdd4b89653753c47541be22a7387ced4ad27b41f8af137356f7b1ec55acd2cbce881a11505e4950266916
     private key is :84923593245ba218f8f1ffd5596bc6ebc75a37d60efbb426dc821b3f392fd4cd

     address is : 0x630c60f3586ea98ddec9baab13b1efc91a77cc05
     public key is : 0xce1bff900a334817243435b723874240e8362be1f95fdd4b89653753c47541be22a7387ced4ad27b41f8af137356f7b1ec55acd2cbce881a11505e4950266916
     private key is :0x84923593245ba218f8f1ffd5596bc6ebc75a37d60efbb426dc821b3f392fd4cd


     {"address":"630c60f3586ea98ddec9baab13b1efc91a77cc05",
     "id":"8bf6c793-8c12-49b0-a42b-0143bd31a0a1",
     "version":3,"crypto":{"cipher":"aes-128-ctr",
     "ciphertext":"28695700d03c930291acd2d5175fe73961955fa13dbea3121dbd12778d722bae",
     "cipherparams":{"iv":"04205980c4e9e9091dec8b5d12aa53ac"},"kdf":"scrypt","kdfparams
     ":{"dklen":32,"n":262144,"p":1,"r":8,
     "salt":"53ae1ac598b8a8f88f60a2502c9346870644f15b4e0f8e795cdfdef52db94ee8"},
     "mac":"ed108cdd8f9e156a4023e69ebe38d11fc02f0ace66786edeb5aea7a35b30b00f"}}
     * */
    public static void explorToLearning() throws IOException, CipherException {

//        String wfile = WalletUtils.generateFullNewWalletFile("testDemo",new File("./"));
        String wfile ="UTC--2017-03-28T03-00-13.545000000Z--630c60f3586ea98ddec9baab13b1efc91a77cc05.json";
        File f = new File(wfile);
        System.out.println("Wallet File is "+wfile+"  full path is :" + f.getAbsolutePath());
        Credentials credentials = WalletUtils.loadCredentials("testDemo",f);
        String address = credentials.getAddress();
        String privatekey = Numeric.toHexStringWithPrefix( credentials.getEcKeyPair().getPrivateKey());
        String publickey = Numeric.toHexStringWithPrefix( credentials.getEcKeyPair().getPublicKey());
        System.out.println("address is : "+address +
                "\t\t\n public key is : "+                publickey +
                "\t\t\n private key is :" +                privatekey);
        WalletFile wf  = Wallet.createStandard("testDemo",credentials.getEcKeyPair());
        ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
        String json = objectMapper.writeValueAsString(wf);
        System.out.println(json);
    }

    public static void checkAddressAndPrivateKey(){

        String addr = "a50f4f3d0b5f9808c418d51b1017b7451220a737";
        boolean isValid = WalletUtils.isValidAddress(  addr  );
        System.out.print("Address "+addr +" is valid = " + isValid + "\n");

        addr = "0xc179d6411f6ef6ed6ea9cd7085b2de2e69b9cf4b";
        isValid  = WalletUtils.isValidAddress(addr);
        System.out.print("Address "+addr +" is valid = " + isValid + "\n");

        addr = "31db33a750328e91736e5cb2756a0f5f09516f24c5f582b8ce60eb46b0f2e0ca";
        isValid  = WalletUtils.isValidPrivateKey(addr);
        System.out.print("Private key "+addr +" is valid = " + isValid + "\n");
        addr = "a50f4f3d0b5f9808c418d51b1017b7451220a737";
        isValid  = WalletUtils.isValidPrivateKey(addr);
        System.out.print("public key "+addr +" is valid = " + isValid + "\n");

    }
}
