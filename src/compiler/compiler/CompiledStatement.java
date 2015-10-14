/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.compiler;

import compiler.nodes.Action;
import compiler.tokenizer.Token;
import compiler.util.AbrahamLinkedList;
import compiler.util.LLNode;

/**
 *
 * @author Ingemar
 */
public abstract class CompiledStatement{

    private static int nextUniqueId = 1;
    protected AbrahamLinkedList<Action> compiled = new AbrahamLinkedList<>();
    
    public abstract LLNode<Token> compile(LLNode<Token> currentToken) throws Exception;
    
    public abstract boolean isMatch(LLNode<Token> token);
    
    @Override
    public abstract CompiledStatement clone();
    
    public AbrahamLinkedList<Action> getCompiled(){
        return compiled;
    }
    
    public String getNextUniqueId(){
        return "$" + nextUniqueId++;
    }
}
