package rup.česky.vzor_interpret.logický;

/*******************************************************************************
 * Instance třídy LogickýVýraz přestavují vyhodnotitelné logické výrazy.
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public interface LogickýVýraz
{
//== VEŘEJNÉ KONSTANTY =========================================================
//== ZDĚDĚNÉ METODY ============================================================
//== DEKLAROVANÉ METODY ========================================================

    /***************************************************************************
     * Vyhodnotí daný výraz na základě hodnot proměnných uložených
     * v zadaném kontextu.
     *
     * @param ctx Kontext obsahující informace o proměnnných
     * @return    Výsledek vyhodnocení daného výrazu
     */
    public boolean vyhodnoť( Kontext ctx );


    /***************************************************************************
     * Nahradí ve výrazu proměnnou zadanou svým názvem zadaným výrazem
     * a vrátí upravený výraz.
     *
     * @param proměnná Název proměnné nahrazované výrazem
     * @param výraz    Výraz nahrazující zadanou proměnnou
     * @return Výsledný výraz vzniklý po nahrazení zadané proměnné
     *         zadaným výrazem
     */
    public LogickýVýraz nahraď( String proměnná, LogickýVýraz výraz );


    /***************************************************************************
     * Vrátí kopii daného výrazu.
     *
     * @return Kopie daného výrazu
     */
    public LogickýVýraz kopíruj();


//== VNOŘENÉ TŘÍDY =============================================================

}
