package rup.česky.vzor_interpret.logický;

/*******************************************************************************
 * Instance třídy Proměnná přestavují ...
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class Proměnná implements LogickýVýraz
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

    private final String název;


//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří novou proměnnou se zadaným názvem a počáteční hodnototou.
     *
     * @param název   Název vytvářené proměnné
     */
    public Proměnná( String název )
    {
        this.název  = název;
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public boolean vyhodnoť( Kontext ctx )
    {
        return ctx.hodnota( název );
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public LogickýVýraz nahraď( String názevProměnné, LogickýVýraz výraz )
    {
        if( názevProměnné.equals( název ) )
            //Je to nahrazovná proměnná => nahradí se
            return výraz.kopíruj();
        else
            //Není to nahrazovaná proměnná => bude dále vystupovat ve výrazu
            return new Proměnná( název );
    }


    /***************************************************************************
     * Vrátí název dané proměnné.
     *
     * @return Název dané proměnné
     */
    public String getNázev()
    {
        return název;
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public LogickýVýraz kopíruj()
    {
        return new Proměnná( název );
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
