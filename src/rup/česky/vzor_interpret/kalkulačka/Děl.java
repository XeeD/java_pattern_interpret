package rup.česky.vzor_interpret.kalkulačka;

/*******************************************************************************
 * Instance třídy <code>Děl</code> přestavují podíl dvou výrazů.
 *
 * @author Rudolf PECINOVSKÝ
 * @version 0.00.000,  4.10.2007
 */

public class Děl extends Binární
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří nový výraz představující podíl zadaných výrazů.
     *
     * @param levý  Levý operand
     * @param pravý Pravý operand
     */
    public Děl( IAritmVýraz levý, IAritmVýraz pravý )
    {
        super( '/', levý, pravý );
    }



//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================

    /***************************************************************************
     * {@inheritDoc}
     */
    protected double vyhodnoť( double levý, double pravý )
    {
        return levý  /  pravý;
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
