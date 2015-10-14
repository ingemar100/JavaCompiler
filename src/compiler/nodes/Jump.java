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
public class Jump extends Action {
    private LLNode<Action> to;

    public Jump(LLNode<Action> to) {
        this.to = to;
    }
    
}
