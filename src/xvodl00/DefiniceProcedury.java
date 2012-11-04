/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

/**
 *
 * @author XeeD
 */
public class DefiniceProcedury implements IProcedura {
    
    final private Identifikator identifikátor;
    final private PosloupnostPrikazu posloupnostPříkazů;
    
    public DefiniceProcedury(Identifikator identifikátor, PosloupnostPrikazu posloupnostPříkazů) {
        this.identifikátor = identifikátor;
        this.posloupnostPříkazů = posloupnostPříkazů;
    }

    @Override
    public void proveď() throws Skok {
        try {
            posloupnostPříkazů.proveď();
        } catch (SkokReturn skok) {
        }
    }
    
}
