/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

/**
 *
 * @author XeeD
 */
public class ReturnHodnotu implements IPrikaz {
    
    final private boolean hodnota;
    
    public ReturnHodnotu(boolean hodnota) {
        this.hodnota = hodnota;
    }

    @Override
    public void proveď() throws Skok {
        if (hodnota) {
            throw new SkokReturnTrue();
        } else {
            throw new SkokReturnFalse();
        }
    }
    
}
