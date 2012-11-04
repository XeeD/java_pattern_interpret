package rup.česky.vzor_interpret.logický;

/*******************************************************************************
 * Instance třídy Alternativa přestavují alternativu dvou výrazů (A nebo B).
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class Alternativa implements LogickýVýraz
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

    private LogickýVýraz levý;
    private LogickýVýraz pravý;


//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří nový výraz představující alternativu zadaných výrazů (A nebo B).
     *
     * @param levý  Levý operand
     * @param pravý Pravý operand
     */
    public Alternativa( LogickýVýraz levý, LogickýVýraz pravý )
    {
        this.levý  = levý;
        this.pravý = pravý;
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public LogickýVýraz kopíruj()
    {
        return new Alternativa( levý, pravý );
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public LogickýVýraz nahraď( String proměnná, LogickýVýraz výraz )
    {
        return new Alternativa( levý .nahraď( proměnná, výraz ),
                                pravý.nahraď( proměnná, výraz ) );
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public boolean vyhodnoť( Kontext ctx )
    {
        //Předpokládáme, že ve výrazech nemohou nastat vedlejší efekty
        return levý.vyhodnoť( ctx )  ||  pravý.vyhodnoť( ctx );
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
