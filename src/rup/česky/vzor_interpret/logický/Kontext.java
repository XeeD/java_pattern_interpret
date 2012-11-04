package rup.česky.vzor_interpret.logický;

import java.util.HashMap;
import java.util.Map;

/*******************************************************************************
 * Instance třídy Kontext přestavují ...
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class Kontext
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    private final Map<String, Boolean> hodnoty = new HashMap<String, Boolean>();


//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================
//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================
//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================

    /***************************************************************************
     * Vrátí hodnotu zadané proměnné
     *
     * @param název Název proměnné, jejíž hodnotu zjišťujeme
     * @return      Hodnota zadané proměnné
     */
    public boolean hodnota( String název )
    {
        Boolean hodnota = hodnoty.get( název );
        if( hodnota == null )
            throw new IllegalStateException(
                "\nProměnná " + název + " neexistuje");
        return hodnota.booleanValue();
    }


    /***************************************************************************
     * Vrátí hodnotu zadané proměnné
     *
     * @param proměnná Proměnná, jejíž hodnotu zjišťujeme
     * @return Hodnota zadané proměnné
     */
    public boolean hodnota( Proměnná proměnná )
    {
        return hodnota( proměnná.getNázev() );
    }


    /***************************************************************************
     * Přiřadí proměnné zadané svým názvem zadanou logickou hodnotu.
     *
     * @param název    Název proměnné, které přiřazujeme hodnotu
     * @param hodnota  Přiřazovaná hondota
     */
    public void přiřaď( String název, boolean hodnota )
    {
        hodnoty.put( název, hodnota );
    }


    /***************************************************************************
     * Přiřadí zadané proměnné zadanou logickou hodnotu.
     *
     * @param proměnná Proměnná, které přiřazujeme hodnotu
     * @param hodnota  Přiřazovaná hondota
     */
    public void přiřaď( Proměnná proměnná, boolean hodnota )
    {
        hodnoty.put( proměnná.getNázev(), hodnota );
    }


    /***************************************************************************
     * Všem proměnným, jejichž jednoznakové názvy byly zadány v parametru,
     * přiřadí hodnotu <code>false</code>.
     *
     * @param proměnné Názvy proměnných, jimž se přiřazuje <code>false</code>
     */
    public void přiřaďFalse( String proměnné )
    {
        for( int i=0;   i < proměnné.length();   i++ )
            hodnoty.put( Character.toString( proměnné.charAt( i ) ), false );
    }


    /***************************************************************************
     * Všem proměnným, jejichž jednoznakové názvy byly zadány v parametru,
     * přiřadí hodnotu <code>true</code>.
     *
     * @param proměnné Názvy proměnných, jimž se přiřazuje <code>true</code>
     */
    public void přiřaďTrue( String proměnné )
    {
        for( int i=0;   i < proměnné.length();   i++ )
            hodnoty.put( Character.toString( proměnné.charAt( i ) ), true );
    }


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
