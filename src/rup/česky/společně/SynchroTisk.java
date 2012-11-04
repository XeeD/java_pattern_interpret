package rup.česky.společně;
//import rup.česky.vzory._00_společné.SynchroTisk;


/*******************************************************************************
 * Třída SynchroTisk slouží k tisku na standardní výstup s explicitním počítáním tisků.
 *
 * @author    Rudolf Pecinovský
 * @version   0.00.000,  0.0.2003
 */
public class SynchroTisk
{
//  static { System.out.println("CLASS - newRupClass - START"); }
//  { System.out.println("INSTANCE - newRupClass - START"); }
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================

    /** Počet dosud odeslaných tisků */
    static int řádek = 0;

    /** Počet zarovnávacích mezer před číslem tisku --
     *  počítáme s max. 999 výstupními tisky, pak se počítá znovu. */
    static String[] mezer = { "  ", " ", "" };



//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

    /***************************************************************************
     * Tiskne zadaný řetězec uvozený pořadovým číslem daného tisku
     */
    public static synchronized void tiskni( String s )
    {
        String číslo = String.valueOf( ++řádek % 1000 );
        int    cifer = číslo.length()-1;
        System.out.println( mezer[cifer] + číslo + ": " + s );
    }


    /***************************************************************************
     * Tiskne zadaný objekt uvozený pořadovým číslem daného tisku
     */
    public static synchronized void tiskni( Object o )
    {
        tiskni( o.toString() );
    }


//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Soukromý konstruktor zabraňující vytvoření instance.
     */
    private SynchroTisk() {}



//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY =====================================================================
}
