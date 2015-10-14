/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.compiler;

import compiler.nodes.DirectFunctionCall;
import compiler.nodes.FunctionCall;
import compiler.tokenizer.Token;
import compiler.util.LLNode;

/**
 *
 * @author Ingemar
 */
public class CompiledCondition extends CompiledStatement {

    @Override
    public CompiledStatement clone() {
        return new CompiledCondition();
    }

    public LLNode<Token> compile(LLNode<Token> currentToken)
        {
            // x == 2
            // y >= 0
            // 2 == x
            // etc
            // even kort door de bocht.

        Token leftToken = currentToken.getValue();
        String leftName = leftToken.getValue();
        currentToken = currentToken.getNext();
        Token operatorToken = currentToken.getValue();
        currentToken = currentToken.getNext();
        Token rightToken = currentToken.getValue();
        String rightName = rightToken.getValue();

        if (leftToken.getType() != Token.Soort.IDENTIFIER) {
            leftName = getNextUniqueId();
            compiled.insertLast(new DirectFunctionCall("ConstantToReturn", leftToken.getValue()));
            compiled.insertLast(new DirectFunctionCall("ReturnToVariable", leftName));
        }
        if (rightToken.getType() != Token.Soort.IDENTIFIER) {
            rightName = getNextUniqueId();
            compiled.insertLast(new DirectFunctionCall("ConstantToReturn", rightToken.getValue()));
            compiled.insertLast(new DirectFunctionCall("ReturnToVariable", rightName));
        }
        
        // ... hetzelfde voor rightname

        switch (operatorToken.getType()) {
            case EQUALS:
                compiled.insertLast(new FunctionCall("AreEqual", new String[]{leftName, rightName}));
                break;
            case NOT_EQUALS:
                compiled.insertLast(new FunctionCall("AreNotEqual", new String[]{leftName, rightName}));
                break;
            case SMALLER:
                compiled.insertLast(new FunctionCall("IsSmaller", new String[]{leftName, rightName}));
                break;
            case SMALLER_EQUALS:
                compiled.insertLast(new FunctionCall("IsSmallerOrEqual", new String[]{leftName, rightName}));
                break;
            case GREATER:
                compiled.insertLast(new FunctionCall("IsGreater", new String[]{leftName, rightName}));
                break;
            case GREATER_EQUALS:
                compiled.insertLast(new FunctionCall("IsGreaterOrEqual", new String[]{leftName, rightName}));
                break;
            default:
                break;
        }
        
        return currentToken.getNext();
    }

    @Override
    public boolean isMatch(LLNode<Token> currentToken) {
        return currentToken.getNext().getValue().getType() == Token.Soort.EQUALS 
                || currentToken.getNext().getValue().getType() == Token.Soort.NOT_EQUALS/*|| etc */
                || currentToken.getNext().getValue().getType() == Token.Soort.SMALLER/*|| etc */
                || currentToken.getNext().getValue().getType() == Token.Soort.SMALLER_EQUALS/*|| etc */
                || currentToken.getNext().getValue().getType() == Token.Soort.GREATER/*|| etc */
                || currentToken.getNext().getValue().getType() == Token.Soort.GREATER_EQUALS/*|| etc */;
    }
}
