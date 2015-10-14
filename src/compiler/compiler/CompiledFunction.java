/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.compiler;

import compiler.nodes.FunctionCall;
import compiler.tokenizer.Token;
import compiler.util.LLNode;

/**
 *
 * @author Ingemar
 */
public class CompiledFunction extends CompiledStatement {

    @Override
    public LLNode<Token> compile(LLNode<Token> currentToken) throws Exception {
        String functionName = currentToken.getValue().getValue();
        currentToken = currentToken.getNext(); //begin rvalue
        
        CompiledStatement argument = CompilerFactory.getInstance().createCompiledStatement(currentToken);
        String varName = currentToken.getValue().getValue();
        currentToken = argument.compile(currentToken);
        
        compiled.insertLast(argument.compiled);
        compiled.insertLast(new FunctionCall(functionName, varName));
        
        if (currentToken.getValue().getType() != Token.Soort.SEMICOLON){
            throw new CompilerException("Expected semicolon", currentToken.getValue());
        }
        return currentToken.getNext();
    }

    @Override
    public boolean isMatch(LLNode<Token> token) {
        return token.getValue().getType() == Token.Soort.FUNCTION;
    }

    @Override
    public CompiledStatement clone() {
        return new CompiledFunction();
    }

}
