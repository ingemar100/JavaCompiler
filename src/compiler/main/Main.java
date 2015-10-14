package compiler.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import compiler.compiler.CompiledBlock;
import compiler.compiler.CompiledStatement;
import compiler.compiler.CompilerFactory;
import compiler.nodes.Action;
import compiler.tokenizer.HarrisonFordException;
import compiler.tokenizer.Token;
import compiler.tokenizer.Tokenizer;
import compiler.util.AbrahamLinkedList;
import compiler.util.LLNode;
import java.util.LinkedList;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            Tokenizer t = new Tokenizer();
            AbrahamLinkedList<Token> tokens = t.getTokens();

            tokens.printAllNodes();

            compile(tokens);

            //bepaal welke actie ondernomen moet worden
            //bepaal de grens in e token list tot waar de actie gedefinieerd is
            //voer de actie uit
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO:
        //
        //Taaldefinitie
        //
        //    Maak een taaldefinitie.
        //    Geef aan wat je taal dient te kunnen. Gebruik daar eventueel voorbeelden bij.Sheet 8 kan je hierbij helpen. Je hoeft geen gebruik te maken van BNF.
        //    Let wel: je definitie gebruik je ook in week 4 en week 6. 
        //
        //Tokenizer
        //
        //    Maak een werkende tokenizer voor je taal.
        //    Let op: foutmeldingen horen er ook bij. 
        //
        //Tokenizer / Compiler / virtual machine
        //
        //    Maak, zo nodig, de gekoppelde lijst die je token list en je programma gaat representeren.
        //    Let wel: 
        //            Dit zijn twee gekoppelde lijsten:  de ene heb je nodig voor de tokenizer en de andere voor de compiler en virtual machine. Je kunt denken om een generic klasse te maken.
        //            Je mag zo nodig de broncode van algoritmiek 1 gebruiken. Je moet de code wel kunnen uitleggen!
        //            Als je taal zo'n gekoppelde lijst heeft, mag je die ook gebruiken. Je moet 'm dan wel gebruiken.
        //            De gekoppelde lijst moet een methode bezitten insertBefore( content, before ), die een nieuwe node maakt met meegegeven inhoud (Content) en deze voor een aangegeven node (before) plaatst. De nodes van de klasse moeten daarom op verzoek bereikbaar zijn.
        //            Maak alvast een eerste opzet van de klassen van sheet 25. Het gaat hier om de klassen DoNothing, Jump, … 
        //
//         if: если
//         else: иначе
//        фореач
//         шхиле
//        фор
//        принт
//         фунцтион
//         ретурн
//         проц старт()
//         вывод: "Привет мир!";
//         кон
    }

    private static void compile(AbrahamLinkedList<Token> tokens) throws Exception {

        CompilerFactory factory = CompilerFactory.getInstance();
        CompiledBlock block = new CompiledBlock();
        block.compile(tokens.getFirst());

        block.getCompiled().printAllNodes();
    }

}
