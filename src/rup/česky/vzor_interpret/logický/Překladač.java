package rup.česky.vzor_interpret.logický;

/*******************************************************************************
 * Instance třídy Překladač přestavují lexikální a syntaktické analyzátory,
 * které převedou text do stromové struktury vnitřní reprezentace programu.
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
     * Převede zadaný text na objekt typu <code>LogickýVýraz</code>,
     * který je vnitřní reprezentací zadaného kódu.
     *
     * @param text Text, který je třeba přeložit
     * @return Vnitřní reprezentace zadaného kódu
     */
    public static LogickýVýraz přelož( String text )
    {
        ZdrojovýKód zdroj = new ZdrojovýKód( text, 0 );
        return přelož( zdroj, null );
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
    private static char dalšíZnak( ZdrojovýKód zdroj )
    {
        char znak;
        for( int i = zdroj.pozice;   i < zdroj.text.length();   i++ ) {
            znak = zdroj.text.charAt( i );
            if( ! Character.isWhitespace( znak ) ) {
                zdroj.pozice = i+1;
                return znak;
            }
        }
        return 0;
    }


    /***************************************************************************
     * Přečte další platný znak ze zadaného zdrojového kódu a přeloží jej
     * do podoby zdrojového stromu, jenž je instancí <code>Logický výaz</code>.
     * <p>
     * Je-li dalším zankem binární operátor, musí být vstupním parametrem
     * <code>výraz</code> neprázdný výraz, který stojí na levé straně nalezeného
     * operátoru. Metoda jej pakpřevede na příslušný uzel grafu.
     *
     *
     * @param zdroj  Přepravka obsahující všechny potebné informace
     *               o zpracovávaném zdrojovém programu
     * @pram výraz   Logický výraz, který by stál na levé straně případného
     *               binárního operátoru
     */
    private static LogickýVýraz přelož( ZdrojovýKód zdroj, LogickýVýraz výraz )
    {
        char znak = dalšíZnak( zdroj );
        if( znak == '&' ) {
            if( výraz == null )   CHYBA(zdroj);
            return new Konjunkce( výraz, přelož( zdroj, null ) );
        }
        if( znak == '|' ) {
            if( výraz == null )   CHYBA(zdroj);
            return new Alternativa( výraz, přelož( zdroj, null ) );
        }

        if( výraz != null )   CHYBA(zdroj);
        boolean našel = false;
        if( znak == '!' ) {
            výraz = new Negace( přelož( zdroj, null ) );
            našel = true;
        }
        if( znak == '(' ) {
            výraz = přelož( zdroj, výraz );
            výraz = binární( zdroj, výraz );
            if( dalšíZnak( zdroj ) != ')' )   CHYBA(zdroj);
            našel = true;
        }
        if( ('A' <= znak)  &&  (znak <= 'Z') ) {
            výraz = new Proměnná( Character.toString( znak ) );
            našel = true;
        }
        if( !našel )  CHYBA(zdroj);
        return binární( zdroj, výraz );
    }


    /***************************************************************************
     */
    private static LogickýVýraz binární( ZdrojovýKód zdroj, LogickýVýraz výraz )
    {
        char znak = dalšíZnak( zdroj );
        zdroj.pozice --;
        if( (znak == '&')  ||  (znak == '|') ) {
            výraz = přelož( zdroj, výraz );
        }
        return výraz;
    }


    /***************************************************************************
     */
    private static void CHYBA(ZdrojovýKód zdroj)
    {
        throw new IllegalStateException( "\nZadaný výraz je neplatný:\n" +
            zdroj.text.substring( 0, zdroj.pozice ) +
            " -¤- " +
            zdroj.text.substring( zdroj.pozice ));
    }


//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================

    /***************************************************************************
     */
    private static class ZdrojovýKód
    {
        String text;
        int    pozice;

        ZdrojovýKód( String text, int pozice ) {
            this.text = text;
            this.pozice = pozice;
        }
    }


//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovací metoda.
     */
    public static void test()
    {
        String zdroj = "((X & !Y)|((!X)&Y))";
        LogickýVýraz výraz = přelož( zdroj );
        Kontext ctx = new Kontext();

        ctx.přiřaďTrue( "X" );
        ctx.přiřaďFalse( "Y" );
        boolean výsledek = výraz.vyhodnoť( ctx );
        System.out.println(
            "Výsledkem výrazu " + zdroj + " pro:" +
            "\n   x = " + ctx.hodnota( "X" ) +
            "\n   y = " + ctx.hodnota( "Y" ) +
            "\nje hodnota " + výsledek );

    }
    /** @param ppr Parametry příkazového řádku - nepoužité */
    public static void main(String[]ppr){ test(); }/*-*/
}
