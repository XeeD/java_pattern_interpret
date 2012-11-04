package rup.česky.vzor_interpret.kalkulačka;

/*******************************************************************************
 * Instance třídy Konstanta přestavují konstanty vystupující
 * v aritmetických výrazech.
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class Konstanta implements IAritmVýraz
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    private final double hodnota;   //Hodnota dané konstanty


//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================
//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří novou konstatnu se zadanou hodnotou.
     *
     * @param hodnota Hodnota vytvářené konstanty
     */
    public Konstanta( double hodnota ) {
        this.hodnota = hodnota;
    }


//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================

    /***************************************************************************
     * {@inheritDoc}
     */
    public IAritmVýraz kopie()
    {
        return new Konstanta( hodnota );
    }


    /***************************************************************************
     * Protože v konstantě nelze nahradit proměnnou výrazem,
     * vrátí po "aplikované substituci" kopii sebe sama.
     */
    public IAritmVýraz nahraď( String proměnná, IAritmVýraz výraz )
    {
        return new Konstanta( hodnota );
    }


    /***************************************************************************
     * Nezávisle na kontextu vrací konstanta vždy svoji hodnotu.
     */
    public double vyhodnoť( Kontext ctx )
    {
        return hodnota;
    }


    /***************************************************************************
     * Vrátí řetězcovou podobu hodnoty výrazu.
     */
    public String toString()
    {
        return String.valueOf( hodnota );
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
