/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.nodes;

import java.util.List;

/**
 *
 * @author Ingemar
 */
public abstract class AbstractFunctionCall extends Action{
    
    public abstract List<String> getParameters();
}
