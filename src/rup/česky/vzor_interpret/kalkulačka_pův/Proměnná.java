package rup.česky.vzor_interpret.kalkulačka_pův;

/*******************************************************************************
 * Instance třídy Proměnná přestavují proměnné
 * vystupující v aritmetických výrazech.
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class Proměnná implements IAritmVýraz
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

    private final String název; //Název proměnné


//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří novou proměnnou se zadaným názvem.
     *
     * @param název   Název vytvářené proměnné
     */
    public Proměnná( String název )
    {
        this.název  = název;
    }


//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================

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
    public IAritmVýraz kopie()
    {
        return new Proměnná( název );
    }


    /***************************************************************************
     * {@inheritDoc}
     * Má-li se nahradit daná proměnná, vrátí nahrazující výraz,
     * má-li se nahradit jiná proměnná, na výrazu (proměnné) se náhradou
     * nic nezmění, a proto vrátí sebe sama.
     */
    public IAritmVýraz nahraď( String názevProměnné, IAritmVýraz výraz )
    {
        if( názevProměnné.equals( název ) )
            //Je to nahrazovná proměnná => nahradí se
            return výraz.kopie();
        else
            //Není to nahrazovaná proměnná => bude dále vystupovat ve výrazu
            return new Proměnná( název );
    }


    /***************************************************************************
     * Vrátí hodnotu, uloženou pro danou porměnnou v kontextu.
     */
    public double vyhodnoť( Kontext ctx )
    {
        return ctx.hodnota( název );
    }


    /***************************************************************************
     * Vrátí název dané proměnné.
     */
    public String toString()
    {
        return název;
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
