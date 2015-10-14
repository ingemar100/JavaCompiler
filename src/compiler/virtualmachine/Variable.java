/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.virtualmachine;

/**
 *
 * @author Ingemar
 */
public class Variable {

    Variable(String returnValue, Type type) {
        this.type = type;
        this.value = returnValue;
    }
    public static enum Type { NUMBER, STRING };
    private Type type;
    private String value;

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
    
    
}
