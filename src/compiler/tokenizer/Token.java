/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.tokenizer;

public class Token {
    private int posInLijst;
    private int regelnummer;
    private int posInRegel;
    private Soort type;
    private int level;
    private Token partner;
    private String value;
    public static enum Soort{ NOT_EQUALS, FUNCTION, BRACKET_OPEN, BRACKET_CLOSE, IF, IF_WITH_ELSE, END_IF, END_WHILE, END_ELSE, END_FOR, ELSE, FOR, IDENTIFIER, EQUALS, NUMBER, SEMICOLON, WHILE, ELLIPSIS_OPEN, GREATER_EQUALS, SMALLER_EQUALS, GREATER, SMALLER, ELLIPSIS_CLOSE, BRACKETS_OPEN, BRACKETS_CLOSE, MINUS, PLUS, MULTIPLY, DIVIDE, ASSIGN };

    public Token(int posInLijst, int regelnummer, int posInRegel, Soort type, int level, Token partner, String value) {
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

    public Token getPartner() {
        return partner;
    }

    public void setPartner(Token partner) {
        this.partner = partner;
    }

    public int getLevel() {
        return level;
    }

    public int getRegelnummer() {
        return regelnummer;
    }

    public int getPosInRegel() {
        return posInRegel;
    }

    public void setType(Soort type) {
        this.type = type;
    }
    
    public String toString(){
        String part = (partner == null) ? "" : partner.getPosInLijst() + "";
        return getPosInLijst() + ": " + getType() + ": " + getValue() + " partner: " + part + " level: " + getLevel();
    }
}
