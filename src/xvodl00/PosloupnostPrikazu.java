package xvodl00;

import robot.cz.Robot;

/**
 * Instance třídy <b><code>Program</code></b> předtavují zřetězené příkazy,
 * které je možno předávat dalším příkazům. Můžeme tak například definovat
 * například část sekvenci příkazů, které jsou předány smyčce While.
 * 
 * PosloupnostPrikazu se liší od DefiniceProcedury tím, že nemá jméno (nelze
 * je tedy volat) a není možné ji přerušit pomocí příkazu <code>Return</code>.
 * 
 * PosloupnostPrikazu však může příkazy <code>Return</code> obsahovat,
 * pokud je použita v definici procedury, která tyto příkazy umí "odchytit".
 *
 * @author Lukáš Voda (xvodl00)
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
