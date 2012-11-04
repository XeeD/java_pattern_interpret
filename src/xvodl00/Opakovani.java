package xvodl00;

/**
 * *****************************************************************************
 * Instance třídy <b><code>Opakování</code></b> předtavují implementaci
 * smyčky <code>for</code>, která je definovaná v gramatice jazyka.
 *
 * @author Lukáš Voda (xvodl00)
 */
public class Opakovani implements IPrikaz {
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    private int počet;
    private PosloupnostPrikazu posloupnostPříkazů;
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY ATRIBUTŮ TŘÍDY ==========================================
//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================
    /**
     * *************************************************************************
     *
     */
    public Opakovani(int počet, PosloupnostPrikazu pp) {
        this.počet = počet;
        posloupnostPříkazů = pp;
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
        for (int i = 0; i < počet; i++) {
            try {
                posloupnostPříkazů.proveď();
            } catch (SkokBreak skok) {
                break;
            }
        }
    }
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTOVACÍ METODY A TŘÍDY ==================================================
//
//    /***************************************************************************
//     * Testovací metoda.
//     */
//    public static void test()
//    {
//         Opakování instance = new Opakování();
//    }
//    /** @param args Parametry příkazového řádku - nepoužité. */
//    public static void main( String... args ) { test(); }
}
