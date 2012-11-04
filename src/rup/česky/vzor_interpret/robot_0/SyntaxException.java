package rup.česky.vzor_interpret.robot_0;



/*******************************************************************************
 * Instance třídy <b><code>SyntaxException</code></b> předtavují ...
 *
 * @author    jméno autora
 * @version   0.00.000,  0.0.2008
 */
public class SyntaxException extends RuntimeException
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY ATRIBUTŮ TŘÍDY ==========================================
//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     *
     */
    public SyntaxException( String zpráva )
    {
         super( zpráva );
    }


    /***************************************************************************
     *
     */
    public SyntaxException( String zpráva, Throwable výjimka )
    {
         super( zpráva, výjimka );
    }


//== ABSTRAKTNÍ METODY =========================================================
//== PŘÍSTUPOVÉ METODY INSTANCÍ ================================================
//== OSTATNÍ NESOUKROMÉ  METODY INSTANCÍ =======================================
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
//         SyntaxException instance = new SyntaxException();
//    }
//    /** @param args Parametry příkazového řádku - nepoužité. */
//    public static void main( String... args ) { test(); }
}
