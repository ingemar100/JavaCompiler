/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.nodes;

import compiler.util.LLNode;
import compiler.virtualmachine.NextNodeVisitor;

/**
 *
 * @author Ingemar
 */
public class Jump extends Action {
    private LLNode<Action> jumpToNode;

    public Jump(LLNode<Action> to) {
        this.jumpToNode = to;
    }

    @Override
    public void accept(NextNodeVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LLNode<Action> getJumpToNode() {
        return jumpToNode;
    }
    
}
