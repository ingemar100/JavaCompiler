/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.nodes;

import compiler.virtualmachine.NextNodeVisitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ingemar
 */
public class FunctionCall extends AbstractFunctionCall{
    private String functionName;
    private String[] args;

    public FunctionCall(String name, String[] args) {
        this.functionName = name;
        this.args = args;
    }
    
    @Override
    public String toString(){
        String s = "FunctionCall - functienaam: " + functionName + ", args: ";
        for (int i = 0; i < args.length; i++){
            s += args[i] + ",";
        }
        return s;
    }

    @Override
    public void accept(NextNodeVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<String> getParameters() {
        List<String> l = new ArrayList();
        l.add(functionName);
        l.addAll(Arrays.asList(args));
        return l;
    }
}
