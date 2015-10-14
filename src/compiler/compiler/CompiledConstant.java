/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.compiler;

import compiler.nodes.DirectFunctionCall;
import compiler.tokenizer.Token;
import compiler.util.LLNode;

/**
 *
 * @author Ingemar
 */
public class CompiledConstant extends CompiledStatement {

    @Override
    public boolean isMatch(LLNode<Token> token) {
        return token.getValue().getType() == Token.Soort.NUMBER;
    }

    @Override
    public CompiledStatement clone() {
        return new CompiledConstant();
    }

    @Override
    public LLNode<Token> compile(LLNode<Token> currentToken) throws Exception {
        String variableName = this.getNextUniqueId();
        this.compiled.insertLast(new DirectFunctionCall("ConstantToReturn", currentToken.getValue().getValue()));
        this.compiled.insertLast(new DirectFunctionCall("ReturnToVariable", variableName));
        return currentToken.getNext();
    }

}
