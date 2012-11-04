/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

/**
 *
 * @author XeeD
 */
public class DefiniceFunkce implements IFunkce {
    
    final private PosloupnostPrikazu posloupnostPříkazů;
    final private Identifikator identifikátor;
    
    public DefiniceFunkce(Identifikator identifikátor, PosloupnostPrikazu posloupnostPříkazů) {
        this.identifikátor = identifikátor;
        this.posloupnostPříkazů = posloupnostPříkazů;
        DefinovaneFunkce.přidejDefinici(identifikátor, this);
    }

    @Override
    public boolean jePravda() throws Skok {
        try {
            posloupnostPříkazů.proveď();
        } catch (SkokReturnTrue skok) {
            return true;
        } catch (SkokReturnFalse skok) {
            return false;
        }
        throw new RuntimeException("Funkce musí vracet hodnotu");
    }
    
}
