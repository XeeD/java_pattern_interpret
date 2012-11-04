package rup.česky.vzor_interpret.logický;

/*******************************************************************************
 * Instance třídy Negace přestavují ...
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class Negace implements LogickýVýraz
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    private final LogickýVýraz výraz;


//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří výraz, který je negací zadaného výrazu.
     *
     * @param výraz Negovaný výraz
     */
    public Negace( LogickýVýraz výraz )
    {
        this.výraz = výraz;
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public LogickýVýraz kopíruj()
    {
        return new Negace( výraz );
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public LogickýVýraz nahraď( String proměnná, LogickýVýraz výraz )
    {
        return new Negace( nahraď( proměnná, výraz ) );
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public boolean vyhodnoť( Kontext ctx )
    {
        return ! výraz.vyhodnoť( ctx );
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
