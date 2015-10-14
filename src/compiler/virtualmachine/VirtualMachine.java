/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.virtualmachine;

import compiler.nodes.AbstractFunctionCall;
import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.Jump;
import compiler.util.AbrahamLinkedList;
import compiler.util.LLNode;
import java.util.HashMap;

/**
 *
 * @author Ingemar
 */
public class VirtualMachine {

    private String returnValue;
    private HashMap<String, Variable> variables = new HashMap();
    private HashMap<String, BaseCommand> commands;

    public VirtualMachine() {
        fillDic();
    }

    public void Run(AbrahamLinkedList list) throws Exception {
        LLNode<Action> currentNode = list.getFirst();
        NextNodeVisitor visitor = new NextNodeVisitor();
        System.out.println("\n\n");

        while (currentNode != null) {
            // Doe iets met de huidige node:  
            if (currentNode.getValue() instanceof AbstractFunctionCall) {
                AbstractFunctionCall actionNode = (AbstractFunctionCall) currentNode.getValue();
                String name = actionNode.getParameters().get(0);
                System.out.println(name);
                commands.get(name).execute(this, actionNode.getParameters());
            } else {
                System.out.println(currentNode.getValue().toString());
            }

            // Bepaal de volgende node: 
            // visitor pattern is niet zinvol 
//            currentNode.getValue().accept(visitor);
//            currentNode = visitor.getNextNode();
            if (currentNode.getValue() instanceof Jump) {
                currentNode = ((Jump) currentNode.getValue()).getJumpToNode();
            } else if (currentNode.getValue() instanceof ConditionalJump) {
                currentNode = ((ConditionalJump) currentNode.getValue()).getJumpToNode(this);
            } else {
                currentNode = currentNode.getNext();
            }
        }
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public void setVariable(String get, String returnValue) {
        this.variables.put(get, new Variable(returnValue, Variable.Type.NUMBER));
    }

    public Variable getVariable(String name) {
        return variables.get(name);
    }

    private void fillDic() {
        commands = new HashMap();
        commands.put("Add", new PlusCommand());
        commands.put("Subtract", new SubtractCommand());
        commands.put("Multiply", new MultiplyCommand());
        commands.put("Divide", new DivideCommand());
        commands.put("print", new PrintCommand());
        commands.put("AreEqual", new AreEqualCommand());
        commands.put("AreNotEqual", new AreNotEqualCommand());
        commands.put("IsSmaller", new IsSmallerCommand());
        commands.put("IsSmallerOrEqual", new IsSmallerOrEqualCommand());
        commands.put("IsGreater", new IsGreaterCommand());
        commands.put("IsGreaterOrEqual", new IsGreaterOrEqualCommand());
        commands.put("ConstantToReturn", new ReturnConstantCommand());
        commands.put("ReturnToVariable", new ReturnToVariableCommand());
    }
}
