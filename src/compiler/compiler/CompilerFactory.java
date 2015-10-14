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

    private ArrayList<CompiledStatement> _compilers;

    private CompilerFactory() {
        _compilers = new ArrayList<>();
        _compilers.add(new CompiledAssignment());
        _compilers.add(new CompiledCondition());
        _compilers.add(new CompiledConstant());
        _compilers.add(new CompiledIf());
        _compilers.add(new CompiledRValue());
        _compilers.add(new CompiledWhile());
        _compilers.add(new CompiledFunction());
        _compilers.add(new CompiledIfElse());
        _compilers.add(new CompiledVariable());
    }

    public CompiledStatement createCompiledStatement(LLNode<Token> currentToken) throws Exception {
        for (CompiledStatement compiledStatement : _compilers) {
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
