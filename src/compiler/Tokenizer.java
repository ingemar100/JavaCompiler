/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ingemar
 */
public class Tokenizer {
    HashMap<String, Token.Soort> d = new HashMap();
    
    public Tokenizer() {
        fillDictionary();
        parse();
    }
    
    public void fillDictionary(){
//        d.put("a", IDENTIFIER);
//        d.put("1", Token.tokenSoort.NUMBER);
        d.put("==", Token.Soort.EQUALS);
        d.put(";", Token.Soort.SEMICOLON);
        d.put("while", Token.Soort.WHILE);
        d.put("(", Token.Soort.ELLIPSIS_OPEN);
        d.put(")", Token.Soort.ELLIPSIS_CLOSE);
        d.put(">=", Token.Soort.GREATER_EQUALS);
        d.put("<=", Token.Soort.SMALLER_EQUALS);
        d.put("<", Token.Soort.SMALLER);
        d.put(">", Token.Soort.GREATER);
        d.put("{", Token.Soort.BRACKETS_OPEN);
        d.put("}", Token.Soort.BRACKETS_CLOSE);
        d.put("-", Token.Soort.MINUS);
        d.put("+", Token.Soort.PLUS);
        d.put("*", Token.Soort.MULTIPLY);
        d.put("/", Token.Soort.DIVIDE);
        d.put("=", Token.Soort.ASSIGN);
    }

    public void parse() {
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        Scanner inFile1;
        String regel = "";

        try {
            Scanner scanner = new Scanner(new File("file.txt"));
            inFile1 = scanner.useDelimiter("\\n");

            List<Token> temps = new ArrayList<Token>();
            int regelnummer = 1;
            int posInLijst = 1;
            int level = 1;

            while (inFile1.hasNext()) {
                //parse line
                regel = inFile1.next();
                regel = regel.trim();
                if (regel.isEmpty()){
                    continue;
                }

                String[] values = regel.split("\\s");

                for (int i = 0; i < values.length; i++) {
                if (regel.isEmpty()){
                    break;
                }
                    //veranderen naar regEx
                    Token.Soort soort = vindSoort(values[i]);
                    if (values[i].equals("(") || values[i].equals("{") || values[i].equals("[") || values[i].equals("<")) {
                        level++;
                    } else if (values[i].equals(")") || values[i].equals("}")|| values[i].equals("]") || values[i].equals(">")) {
                        level--;
                    }
                    Token t = new Token(posInLijst++, regelnummer, 1, soort, level, 0, values[i]);
                    temps.add(t);
                }

                regelnummer++;
            }
            inFile1.close();

            for (Token t : temps) {
                System.out.println(t.getType() + ": " + t.getValue());
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tokenizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Token.Soort vindSoort(String woord){
        if (d.containsKey(woord)){
            return d.get(woord);
        }
        else {
            if (woord.matches("^-?\\d+$")){
                return Token.Soort.NUMBER;
            }
            else {
                return Token.Soort.IDENTIFIER;
            }
        }
    }
}
