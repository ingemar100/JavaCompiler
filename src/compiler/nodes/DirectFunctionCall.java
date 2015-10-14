/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.nodes;

import compiler.virtualmachine.NextNodeVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ingemar
 */
public class DirectFunctionCall extends AbstractFunctionCall{
    private String actie;
    private String waarde;

    public DirectFunctionCall(String actie, String waarde) {
        this.actie = actie;
        this.waarde = waarde;
    }
    
    public String toString(){
        return "DirectFunctionCall - actie: " + actie + ", waarde: " + waarde; 
    }

    @Override
    public void accept(NextNodeVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getParameters() {
        List l = new ArrayList<String>();
        l.add(this.actie);
        l.add(this.waarde);
        return l;
    }
}
