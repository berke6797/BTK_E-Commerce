package com.btk.utility;

import java.util.UUID;

public class CodeGenerator {
    public static String generateCode(){
        String code = UUID.randomUUID().toString(); //örnek UUID --> 1b9318d9-e123-4744-8ef2-39603e3218aa
        String[] data = code.split("-");
        String newCode="";
        for (String str : data){
            newCode += str.substring(0,2);
        }
        return newCode;
    }
}
