/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.tokenizer;

/**
 *
 * @author Ingemar
 */
public class HarrisonFordException extends Exception{
    
    public HarrisonFordException(){
        
    }
    
    public HarrisonFordException(String message, Token t){
        super(message + " \nRegel: " + t.getRegelnummer() + " \nPositie: " + t.getPosInRegel());
    }
}
