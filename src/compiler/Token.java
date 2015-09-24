/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author Ingemar
 */
public class Token {
    private int posInLijst;
    private int regelnummer;
    private int posInRegel;
    private int type;
    private int level;
    private int partner;

    public Token(int posInLijst, int regelnummer, int posInRegel, int type, int level, int partner) {
        this.posInLijst = posInLijst;
        this.regelnummer = regelnummer;
        this.posInRegel = posInRegel;
        this.type = type;
        this.level = level;
        this.partner = partner;
    }
    
}
