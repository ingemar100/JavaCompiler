/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.nodes;

import compiler.util.LLNode;

/**
 *
 * @author Ingemar
 */
public class ConditionalJump extends Action {
    private LLNode<Action> ifTrue;
    private LLNode<Action> ifFalse;
    
    public ConditionalJump(LLNode<Action> ifTrue, LLNode<Action> end) {
        this.ifTrue = ifTrue;
        this.ifFalse = end;
    }
    
    @Override
    public String toString(){
        return "Conditional Jump - if true: go to " + ifTrue + " else: go to " + ifFalse;
    }
}
