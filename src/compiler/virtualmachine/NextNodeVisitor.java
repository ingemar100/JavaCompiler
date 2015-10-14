/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.virtualmachine;

import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DirectFunctionCall;
import compiler.nodes.DoNothing;
import compiler.nodes.FunctionCall;
import compiler.nodes.Jump;
import compiler.util.LLNode;

/**
 *
 * @author Ingemar
 */
public class NextNodeVisitor {

    public LLNode<Action> nextNode;

    public void visit(LLNode<Action> node) {
        if (node.getValue() instanceof DoNothing) {
            nextNode = node.getNext();
        } else if (node.getValue() instanceof Jump) {
            nextNode = ((Jump) node.getValue()).getJumpToNode();
        } else if (node.getValue() instanceof ConditionalJump) {
            
        } else if (node.getValue() instanceof DirectFunctionCall) {

        } else if (node.getValue() instanceof FunctionCall) {

        }
    }

    public LLNode<Action> getNextNode() {
        return nextNode;
    }

    private void setNextNode() {
        this.nextNode = nextNode;
    }
}
