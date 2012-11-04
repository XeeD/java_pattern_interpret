/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

/**
 *
 * @author XeeD
 */
public class Break implements IPrikaz {

    @Override
    public void proveď() throws SkokBreak {
        throw new SkokBreak();
    }
    
}
