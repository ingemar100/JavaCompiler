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
public class CompiledWhile extends CompiledStatement {

    @Override
    public LLNode<Token> compile(LLNode<Token> currentToken) throws Exception {
        // while ( .... ) endwhile
        currentToken = currentToken.getNext().getNext(); // verplaats 2 naar rechts (voorbij de ( )

        // De factory bepaalt wat er verder gebeurt.
        CompiledStatement condition = CompilerFactory.getInstance().createCompiledStatement(currentToken);
        currentToken = condition.compile(currentToken).getNext();
        // Na de rightCompiled uitgevoerd te hebben hoeven we alleen maar de ReturnValue op te halen en in een variable op te slaan.

        LLNode<Action> begin = compiled.insertLast(new DoNothing());
        compiled.insertLast(condition.compiled);
        LLNode<Action> ifTrue = compiled.insertLast(new DoNothing());

        if (currentToken.getValue().getType() != Token.Soort.END_WHILE) {
            CompiledStatement st = CompilerFactory.getInstance().createCompiledStatement(currentToken);
            currentToken = st.compile(currentToken);
            //statement block
            compiled.insertLast(st.compiled);
        }
        compiled.insertLast(new Jump(begin));
        LLNode<Action> end = compiled.insertLast(new DoNothing());
        compiled.insertBefore(ifTrue, new ConditionalJump(ifTrue, end));

        if (currentToken.getValue().getType() != Token.Soort.END_WHILE) {
            throw new Exception();
        }
        return currentToken.getNext();
    }

    @Override
    public CompiledStatement clone() {
        return new CompiledWhile();
    }

    @Override
    public boolean isMatch(LLNode<Token> currentToken) {
        return currentToken.getValue().getType() == Token.Soort.WHILE;
    }
}
