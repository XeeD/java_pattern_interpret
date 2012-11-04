package rup.česky.vzor_interpret.logický;

/*******************************************************************************
 * Instance třídy Testy přestavují ...
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class Testy
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * X
     */
    private Testy()
    {
    }

//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Test příkladu z GoF.
     */
    public static void testGoF()
    {
        Proměnná x = new Proměnná( "x" );
        Proměnná y = new Proměnná( "y" );

        LogickýVýraz výraz = new Alternativa(
            new Konjunkce( new Konstanta( true ), x ),
            new Konjunkce( y, new Negace( x ) )
        );

        Kontext ctx = new Kontext();
        ctx.přiřaď( x, false );
        ctx.přiřaď( y, false );

        boolean výsledek = výraz.vyhodnoť( ctx );
        System.out.println(
            "Výsledkem výrazu (true & x) | (y & !x) pro:" +
            "\n   x = " + ctx.hodnota( x ) +
            "\n   y = " + ctx.hodnota( y ) +
            "\nje hodnota " + výsledek );
    }
    /** @param ppr Parametry příkazového řádku - nepoužité */
    public static void main(String[]ppr){ testGoF(); }/*-*/
}
