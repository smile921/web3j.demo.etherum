package com.zjex.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frere921 on 2017/3/30.
 */
public class FileUtil {
    public static void writeFile(String file,String content){
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> obtainSolFileNames(String dirName){
        File fileDir = new File(dirName);
        List<String> lst = new ArrayList<>();
        if(fileDir.isDirectory()){
            File[] files = fileDir.listFiles();
            for(File f : files){
                if (f.isFile()){
                    String name =  f.getName();
                    if(name.endsWith(".sol")){
                        lst.add(name.substring(0,name.length()-4));
                    }
                }
            }
        }

        return lst;
    }
}
