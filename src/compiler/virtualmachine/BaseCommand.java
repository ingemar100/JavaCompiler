/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.virtualmachine;

import java.util.List;

/**
 *
 * @author Ingemar
 */

public abstract class BaseCommand {
    public abstract void execute(VirtualMachine vm, List<String> parameters) throws Exception;
}