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
public class CompiledRValue extends CompiledStatement {

    @Override
    public LLNode<Token> compile(LLNode<Token> currentToken) throws Exception {
        // ( x + 3 )

        currentToken = currentToken.getNext(); //voorbij haakje
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

        switch (operatorToken.getType()) {
            case PLUS:
                compiled.insertLast(new FunctionCall("Add", new String[]{leftName, rightName}));
                break;
            case MINUS:
                compiled.insertLast(new FunctionCall("Subtract", new String[]{leftName, rightName}));
                break;
            case MULTIPLY:
                compiled.insertLast(new FunctionCall("Multiply", new String[]{leftName, rightName}));
                break;
            case DIVIDE:
                compiled.insertLast(new FunctionCall("Divide", new String[]{leftName, rightName}));
                break;
            default:
                break;
        }

        currentToken = currentToken.getNext();
        if (currentToken.getValue().getType() != Token.Soort.ELLIPSIS_CLOSE) {
            throw new Exception();
        }

        return currentToken.getNext();
    }

    @Override
    public boolean isMatch(LLNode<Token> token) {
        return token.getValue().getType() == Token.Soort.ELLIPSIS_OPEN;
    }

    @Override
    public CompiledStatement clone() {
        return new CompiledRValue();
    }

}
