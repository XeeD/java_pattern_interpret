package rup.česky.vzor_interpret.regulárni;


/*******************************************************************************
 * Instance třídy <code>Posloupnost</code> dokáží vyhodnotit,
 * zda v předloženém textu následuje pasáž odpovídající dvěma za sebou
 * následujícícm regulárním výrazům zadaným jejímu konstruktoru.
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  0.0.2007
 */
public class Posloupnost implements IRegVýraz
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    /** Regulární výrazy, s nimiž se text porovnává. */
    private final IRegVýraz první, druhý;


//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří instanci kontrolující, zda další text odpovídá posloupnosti
     * dvou zadaných regulárních výrazů.
     *
     * @param první Regulární výraz, jemuž má odpovídat úvodní část textu
     * @param druhý Regulární výraz, jemuž má odpovídat následující část textu
     */
    public Posloupnost( IRegVýraz první, IRegVýraz druhý ) {
        this.první = první;
        this.druhý = druhý;
    }


//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ  METODY INSTANCÍ ===============================================

    /***************************************************************************
     * Vyhodnotí, zda následující text v parametru <code>zdroj</code>
     * odpovídá posloupnosti zadaných regulárních výrazů.
     * Odpovídá-li, přesune refereční bod za příslušný text.
     *
     * @param zdroj Přepravka s analyzovaným textem a indexem počátečního znaku
     * @return <code>true</code> pokud následující text odpovídá požadavkům
     */
    @Override
    public boolean vyhodnoť( Zdroj zdroj ) {
        return první.vyhodnoť(zdroj)  &&  druhý.vyhodnoť(zdroj);
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY =====================================================================
}
