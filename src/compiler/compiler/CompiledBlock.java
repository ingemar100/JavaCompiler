/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.compiler;

import compiler.tokenizer.Token;
import compiler.util.LLNode;

/**
 *
 * @author Ingemar
 */
public class CompiledBlock extends CompiledStatement{

    @Override
    public LLNode<Token> compile(LLNode<Token> currentToken) throws Exception {
        CompilerFactory factory = CompilerFactory.getInstance();

        while (currentToken != null) {
            CompiledStatement statement = factory.createCompiledStatement(currentToken);
            currentToken = statement.compile(currentToken);
            this.compiled.insertLast(statement.compiled);
        }
        
        return currentToken;
    }

    @Override
    public boolean isMatch(LLNode<Token> token) {
        return false;
    }

    @Override
    public CompiledStatement clone() {
        return new CompiledBlock();
    }
    
}
