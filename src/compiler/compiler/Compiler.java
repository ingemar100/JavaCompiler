/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.compiler;

import compiler.tokenizer.Token;

/**
 *
 * @author Ingemar
 */
public abstract class Compiler {
    public abstract void compile();
    
    public Token getLastToken(){
        return null;
    }
}
