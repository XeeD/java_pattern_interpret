package rup.česky.vzor_interpret.kalkulačka_2;

import java.util.Set;
import java.util.TreeMap;
import java.util.Map;

/*******************************************************************************
 * Instance třídy <code>Kontext</code> přestavují sady proměnných
 * s přiřazenými hodnotami.
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2007
 */
public class Kontext
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

    public static final Double DOUBLE_0 = 0.0;


//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

    private final Map<String, Double> hodnoty = new TreeMap<String, Double>();


//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================
//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří nový, prázdný kontext.
     */
    public Kontext()
    {
    }


    /***************************************************************************
     * Vytvoří nový kontext a vloží do něj inicializované proměnné
     * zadané v parametru.
     *
     * @param proměnné Textové řetězce obsahující názvy proměnných a jejich
     *                 hodnoty. Podrobnosti v metodě {@link nastav(String...)}.
     *
     */
    public Kontext( String... proměnné )
    {
        nastav( proměnné );
    }


//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================

    /***************************************************************************
     * Vrátí množinu instancí třídy {@link Map.Entry} obsahujících
     * informace o všech porměnných v zadaném kontextu přičemž
     * každá z instancí v množině obsahuje název proměnné a její hodnotu.
     */
    public Set<Map.Entry<String,Double>> getProměnné()
    {
        return hodnoty.entrySet();
    }


    /***************************************************************************
     * Vrátí hodnotu proměnné se zadaným názvem.
     *
     * @param název Název proměnné, jejíž hodnotu zjišťujeme
     * @return      Hodnota zadané proměnné
     */
    public double hodnota( String název )
    {
        Double hodnota = hodnoty.get( název );
        if( hodnota == null )
            throw new IllegalStateException(
                "\nProměnná " + název + " neexistuje");
        return hodnota.doubleValue();
    }


    /***************************************************************************
     * Vrátí hodnotu zadané proměnné.
     *
     * @param proměnná Proměnná, jejíž hodnotu zjišťujeme
     * @return Hodnota zadané proměnné
     */
    public double hodnota( Proměnná proměnná )
    {
        return hodnota( proměnná.getNázev() );
    }


    /***************************************************************************
     * Nastaví zadaným proměnným zadané počáteční hodnoty.
     * Požadovaná nastavení se zadávají jako textové řetězce ve tvaru<br>
     * <code><i>NázevProměnné</i> = <i>hodnota</i></code><br>
     * V jednom řetězci je možné inicializovat více proměnných.
     * Jednotlivé inicializace se v takovém případě oddělují čárkou nebo
     * středníkem. Případné mezery v okolí rovnítek, čárek a středníků
     * se ignorují.
     *
     * @param inicializace  Pole incializačních řetězců
     */
    public final void nastav( String... inicializace )
    {
        for( String dávka : inicializace ) {
            String[] sada = dávka.split( " *[,;] *" );
            for( String přiřazení : sada ) {
                String[] ph = přiřazení.split( " *= *" );
                přiřaď( Double.parseDouble( ph[1] ), ph[0] );
            }
        }
    }


    /***************************************************************************
     * Přiřadí proměnným zadaným svým názvem zadanou hodnotu.
     *
     * @param název    Název proměnné, které přiřazujeme hodnotu
     * @param hodnota  Přiřazovaná hondota
     */
    public void přiřaď( double hodnota, String... název )
    {
        Double h = Double.valueOf( hodnota );
        for( String s : název )
            hodnoty.put( s, h );
    }


    /***************************************************************************
     * Přiřadí zadaným proměnným nulovou hodnotu.
     *
     * @param proměnné Názvy nulovaných proměnných oddělené bílými znaky
     */
    public void nuluj( String... proměnná )
    {
        for( String s : proměnná )
            hodnoty.put( s, DOUBLE_0 );
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
