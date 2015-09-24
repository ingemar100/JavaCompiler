/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ingemar
 */
public class Tokenizer {

    public Tokenizer() {
        parse();
    }

    public void parse() {
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        Scanner inFile1;
        String regel = "";

        try {
            Scanner scanner = new Scanner(new File("file.txt"));
            inFile1 = scanner.useDelimiter("\\n");

            List<String> temps = new ArrayList<String>();
            int regelnummer = 1;
            int posInLijst = 1;
            int level = 1;

            while (inFile1.hasNext()) {
                //parse line
                regel = inFile1.next();
                regel = regel.trim();

                String[] values = regel.split("\\s");

                for (int i = 0; i < values.length; i++) {
                    //veranderen naar regEx
                    if (values[i].equals("(") || values[i].equals("{") || values[i].equals("[") || values[i].equals("<")) {
                        level++;
                    } else if (values[i].equals(")") || values[i].equals("}")|| values[i].equals("]") || values[i].equals(">")) {
                        level--;
                    }
                    Token t = new Token(posInLijst++, regelnummer, 1, 0, level, 0);
                    temps.add(values[i]);
                }

                regelnummer++;
            }
            inFile1.close();

            String[] tempsArray = temps.toArray(new String[0]);

            for (String s : tempsArray) {
                System.out.println(s);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tokenizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
