package rup.česky.vzor_interpret.kalkulačka_2;

import java.util.Map;

 /*******************************************************************************
 * Třída <code>TestPřekladače</code> slouží jako testovací třída pro ověření
 * funkce třídy <code>Překladač</code> a tříd s ní spolupracujících.
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class TestPřekladače
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    private TestPřekladače() {}

//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Vyhodnotí zadaný kód,
     * do nějž za proměnné dosadí hodnoty ze zadaného kontextu.
     * Výsledek vypíše na standardní výstup.
     */
    private static void zpracuj( String výraz, Kontext kontext )
    {
        zpracuj( Překladač.přeložVýraz( výraz ), kontext );
    }


    /***************************************************************************
     * Vyhodnotí zadaný aritmetický výraz,
     * do nějž za proměnné dosadí hodnoty ze zadaného kontextu.
     * Výsledek vypíše na standardní výstup.
     */
    private static void zpracuj( IAritmVýraz výraz, Kontext kontext )
    {
        double výsledek   = výraz.vyhodnoť( kontext );
        StringBuilder sb = new StringBuilder();
        sb.append("Výsledkem výrazu ").append(výraz).append(" pro:");
        for( Map.Entry<String,Double> mesd : kontext.getProměnné() ) {
            sb.append( "\n  " )
              .append( mesd.getKey() )
              .append( " = " )
              .append( mesd.getValue() );
        }
        sb.append( "\nje hodnota " ).append( výsledek ).append( "\n\n" );
        System.out.println( sb );
    }


//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovací metoda.
     */
    public static void test()
    {
        Kontext ctx = new Kontext();
        String zdroj;

        ctx.přiřaď( 3, "X" );
        ctx.přiřaď( 4, "Y" );

        zpracuj( "(#3# * #5#) + #2# / (#37# - #18#) * #2#", ctx );

        zpracuj( "((X + Y) * (X - Y))", ctx );
        zpracuj( "X * X  +  Y * Y", ctx );
        zpracuj( "#3.14# * X * Y", ctx );
        zpracuj( "-#5# * -Deset -Padesát",
                 new Kontext( "Deset=10, Padesát=50") );

        IAritmVýraz výraz = Překladač.přeložVýraz( "(x * y) / (x + y)" );
        IAritmVýraz subst = Překladač.přeložVýraz( "a + b" );
        IAritmVýraz nový  = výraz.nahraď( "x", subst );
        Kontext kontext = new Kontext( "y=2", "a=7, b=3" );
        zpracuj( nový, kontext );
    }
    /** @param ppr Parametry příkazového řádku - nepoužité */
    public static void main(String[]ppr){ test(); }/*-*/
}
