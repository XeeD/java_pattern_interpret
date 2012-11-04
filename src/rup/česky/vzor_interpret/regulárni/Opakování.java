package rup.česky.vzor_interpret.regulárni;


/*******************************************************************************
 * Instance třídy <code>Opakování</code> vyhledá v analyzovaném textu případná
 * opakování zadaného regulárního výrazu a přesune další vyhodnocování
 * až za ně. Protože se zadaný regulární výraz může v analyzovaném textu
 * vyskytnou i nulakrát, vrací její vyhodnocovací metoda vždy <code>true</code>.
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  0.0.2006
 */
public class Opakování implements IRegVýraz
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    /** Výraz, jehož výskyty máme přeskakovat. */
    private final IRegVýraz výraz;


//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří instanci přeskakující ve vyhodnocovaném textu případné
     * (i opakované) výskyty zadaného regulárního výrazu.
     *
     * @param výraz Výraz, jehož následující výskyty přeskakujeme
     */
    public Opakování( IRegVýraz výraz)
    {
        this.výraz = výraz;
    }


//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ  METODY INSTANCÍ ===============================================

    /***************************************************************************
     * Přesune referenční bod ve vyhodnocovaném textu až za případná opakování
     * zadaného regulárnímu výrazu.
     *
     * @param zdroj Přepravka s analyzovaným textem a indexem počátečního znaku
     * @return Vždy <code>true</code>
     */
    @Override
    @SuppressWarnings("empty-statement")
    public boolean vyhodnoť( Zdroj zdroj ) {
        while( výraz.vyhodnoť( zdroj ) );
        return true;
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY =====================================================================
}
