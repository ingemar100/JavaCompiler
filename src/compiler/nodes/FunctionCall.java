/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.nodes;

/**
 *
 * @author Ingemar
 */
public class FunctionCall extends AbstractFunctionCall{
    private String functionName;
    private String arg1;
    private String arg2;

    public FunctionCall(String name, String arg1, String arg2) {
        this.functionName = name;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    
    public FunctionCall(String name, String arg){
        this.functionName = name;
        this.arg1 = arg;
    }
    
    public String toString(){
        return "FunctionCall - functienaam: " + functionName + ", args: " + arg1 + "," + arg2;
    }
}
