/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

public class Token {
    private int posInLijst;
    private int regelnummer;
    private int posInRegel;
    private Soort type;
    private int level;
    private int partner;
    private String value;
    public static enum Soort{ IDENTIFIER, EQUALS, NUMBER, SEMICOLON, WHILE, ELLIPSIS_OPEN, GREATER_EQUALS, SMALLER_EQUALS, GREATER, SMALLER, ELLIPSIS_CLOSE, BRACKETS_OPEN, BRACKETS_CLOSE, MINUS, PLUS, MULTIPLY, DIVIDE, ASSIGN };

    public Token(int posInLijst, int regelnummer, int posInRegel, Soort type, int level, int partner, String value) {
        this.posInLijst = posInLijst;
        this.regelnummer = regelnummer;
        this.posInRegel = posInRegel;
        this.type = type;
        this.level = level;
        this.partner = partner;
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
    
    public Soort getType(){
        return type;
    }

    public int getPosInLijst() {
        return posInLijst;
    }

    public int getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        this.partner = partner;
    }
    
}
