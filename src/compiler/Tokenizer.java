/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import compiler.HarrisonFordException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tokenizer {

    HashMap<String, Token.Soort> dic = new HashMap();
    HashSet<Token.Soort> openTokens = new HashSet();
    HashSet<Token.Soort> closeTokens = new HashSet();

    public Tokenizer() throws HarrisonFordException {
        fillDictionary();
        parse();
    }

    public void fillDictionary() {
//        d.put("a", IDENTIFIER);
//        d.put("1", Token.tokenSoort.NUMBER);
        dic.put("==", Token.Soort.EQUALS);
        dic.put(";", Token.Soort.SEMICOLON);
        dic.put("while", Token.Soort.WHILE);
        dic.put("endwhile", Token.Soort.END_WHILE);
        dic.put("if", Token.Soort.IF);
        dic.put("endif", Token.Soort.END_IF);
        dic.put("else", Token.Soort.ELSE);
        dic.put("endelse", Token.Soort.END_ELSE);
        dic.put("for", Token.Soort.FOR);
        dic.put("endfor", Token.Soort.END_FOR);
        dic.put("(", Token.Soort.ELLIPSIS_OPEN);
        dic.put(")", Token.Soort.ELLIPSIS_CLOSE);
        dic.put(">=", Token.Soort.GREATER_EQUALS);
        dic.put("<=", Token.Soort.SMALLER_EQUALS);
        dic.put("<", Token.Soort.SMALLER);
        dic.put(">", Token.Soort.GREATER);
        dic.put("{", Token.Soort.BRACKETS_OPEN);
        dic.put("}", Token.Soort.BRACKETS_CLOSE);
        dic.put("-", Token.Soort.MINUS);
        dic.put("+", Token.Soort.PLUS);
        dic.put("*", Token.Soort.MULTIPLY);
        dic.put("/", Token.Soort.DIVIDE);
        dic.put("=", Token.Soort.ASSIGN);

        openTokens.add(Token.Soort.ELLIPSIS_OPEN);
        openTokens.add(Token.Soort.BRACKETS_OPEN);
        openTokens.add(Token.Soort.IF);
        openTokens.add(Token.Soort.WHILE);
        openTokens.add(Token.Soort.ELSE);
        openTokens.add(Token.Soort.FOR);
        openTokens.add(Token.Soort.BRACKET_OPEN);
        openTokens.add(Token.Soort.ELLIPSIS_OPEN);
        openTokens.add(Token.Soort.WHILE);
        openTokens.add(Token.Soort.ELSE);

        closeTokens.add(Token.Soort.ELLIPSIS_CLOSE);
        closeTokens.add(Token.Soort.BRACKETS_CLOSE);
        closeTokens.add(Token.Soort.END_IF);
        closeTokens.add(Token.Soort.END_WHILE);
        closeTokens.add(Token.Soort.END_ELSE);
        closeTokens.add(Token.Soort.END_FOR);
        closeTokens.add(Token.Soort.BRACKET_CLOSE);
        closeTokens.add(Token.Soort.ELLIPSIS_CLOSE);
        closeTokens.add(Token.Soort.END_WHILE);
        closeTokens.add(Token.Soort.END_ELSE);
    }

    public void parse() throws HarrisonFordException {
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        Scanner inFile1;
        String regel = "";

        try {
            Scanner scanner = new Scanner(new File("file.txt"));
            inFile1 = scanner.useDelimiter("\\n");
//            inFile1.

            List<Token> temps = new ArrayList<Token>();
            int regelnummer = 1;
            int posInLijst = 1;
            int level = 1;
            Stack<Integer> partners = new Stack<>();

            while (inFile1.hasNext()) {
                //parse line
                boolean endOfLine = false;
                regel = inFile1.next();
                regel = regel.trim();
                if (regel.isEmpty()) {
                    continue;
                }

                String[] values = regel.split("\\s");

                for (int i = 0; i < values.length; i++) {
                    if (endOfLine) {
                        throw new HarrisonFordException("Reached end of line");
                    }
                    int partner = 0;
                    Token.Soort soort = vindSoort(values[i]);
                    int thisLevel = level;
                    if (levelPlus(soort)) {
                        level++;
                        partners.push(posInLijst);
                    } else if (levelMin(soort)) {
                        level--;
                        thisLevel--;
                        partner = partners.pop();

                        Token partnerToken = temps.get(partner - 1);
                        partnerToken.setPartner(posInLijst);

                    }
                    Token t = new Token(posInLijst++, regelnummer, 1, soort, thisLevel, partner, values[i]);
                    temps.add(t);

                    if (t.getType() == Token.Soort.SEMICOLON) {
                        endOfLine = true;
                    }
                    if (t.getType() == Token.Soort.ELSE) {
                        Token vorig = temps.get(posInLijst - 3);
                        if (vorig.getType() != Token.Soort.END_IF) {
                            throw new HarrisonFordException("Else without if");
                        }
                    }
                }

                regelnummer++;
            }
            inFile1.close();

            printTokens(temps);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tokenizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Token.Soort vindSoort(String woord) {
        if (dic.containsKey(woord)) {
            return dic.get(woord);
        } else {
            if (woord.matches("^-?\\d+$")) {
                return Token.Soort.NUMBER;
            } else {
                return Token.Soort.IDENTIFIER;
            }
        }
    }

    private void printTokens(List<Token> temps) {
        for (Token t : temps) {
            System.out.println(t.getPosInLijst() + ": " + t.getType() + ": " + t.getValue() + " partner: " + t.getPartner() + " level: " + t.getLevel());
        }
    }

    boolean levelPlus(Token.Soort soort) {
        if (openTokens.contains(soort)) {
            return true;
        } else {
            return false;
        }
    }

    boolean levelMin(Token.Soort soort) {
        if (closeTokens.contains(soort)) {
            return true;
        } else {
            return false;
        }
    }
}
