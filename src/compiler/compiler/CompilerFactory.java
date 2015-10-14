/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.compiler;

import compiler.tokenizer.Token;
import compiler.util.LLNode;
import java.util.ArrayList;

/**
 *
 * @author Ingemar
 */
public class CompilerFactory {

    private static CompilerFactory instance;

    private ArrayList<CompiledStatement> compilers;

    private CompilerFactory() {
        compilers = new ArrayList<>();
        compilers.add(new CompiledAssignment());
        compilers.add(new CompiledCondition());
        compilers.add(new CompiledConstant());
        compilers.add(new CompiledIf());
        compilers.add(new CompiledRValue());
        compilers.add(new CompiledWhile());
        compilers.add(new CompiledFunction());
        compilers.add(new CompiledIfElse());
        compilers.add(new CompiledVariable());
    }

    public CompiledStatement createCompiledStatement(LLNode<Token> currentToken) throws Exception {
        for (CompiledStatement compiledStatement : compilers) {
            if (compiledStatement.isMatch(currentToken)) {
                return compiledStatement.clone();
            }
        }

        throw new CompilerException("\nUnknown token: " + currentToken.getValue().getValue(), currentToken.getValue());
    }

    public static CompilerFactory getInstance() {
        if (instance == null) {
            instance = new CompilerFactory();
        }
        return instance;
    }
}
