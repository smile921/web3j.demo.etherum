package com.zjex.smart.contract;

import com.squareup.javapoet.JavaFile;
import com.zjex.file.FileUtil;
import org.ethereum.core.CallTransaction;
import org.ethereum.solidity.compiler.CompilationResult;
import org.ethereum.solidity.compiler.SolidityCompiler;
//import org.ethereum.util.FileUtil;
import org.junit.Test;
import org.springframework.util.FileSystemUtils;
import org.web3j.codegen.SolidityFunctionWrapperGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.ethereum.solidity.compiler.SolidityCompiler.Options.*;
import static org.ethereum.solidity.compiler.SolidityCompiler.Options.METADATA;

/**
 * Created by frere921 on 2017/3/29.
 */
public class CompileSolidity2JavaTest {

    @Test
    public void defaultFuncTest() throws IOException {
        String contractSrc =
                "pragma solidity ^0.4.7;\n" +
                        "contract a {" +
                        "        function() {throw;}" +
                        "}";

        SolidityCompiler.Result res = SolidityCompiler.compile(
                contractSrc.getBytes(), true, ABI, BIN, INTERFACE, METADATA);
        System.out.println("Out: '" + res.output + "'");
        System.out.println("Err: '" + res.errors + "'");
        CompilationResult result = CompilationResult.parse(res.output);

        CompilationResult.ContractMetadata a = result.contracts.get("a");
        CallTransaction.Contract contract = new CallTransaction.Contract(a.abi);
        System.out.printf(contract.functions[0].toString());
    }

    @Test
    public void compileFilesTest() throws IOException {

        File source = new File("src/test/resources/solidity/Digger.sol");

        SolidityCompiler.Result res = SolidityCompiler.compile(source,
                true, ABI, BIN, INTERFACE, METADATA);
        System.out.println("Out: '" + res.output + "'");
        System.out.println("Err: '" + res.errors + "'");
        CompilationResult result = CompilationResult.parse(res.output);

        CompilationResult.ContractMetadata a = result.contracts.get("Digger");
        CallTransaction.Contract contract = new CallTransaction.Contract(a.abi);
        System.out.printf(contract.functions[0].toString());
    }

    @Test
    public void compileFolderFilesTest() throws Exception {

        String solName= "Digger";
        File source = new File("src/main/resources/contracts/solidity/"+solName+".sol");
        SolidityCompiler.Result res = SolidityCompiler.compile(source,
                true, ABI, BIN, INTERFACE, METADATA);
        CompilationResult result = CompilationResult.parse(res.output);
        CompilationResult.ContractMetadata md = result.contracts.get(solName);
        System.out.println(md.abi);
        System.out.println(md.bin);
        System.out.println(md.getInterface());
        System.out.println(md.metadata);
        String abif = "src/main/resources/contracts/abi/"+solName+".abi";
        String binf ="src/main/resources/contracts/bin/"+solName+".bin";
        FileUtil.writeFile(abif,md.abi);
        FileUtil.writeFile(binf,md.bin);
//        SolidityFunctionWrapperGenerator solGenerator  ;
        String packageName ="zjex.com.smart.contracs";
        String outputDir = "src/test/java";
        String[] args = {  "generate" ,binf, abif , "-p" ,packageName, "-o", outputDir};
        SolidityFunctionWrapperGenerator.run( args);
    }

    @Test
    public void listFileNameTest(){
        String dir = "src/main/resources/contracts/solidity";
        List<String> lst = FileUtil.obtainSolFileNames(dir);
        System.out.print(lst);
    }


}
