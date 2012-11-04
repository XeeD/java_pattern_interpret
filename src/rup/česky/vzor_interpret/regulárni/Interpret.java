package rup.česky.vzor_interpret.regulárni;


/*******************************************************************************
 * Instance třídy <code>Interpret</code> představují přeložené regulární výrazy,
 * které jsou schopny zanalyzovat, zda zadaný text
 * vyhovuje příslušnému regulárnímu výrazu.
 *
 * @author Rudolf PECINOVSKÝ
 * @version 0.00.000,  0.0.2007
 */
public class Interpret
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================

        /** Vnitřní reprezentace regulárního výrazu. */
        private final IRegVýraz výraz;


//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří instanci reprezentující zadaný regulární výraz.
     *
     * @param výraz  Regulární výraz, který chceme reprezentovat
     */
    public Interpret( IRegVýraz výraz )
    {
        this.výraz = výraz;
    }


//== ABSTRAKTNÍ METODY =========================================================
//== NESOUKROMÉ  METODY INSTANCÍ ===============================================

    /***************************************************************************
     * Zanalyzuje zadaný text a vrátí informace o tom,
     * vyhovuje-li regulárnímu výrazu reprezentovanému daným interpretem.
     *
     * @param text  Analyzovaný text
     * @return <code>true</code> vyhovuje-li zadaný text danému výrazu
     */
    @SuppressWarnings("empty-statement")
    public boolean analyzuj( String text) {
        IRegVýraz.Zdroj zdroj = new IRegVýraz.Zdroj( text );
        boolean ret = výraz.vyhodnoť( zdroj );  //Výraz je vyhodnocen
        if( !ret ) {
            return false;
        }
        //Odpovídá li, je třeba zkontrolovat, zdy byl vyhodnocen celý řetězec
        int i = zdroj.pozice - 1;
        while( (++i < text.length())  &&
               (Character.isWhitespace( text.charAt(i) )) );
        return i == text.length();
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY =====================================================================

    /***************************************************************************
     * Vytvoří interpret výrazu "padají ( žáby | trakaře )"
     * a vyhodnotí několik textů, nakolik výrazu vyhovují
     */
    public static void test() {
        Interpret interpret = new Interpret(
          new Posloupnost(
              new Literál( "padají" ),
              new Opakování(
                  new Volba(
                      new Literál( "žáby"   ),
                      new Literál( "trakaře") ) ) ) );
        String [] texty = {
            //Výrazy, které mají vyhovovat
                "padají žáby žáby trakaře",
                "padají",
                "padají trakaře trakaře trakaře",
            //Výrazy, které nemají vyhovovat
                "padají padají žáby" ,
                "padají žáby padají" ,
                "",
            //Test reakce na oddělovače
                "padajížábyžábytrakaře",
                "padají",
                "      padají  trakaře  \n     trakaře   trakaře     ",
        };
        for( String s : texty ) {
            System.out.println( s + "  --  "+ interpret.analyzuj(s) );
        }
    }
    /** @param args Parametry příkazového řádku */
    public static void main( String... args ) { test(); }
}
