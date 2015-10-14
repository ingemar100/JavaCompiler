/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.nodes;

import compiler.virtualmachine.NextNodeVisitor;

/**
 *
 * @author Ingemar
 */
public class DoNothing extends Action {
    public String toString(){
        return "Doe niets: " + super.toString();
    }

    @Override
    public void accept(NextNodeVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
