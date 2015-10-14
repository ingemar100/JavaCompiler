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
class AreEqualCommand extends BaseCommand {

    public AreEqualCommand() {
    }

    @Override
    public void execute(VirtualMachine vm, List<String> parameters) {
        Variable variable1 = vm.getVariable(parameters.get(1));
        Variable variable2 = vm.getVariable(parameters.get(2));

        vm.setReturnValue(variable1.getValue().equals(variable2.getValue()) + "");
    }

}
