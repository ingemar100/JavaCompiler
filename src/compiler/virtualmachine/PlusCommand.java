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
public class PlusCommand extends BaseCommand {

    public void execute(VirtualMachine vm, List<String> parameterNames) {
        Variable variable1 = vm.getVariable(parameterNames.get(1));
        Variable variable2 = vm.getVariable(parameterNames.get(2));

        if (variable1.getType() == Variable.Type.NUMBER && variable2.getType() == Variable.Type.NUMBER) {
            String a = variable1.getValue();
            String b = variable2.getValue();
            int ernationaal = Integer.parseInt(a);
            int eger =  + Integer.parseInt(b);
            int oTheWild = ernationaal + eger;
            vm.setReturnValue(Integer.toString(oTheWild));
        } else {
            vm.setReturnValue(variable1.getValue() + variable2.getValue());
        }
    }
}
