/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.nodes;

import compiler.virtualmachine.NextNodeVisitor;

/**
 *
 * @author Ingemar
 */
public abstract class Action {
    public abstract void accept(NextNodeVisitor visitor);
}
