package rup.česky.společně;

/*******************************************************************************
 * Instance třídy <code>UnexpectedException</code> představují ...
 *
 * @author    jméno autora
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  0.0.2007
 */
public class UnexpectedException extends RuntimeException
{
//    static { System.out.println("CLASS - UnexpectedException - START"); }
//    { System.out.println("INSTANCE - UnexpectedException - START"); }
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     *
     */
    public UnexpectedException( Throwable t )
    {
        super( "\nNeočekávaná výjimka " + t, t );
    }


    /***************************************************************************
     *
     */
    public UnexpectedException( String zpráva, Throwable t )
    {
        super( "\nNeočekávaná výjimka " + t + "\n" + zpráva, t );
    }

//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY =====================================================================
//
//    /***************************************************************************
//     * Testovací metoda.
//     */
//    public static void test()
//    {
//        UnexpectedException x = new UnexpectedException();
//    }
//    /** @param args Parametry příkazového řádku - nepoužívané. */
//    public static void main( String[] args )  {  test();  }
}
