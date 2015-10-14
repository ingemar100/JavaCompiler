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
class MultiplyCommand extends BaseCommand {

    public MultiplyCommand() {
    }

    @Override
    public void execute(VirtualMachine vm, List<String> parameters) {
     Variable variable1 = vm.getVariable(parameters.get(1));
     Variable variable2 = vm.getVariable(parameters.get(2));

     if(variable1.getType() == Variable.Type.NUMBER && variable2.getType() == Variable.Type.NUMBER)
        vm.setReturnValue(Integer.toString(Integer.parseInt(variable1.getValue()) * Integer.parseInt(variable2.getValue())));
     else
	vm.setReturnValue(variable1.getValue() + variable2.getValue());
    }
    
}
