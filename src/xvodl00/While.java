/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

/**
 *
 * @author XeeD
 */
public class While implements IPrikaz {
    
    private IFunkce funkce;
    private PosloupnostPrikazu posloupnostPříkazů;
    
    public While(IFunkce funkce, PosloupnostPrikazu posloupnostPříkazů) {
        this.funkce = funkce;
        this.posloupnostPříkazů = posloupnostPříkazů;
    }

    @Override
    public void proveď() throws Skok {
        while(funkce.jePravda()) {
            try {
                posloupnostPříkazů.proveď();
            } catch(SkokBreak skok) {
                return;
            }
        }
    }
    
}
