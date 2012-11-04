package rup.česky.vzor_interpret.kalkulačka_2;

/*******************************************************************************
 * Instance třídy <code>IAritmVýraz</code>
 * přestavují vyhodnotitelné aritmetické výrazy.
 *
 * @author Rudolf PECINOVSKÝ
 * @version 0.00.000,  4.10.2007
 */

public interface IAritmVýraz extends Cloneable
{
//== VEŘEJNÉ KONSTANTY =========================================================
//== ZDĚDĚNÉ METODY ============================================================
//== DEKLAROVANÉ METODY ========================================================

    /***************************************************************************
     * Vrátí kopii daného výrazu.
     *
     * @return Kopie daného výrazu
     */
    public IAritmVýraz kopie();


    /***************************************************************************
     * Nahradí v zadaném výrazu proměnnou zadanou svým názvem zadaným výrazem
     * a vrátí upravený výraz. Substituce proměnné výrazem.
     *
     * @param proměnná Název proměnné nahrazované zadaným výrazem
     * @param výraz    Výraz nahrazující zadanou proměnnou
     * @return Výsledný výraz vzniklý z daného výrazu po nahrazení (sustituci)
     *         zadané proměnné  zadaným výrazem
     */
    public IAritmVýraz nahraď( String proměnná, IAritmVýraz výraz );


    /***************************************************************************
     * Přiřadí proměnným hodnoty uložené v zadaném kontextu, po tomto
     * přiřazení výraz vyhodnotí a vrátí získanou hodnotu.
     *
     * @param ctx Kontext obsahující informace o proměnných
     * @return    Výsledek vyhodnocení daného výrazu
     */
    public double vyhodnoť( Kontext ctx );


//== VNOŘENÉ TŘÍDY =============================================================
}
