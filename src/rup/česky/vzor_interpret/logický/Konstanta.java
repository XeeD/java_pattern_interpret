package rup.česky.vzor_interpret.logický;

/*******************************************************************************
 * Instance třídy Konstanta přestavují ...
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class Konstanta implements LogickýVýraz
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    private final boolean hodnota;
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří novou konstatnu se zadanou hodnotou.
     *
     * @param hodnota Hodnota vytvářené konstanty
     */
    public Konstanta( boolean hodnota )
    {
        this.hodnota = hodnota;
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public LogickýVýraz kopíruj()
    {
        return new Konstanta( hodnota );
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public LogickýVýraz nahraď( String proměnná, LogickýVýraz výraz )
    {
        return new Konstanta( hodnota );
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public boolean vyhodnoť( Kontext ctx )
    {
        return hodnota;
    }

//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
//
//    /***************************************************************************
//     * Testovací metoda.
//     * /
//    public static void test()
//    {
//    }
//    /** @param ppr Parametry příkazového řádku - nepoužité * /
//    public static void main(String[]ppr){ test(); }/*-*/
}
