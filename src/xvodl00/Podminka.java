/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

/**
 *
 * @author XeeD
 */
public class Podminka implements IPrikaz {

    private IFunkce funkce;
    private PosloupnostPrikazu posloupnostPříkazůPravda;
    private PosloupnostPrikazu posloupnostPříkazůNepravda;

    public Podminka(IFunkce funkce, PosloupnostPrikazu posloupnostPříkazů) {
        this.funkce = funkce;
        this.posloupnostPříkazůPravda = posloupnostPříkazů;
    }

    public Podminka(IFunkce funkce, PosloupnostPrikazu posloupnostPříkazůPravda,
            PosloupnostPrikazu posloupnostPříkazůNepravda) {
        this.funkce = funkce;
        this.posloupnostPříkazůPravda = posloupnostPříkazůPravda;
        this.posloupnostPříkazůNepravda = posloupnostPříkazůNepravda;
    }

    @Override
    public void proveď() throws Skok {
        if (funkce.jePravda()) {
            posloupnostPříkazůPravda.proveď();
        } else if (posloupnostPříkazůNepravda != null) {
            posloupnostPříkazůNepravda.proveď();
        }
    }
}
