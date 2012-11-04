package xvodl00;

import robot.cz.Robot;
import rup.česky.společně.IO;

/**
 * *****************************************************************************
 * Instance třídy <b><code>Program</code></b> předtavují ...
 *
 * @author jméno autora
 * @version 0.00.000, 0.0.2008
 */
public class PosloupnostPrikazu implements IPrikaz {
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    private final IPrikaz[] posloupnostPříkazů;

//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY ATRIBUTŮ TŘÍDY ==========================================
//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY ===========================================
//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================
    /**
     * *************************************************************************
     *
     */
    
    public PosloupnostPrikazu(IPrikaz... příkaz) {
        posloupnostPříkazů = příkaz;
    }

//== ABSTRAKTNÍ METODY =========================================================
//== PŘÍSTUPOVÉ METODY INSTANCÍ ================================================
//== OSTATNÍ NESOUKROMÉ  METODY INSTANCÍ =======================================
    /**
     * *************************************************************************
     *
     */
    @Override
    public void proveď() throws Skok {
        for (IPrikaz příkaz : posloupnostPříkazů) {
            příkaz.proveď();
        }
    }

//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTOVACÍ METODY A TŘÍDY ==================================================
    /**
     * *************************************************************************
     * Testovací metoda.
     */
    public static void test() {
        PosloupnostPrikazu pgm = new PosloupnostPrikazu(new IPrikaz[]{
                    AtomickaProcedura.krok,
                    AtomickaProcedura.vlevoVbok,
                    AtomickaProcedura.krok,});
        IO.zpráva("Připraveno");
        try {
            pgm.proveď();
        } catch (Skok skok) {
            throw new RuntimeException("Nezachycený skok");
        }
    }

    /**
     * @param args Parametry příkazového řádku - nepoužité.
     */
    public static void main(String... args) {
        test();
    }
}
