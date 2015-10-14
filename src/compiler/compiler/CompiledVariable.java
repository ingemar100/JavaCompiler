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
public class CompiledVariable extends CompiledStatement{


    @Override
    public boolean isMatch(LLNode<Token> token) {
        return token.getValue().getType() == Token.Soort.IDENTIFIER;
    }

    @Override
    public CompiledStatement clone() {
        return new CompiledVariable();
    }

    @Override
    public LLNode<Token> compile(LLNode<Token> currentToken) throws Exception {
        String variableName = currentToken.getValue().getValue();
        this.compiled.insertLast(new DirectFunctionCall("ReturnToVariable", variableName));
        return currentToken.getNext();
    }

    
}
