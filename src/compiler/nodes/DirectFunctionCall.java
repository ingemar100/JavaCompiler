/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.nodes;

/**
 *
 * @author Ingemar
 */
public class DirectFunctionCall extends AbstractFunctionCall{
    private String actie;
    private String waarde;

    public DirectFunctionCall(String actie, String waarde) {
        this.actie = actie;
        this.waarde = waarde;
    }
    
    public String toString(){
        return "DirectFunctionCall - actie: " + actie + ", waarde: " + waarde; 
    }
}
