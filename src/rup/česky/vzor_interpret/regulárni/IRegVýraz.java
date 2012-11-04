package rup.česky.vzor_interpret.regulárni;


/*******************************************************************************
 * Rozhraní <code>IRegVýraz</code> definuje požadavky na instance reprezentující
 * jednotlivé speciální druhy regulárního výrazu.
 * Kromě toho definuje jako svoji vnořenou třídu přepravku,
 * která slouží instancím tříd, jež toto rozhraní implementují,
 * k vzájemné výměně informací.
 *
 * @author Rudolf PECINOVSKÝ
 * @version 0.00.000,  0.0.2007
 */
public interface IRegVýraz
{
//== KONSTANTY =================================================================
//== METODY ====================================================================

    /***************************************************************************
     * Vyhodnotí, zda text v parametru <code>zdroj</code> počínaje pozici,
     * na níž ukazuje index, odpovídá příslušnému regulárnímu výrazu.
     * Odpovídá-li, přesune referenční bod za tento text.
     *
     * @param zdroj Přepravka s analyzovaným textem a indexem počátečního znaku
     * @return <code>true</code> pokud následující text odpovídá požadavkům
     */
    public boolean vyhodnoť( Zdroj zdroj );


//== VNOŘENÉ TŘÍDY =============================================================

    /***************************************************************************
     * Přepravka určená pro třídy implementující rozhraní <code>IRegVýraz</code>
     * k předávání informací o postupu při vyhodnocování zadaného výrazu.
     * <p>
     * Třída není neměnná, protože atribut <code>pozice</code> nemůže být
     * konstantní. Bezpečnosti je dosaženou omezením přístupových práv
     * na "package private".
     */
    static class Zdroj
    {
        /** Analyzovaný text. */
        final String text;

        /** Pozice, k níž je text prověřen. */
        int pozice = 0;

        /***********************************************************************
         * Vytvoří instanci přepravky s analyzovaným textem
         * a indexem nastaveným na počátek tohoto textu.
         *
         * @param text Text určený k ananlýze
         */
        Zdroj( String text ) {
            this.text = text;
        }
    }
}
