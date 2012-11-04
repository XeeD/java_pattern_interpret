/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

/**
 *
 * @author XeeD
 */
public class Return implements IPrikaz {

    @Override
    public void proveď() throws SkokReturn {
        throw new SkokReturn();
    }
    
}
