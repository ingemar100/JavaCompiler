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
public class CompiledAssignment extends CompiledStatement {

    @Override
    public LLNode<Token> compile(LLNode<Token> currentToken) throws Exception {
        // x = ....
        String variableName = currentToken.getValue().getValue(); // x
        currentToken = currentToken.getNext().getNext(); // verplaats 2 naar rechts (voorbij de = )

        // De factory bepaalt wat er verder gebeurt.
        CompiledStatement rightCompiled = CompilerFactory.getInstance().createCompiledStatement(currentToken);
        currentToken = rightCompiled.compile(currentToken);
        // Na de rightCompiled uitgevoerd te hebben hoeven we alleen maar de ReturnValue op te halen en in een variable op te slaan.
        this.compiled.insertLast(rightCompiled.compiled);
        this.compiled.insertLast(new DirectFunctionCall("ReturnToVariable", variableName));
        
        if (currentToken.getValue().getType() != Token.Soort.SEMICOLON){
            throw new CompilerException("Expected semicolon", currentToken.getValue());
        }
        return currentToken.getNext();
    }

    @Override
    public CompiledStatement clone() {
        return new CompiledAssignment();
    }

    @Override
    public boolean isMatch(LLNode<Token> currentToken) {
        // We zijn een match als we een identifier met daarna een = tegenkomen.
        return currentToken.getValue().getType() == Token.Soort.IDENTIFIER
                && currentToken.getNext().getValue().getType() == Token.Soort.ASSIGN;
    }
}
