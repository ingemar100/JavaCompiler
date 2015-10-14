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
public class CompilerException extends Exception{
    public CompilerException(String mess, Token t){
        super("\n" + mess + "\nat line " + t.getRegelnummer() + " pos: " + t.getPosInRegel());
    }
}
