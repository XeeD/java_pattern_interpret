package rup.česky.vzor_interpret.regulárni;


/*******************************************************************************
 * Instance třídy <code>Volba</code> dokáží vyhodnotit,
 * zda v předloženém textu následuje pasáž odpovídající některému ze dvou
 * regulárních výrazů zadaných jejímu konstruktoru.
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  0.0.2007
 */
public class Volba implements IRegVýraz
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
     * Vytvoří instanci kontrolující, zda další text odpovídá jednomu ze dvou
     * zadných regulárních výrazů.
     *
     * @param první První akceptovatelný výraz
     * @param druhý Druhý akceptovatelný výraz
     */
    public Volba( IRegVýraz první, IRegVýraz druhý ) {
        this.první = první;
        this.druhý = druhý;
    }


//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ  METODY INSTANCÍ ===============================================

    /***************************************************************************
     * Vyhodnotí, zda text v parametru zdroj
     * odpovídá příslušnému regulárnímu výrazu.
     * Odpovídá-li, přesune referenční bod za příslušný text.
     *
     * @param zdroj Přepravka s analyzovaným textem a indexem počátečního znaku
     * @return <code>true</code> pokud následující text odpovídá požadavkům
     */
    @Override
    public boolean vyhodnoť( Zdroj zdroj ) {
        return první.vyhodnoť(zdroj)  ||  druhý.vyhodnoť(zdroj);
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY =====================================================================
}
