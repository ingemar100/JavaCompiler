/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.compiler;

import compiler.nodes.Action;
import compiler.nodes.ConditionalJump;
import compiler.nodes.DoNothing;
import compiler.nodes.Jump;
import compiler.tokenizer.Token;
import compiler.util.LLNode;

/**
 *
 * @author Ingemar
 */
public class CompiledIfElse extends CompiledStatement {

    @Override
    public LLNode<Token> compile(LLNode<Token> currentToken) throws Exception {
        currentToken = currentToken.getNext().getNext();

        CompiledStatement condition = CompilerFactory.getInstance().createCompiledStatement(currentToken);
        currentToken = condition.compile(currentToken).getNext();

        compiled.insertLast(new DoNothing());
        compiled.insertLast(condition.compiled);
        LLNode<Action> ifTrue = compiled.insertLast(new DoNothing());

        if (currentToken.getValue().getType() != Token.Soort.END_IF) {
            CompiledStatement st = CompilerFactory.getInstance().createCompiledStatement(currentToken);
            currentToken = st.compile(currentToken);
            //statement block
            compiled.insertLast(st.compiled);
        }
        LLNode<Action> beginElse = compiled.insertLast(new DoNothing());
        compiled.insertBefore(ifTrue, new ConditionalJump(ifTrue, beginElse));

        if (currentToken.getValue().getType() != Token.Soort.END_IF) {
            throw new CompilerException("Expectef endif keyword", currentToken.getValue());
        }

        currentToken = currentToken.getNext().getNext(); //after else
        CompiledStatement st = CompilerFactory.getInstance().createCompiledStatement(currentToken);
        currentToken = st.compile(currentToken);
        compiled.insertLast(st.compiled);
        
        LLNode<Action> end = compiled.insertLast(new DoNothing());
        compiled.insertBefore(beginElse, new Jump(end));
        return currentToken.getNext();
    }

    @Override
    public CompiledStatement clone() {
        return new CompiledIfElse();
    }

    @Override
    public boolean isMatch(LLNode<Token> currentToken) {
        return currentToken.getValue().getType() == Token.Soort.IF_WITH_ELSE;
    }
}
