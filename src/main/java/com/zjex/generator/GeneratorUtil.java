package com.zjex.generator;

import com.zjex.file.FileUtil;
import org.ethereum.solidity.compiler.CompilationResult;
import org.ethereum.solidity.compiler.SolidityCompiler;
import org.web3j.codegen.SolidityFunctionWrapperGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.ethereum.solidity.compiler.SolidityCompiler.Options.*;

/**
 * Created by frere921 on 2017/3/30.
 */
public class GeneratorUtil {


    /**
     * 把 src main resources contracts solidity 目录下的只能合约编译输出 abi 和bin 文件，
     * 再跟进abi 和 bin 文件编译输出web3j的java 类文件 ，输出到包目录 src main java
     * 包名称  zjex.com.smart.contracs
     * */
    public static void generate2JavaSrc(String solName,String packageName) throws Exception {
        //        String packageName ="zjex.com.smart.contracts";
        String outputDir = "src/main/java";
        String solSrc = "src/main/resources/contracts/solidity/"+solName+".sol";
        String abif = "src/main/resources/contracts/abi/"+solName+".abi";
        String binf ="src/main/resources/contracts/bin/"+solName+".bin";
        GeneratorUtil.generate2JavaSrc(solName,solSrc,abif,binf,packageName,outputDir);
    }

    public static void generate2JavaSrc(String solName,String solSrc,String abiSrc,String binSrc,String packageName,String outputDir) throws Exception {
        //  String solName= "Digger";
        File source = new File(solSrc);
        SolidityCompiler.Result res = SolidityCompiler.compile(source,
                true, ABI, BIN, INTERFACE, METADATA);
        CompilationResult result = CompilationResult.parse(res.output);
        CompilationResult.ContractMetadata md = result.contracts.get(solName);
        //        System.out.println(md.abi);
        //        System.out.println(md.bin);
        //        System.out.println(md.getInterface());
        //        System.out.println(md.metadata);
        FileUtil.writeFile(abiSrc,md.abi);
        FileUtil.writeFile(binSrc,md.bin);
        String[] args = {  "generate" ,binSrc, abiSrc , "-p" ,packageName, "-o", outputDir};
        SolidityFunctionWrapperGenerator.run( args);
    }

    public static void generateDirSols2JavaSrc(String dir,String packageName) throws Exception {
        List<String>lst = FileUtil.obtainSolFileNames(dir);
        for( String solName : lst){
            GeneratorUtil.generate2JavaSrc(solName,packageName);
        }

    }

    public static void main(String[] args) throws Exception {
        if(args == null||args.length==0){
            GeneratorUtil.exitError(" --dir  solidity file path \n --packageName the generated java class package name ");
        }
        System.out.println( "args : "+args[0]);

        args = args[0].split(" ");
        String dir = GeneratorUtil.parseParameterArgument(args,"--dir");;
        String packageName = GeneratorUtil.parseParameterArgument(args,"--packageName");;
        if ( packageName ==null ||                packageName.trim().length()==0                ){
            packageName= "zjex.com.smart.contracts";
        }
        if( dir!=null&& dir.trim().length() >0){
            GeneratorUtil.generateDirSols2JavaSrc(dir,packageName);
        }else{
            GeneratorUtil.exitError(" --dir  solidity file path");
        }
    }

   public static String parseParameterArgument(String[] args, String... parameters) {
        for (String parameter : parameters) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals(parameter)
                        && i + 1 < args.length) {
                    String parameterValue = args[i + 1];
                    if (!parameterValue.startsWith("-")) {
                        return parameterValue;
                    }
                }
            }
        }
        return "";
    }
    public  static void exitError(String message) {
        System.err.println(message);
        System.exit(1);
    }
}
