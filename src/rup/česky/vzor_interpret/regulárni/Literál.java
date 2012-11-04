package rup.česky.vzor_interpret.regulárni;


/*******************************************************************************
 * Instance třídy <code>Literál</code> testují, jestli vstupní text
 * nepokračuje jejich řetězcem.
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  0.0.2007
 */
public class Literál implements IRegVýraz
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    private final String jméno;


//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

    private int délka;


//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří objekt testující přítomnost zadaného řetězce
     * na aktuální pozici anaylzovaného textu.
     *
     * @param jméno Porovnávaný (vyhledávaný) řetězec
     */
    public Literál( String jméno )
    {
        this.jméno = jméno;
        this.délka = jméno.length();
    }


//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ  METODY INSTANCÍ ===============================================

    /***************************************************************************
     * Vyhodnotí, zda následující text za aktuální pozicí
     * v parametru <code>zdroj</code> není zadaným literálem.
     * Je-li, přesune aktuální pozici za něj.
     *
     * @param zdroj Přepravka s analyzovaným textem a indexem počátečního znaku
     * @return <code>true</code> pokud následující text odpovídá požadavkům
     */
    @SuppressWarnings("empty-statement")
    public boolean vyhodnoť( Zdroj zdroj ) {
        int start=zdroj.pozice-1;
        String text = zdroj.text;
        while( (++start < text.length())  &&
               (Character.isWhitespace( text.charAt(start) )) );
        if( start < text.length() ) {
            int i=start, j=0;   //Potřebujeme se ptát na j po proběhnutí cyklu
            for( ;
                 (j < délka)  &&  (zdroj.text.charAt(i) == jméno.charAt(j));
                 i++, j++ );
            if( j == délka ) {
                zdroj.pozice = start + j;
                return true;
            }
        }
        zdroj.pozice = start;
        return false;
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY =====================================================================
}
