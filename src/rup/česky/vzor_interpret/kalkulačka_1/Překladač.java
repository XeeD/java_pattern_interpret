package rup.česky.vzor_interpret.kalkulačka_1;

/*******************************************************************************
 * Instance třídy Překladač přestavují lexikální a syntaktické analyzátory,
 * které převedou text do stromové struktury vnitřní reprezentace programu.
 * <p>
 * Definice gramatiky použitého jazyka je:<br>
 * <b><i>Konstanta</i> ::= #</b><i>číslo</i><b>#</b><br>
 * <b><i>Proměnná</i> ::=</b> <i>identifikátor</i><br>
 * <b><i>Poločlen</i> ::=</b> <i>Proměnná</i> | <i>Konstanta</i> |
 *                       '<b>(</b>' <i>Výraz</i> '<b>)</b>' <br>
 * <b><i>Člen</i> ::=</b> <i>Poločlen</i> | '<b>+</b>'<i>Poločlen</i> |
 *                       '<b>-</b>'<i>Poločlen</i> <br>
 * <b><i>Součin</i> ::=</b> <i>Člen</i> |
 *                          <i>Součin</i> '<b>*</b>' <i>Člen</i> |
 *                          <i>Součin</i> '<b>/</b>' <i>Člen</i> <br>
 * <b><i>Součet</i> ::=</b> <i>Součin</i> |
 *                          <i>Součet</i> '<b>+</b>' <i>Součin</i> |
 *                          <i>Součet</i> '<b>-</b>' <i>Součin</i> <br>
 * <b><i>Výraz</i> ::=</b> <i>Součet</i>
 *
 * @author    Rudolf PECINOVSKÝ
 * @version   0.00.000,  4.10.2006
 */

public class Překladač
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

    /***************************************************************************
     * Převede zadaný text na objekt typu <code>IAritmVýraz</code>,
     * který je vnitřní reprezentací zadaného kódu.
     *
     * @param text Text, který je třeba přeložit
     * @return Vnitřní reprezentace zadaného kódu
     */
    public static IAritmVýraz přeložVýraz( String text )
    {
        Zdroj zdroj = new Zdroj( text );
        return přeložVýraz( zdroj );
    }



//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Soukromý konstruktor zabraňuje vytváření insancí.
     */
    private Překladač() {}


//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ METODY INSTANCÍ ================================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Přečte další platný znak ze zadaného zdrojového kódu.
     *
     * @param zdroj  Přepravka obsahující všechny potebné informace
     *               o zpracovávaném zdrojovém programu
     * @return Další znak ze zadaného zdrojového kódu
     *         není-li ve zdrojovém kódu další znak, vrátí nulu
     */
    private static char dalšíZnak( Zdroj zdroj )
    {
        char znak;
        for( int i = zdroj.pozice;   i < zdroj.text.length();   i++ ) {
            znak = zdroj.text.charAt( i );
            if( ! Character.isWhitespace( znak ) ) {
                zdroj.pozice = i+1;
                zdroj.naŘadě = znak;
                return znak;
            }
        }
        return 0;
    }


    /***************************************************************************
     * Přeloží text začínající na aktuální pozici zdroje
     * a vrátí objekt představující daný výraz.
     *
     * @param zdroj  Přepravka obsahující všechny potebné informace
     *               o zpracovávaném zdrojovém programu
     * @return Vnitřní reprezentace přeloženého výrazu
     */
    private static IAritmVýraz přeložVýraz( Zdroj zdroj )
    {
        return přeložSoučet( zdroj );
    }


    /***************************************************************************
     * Přeloží text začínající na aktuální pozici zdroje
     * a vrátí objekt představující daný výraz.
     *
     * @param zdroj  Přepravka obsahující všechny potebné informace
     *               o zpracovávaném zdrojovém programu
     * @return Vnitřní reprezentace přeloženého výrazu
     */
    private static IAritmVýraz přeložSoučet( Zdroj zdroj )
    {
        IAritmVýraz součin = přeložSoučin( zdroj );
        IAritmVýraz součet = součin;
        if( zdroj.naŘadě == '+' ) {
            součet =
            new Plus(součin, přeložSoučet(zdroj));
        }
        else if( zdroj.naŘadě == '-' ) {
            součet = new Mínus(součin, přeložSoučet(zdroj));
        }
        return součet;
    }


    /***************************************************************************
     * Přeloží text začínající na aktuální pozici zdroje
     * a vrátí objekt představující daný výraz.
     *
     * @param zdroj  Přepravka obsahující všechny potebné informace
     *               o zpracovávaném zdrojovém programu
     * @return Vnitřní reprezentace přeloženého výrazu
     */
    private static IAritmVýraz přeložSoučin( Zdroj zdroj )
    {
        IAritmVýraz člen = přeložČlen( zdroj );
        IAritmVýraz součin = člen;
        if( zdroj.naŘadě == '*' ) {
            součin = new Krát(člen, přeložSoučin(zdroj));
        }
        else if( zdroj.naŘadě == '/' ) {
            součin = new Děl(člen, přeložSoučin(zdroj));
        }
        return součin;
    }


    /***************************************************************************
     * Přeloží text začínající na aktuální pozici zdroje
     * a vrátí objekt představující daný výraz.
     *
     * @param zdroj  Přepravka obsahující všechny potebné informace
     *               o zpracovávaném zdrojovém programu
     * @return Vnitřní reprezentace přeloženého výrazu
     */
    private static IAritmVýraz přeložČlen( Zdroj zdroj )
    {
        int start = zdroj.pozice;
        char znak = dalšíZnak( zdroj );     //Další nebílý znak
        if( znak == '-' ) {
            znak = dalšíZnak( zdroj );
            return new Krát( new Konstanta( -1 ), přeložPoločlen( zdroj ) );
        }else if( znak == '+' ) {
            znak = dalšíZnak( zdroj );
        }
        return přeložPoločlen( zdroj );
    }


    /***************************************************************************
     * Přeloží text začínající na aktuální pozici zdroje
     * a vrátí objekt představující daný výraz.
     *
     * @param zdroj  Přepravka obsahující všechny potebné informace
     *               o zpracovávaném zdrojovém programu
     * @return Vnitřní reprezentace přeloženého výrazu
     */
    private static IAritmVýraz přeložPoločlen( Zdroj zdroj )
    {
        IAritmVýraz člen = null;
        char znak = zdroj.naŘadě;
        if( znak == '#' ) {
            člen = přeložKonstantu( zdroj );
        }else if(  Character.isJavaIdentifierStart( znak ) ) {
            člen = přeložProměnnou( zdroj );
        }else if( znak == '(' ) {
            člen = přeložVýraz( zdroj );
            if( zdroj.naŘadě != ')' ) {
                CHYBA(zdroj);
            }
        }else {
            CHYBA(zdroj);
        }
        dalšíZnak( zdroj );
        return člen;
    }


    /***************************************************************************
     * Očekává ve zdrojovém programu konstantu (literál)
     * a vrátí instanci, která tuto konstantu představuje.
     */
    @SuppressWarnings("empty-statement")
    private static Konstanta přeložKonstantu( Zdroj zdroj )
    {
        int konec = zdroj.pozice;
        while( zdroj.text.charAt( konec++ ) != '#' );
        double hodnota = Double.parseDouble(
                                zdroj.text.substring(zdroj.pozice, konec-1) );
        zdroj.pozice = konec;
        return new Konstanta( hodnota );
    }


    /***************************************************************************
     * Očekává ve zdrojovém textu název proměnné
     * a vrátí instanci, která tuto proměnnou představuje.
     */
    @SuppressWarnings("empty-statement")
    private static Proměnná přeložProměnnou( Zdroj zdroj )
    {
        int počátek = zdroj.pozice-1;
        int konec   = počátek;
        String text = zdroj.text;
        while( (++konec < text.length())  &&
               Character.isJavaIdentifierPart( text.charAt(konec) ) );
        Proměnná p = new Proměnná(zdroj.text.substring(počátek, konec));
        zdroj.pozice = konec;
        return p;
    }


    /***************************************************************************
     * Společná reakce na jakoukoliv chybu odhalenou v překládaném výrazu.
     * Metoda je ale vyvolávána pouze v reakci na nejmarkantněšjí chyby.
     */
    private static void CHYBA(Zdroj zdroj)
    {
        throw new IllegalStateException( "\nZadaný výraz je neplatný:\n" +
            zdroj.text.substring( 0, zdroj.pozice ) +
            " -¤- " +
            zdroj.text.substring( zdroj.pozice ));
    }


//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================

    /***************************************************************************
     * Instance třídy <code>Zdroj</code> představují zdrojový text
     * překládaného výrazu včetně pozice, kam až se v něm překladač dostal,
     * a znaku, který uvozuje následující část výrazu.
     */
    private static class Zdroj
    {
        String text;
        char   naŘadě = 0;
        int    pozice = 0;

        Zdroj( String text ) {
            this.text = text;
        }

        String kód( int start ) {
            return text.substring( start, pozice );
        }
    }


//== TESTY A METODA MAIN =======================================================
}
