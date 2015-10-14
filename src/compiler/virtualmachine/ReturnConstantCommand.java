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
public class ReturnConstantCommand extends BaseCommand {

    public ReturnConstantCommand() {
    }

    @Override
    public void execute(VirtualMachine vm, List<String> parameters) {
        vm.setReturnValue(parameters.get(1));
//        Variable variable1 = vm.getVariable(parameters.get(0));
//        
//        vm.setReturnValue(variable1.getValue());
    }
    
}
