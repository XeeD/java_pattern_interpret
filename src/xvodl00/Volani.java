/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

/**
 *
 * @author XeeD
 */
public class Volani implements IPrikaz {
    
    private IProcedura procedura;
    
    public Volani(IProcedura procedura) {
        this.procedura = procedura;
    }

    @Override
    public void proveď() throws Skok {
        procedura.proveď();
    }
    
}
