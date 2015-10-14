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
public class PrintCommand extends BaseCommand {

    @Override
    public void execute(VirtualMachine vm, List<String> parameters) {
        Variable variable1 = vm.getVariable(parameters.get(1));
        System.out.println(variable1.getValue());
    }
    
}
