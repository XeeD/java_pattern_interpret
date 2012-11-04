package rup.česky.společně;

/*******************************************************************************
 * Třída Dbg obsahuje různé užitečné metody pro práci s třídami a pro ladění.
 *
 * @author    Rudolf Pecinovský
 * @version   2.00.001,  8.2005
 */
public final class Dbg
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY ATRIBUTŮ TŘÍDY ==========================================
//== OSTATNÍ METODY TŘÍDY ======================================================

    /***************************************************************************
     * Vrátí řetězec s názvem metody, která tuto metodu zavolala.
     *
     * @param rodiče  0 - vrátí pouze název metody<br>
     *                1 - vrátí název metody s názvem třídy<br>
     *                2 - vrátí název metody s názvem třídy a balíčku.
     * @param hloubka Ptá-li se metoda na svůj název nebo na název některé
     *                jí volajících metod. <br>
     *                0 - vrátí název metody, která zavolala tuto metodu<br>
     *                1 - vrátí název metody, jež zavolala metodu,
     *                    která zavolala tuto metodu<br>
     *                atd.
     * @return Požadovaný název
     */
    public static String kdoVolá( int rodiče, int hloubka )
    {
        Throwable t = new Throwable();
        StackTraceElement[] ste = t.getStackTrace();
        if( ste.length < (hloubka+2) )
            return "== NIKDO ==";
        StackTraceElement metoda = ste[hloubka+1];
        String název = metoda.getMethodName();
        if( rodiče > 0 )
        {
            String třída = metoda.getClassName();
            if( rodiče < 2 )
                třída = odbalíčkuj( třída );
            název = třída + "." + název;
        }
        return název;
    }


    /***************************************************************************
     * Vrátí řetězec s názvem metody, která tuto metodu zavolala.
     *
     * @param rodiče  0 - vrátí pouze název metody<br>
     *                1 - vrátí název metody s názvem třídy<br>
     *                2 - vrátí název metody s názvem třídy a balíčku.
     * @return Požadovaný název
     */
    public static String kdoVolá( int rodiče )
    {
        return kdoVolá( rodiče, 1 );
    }


    /***************************************************************************
     * Vrátí řetězec s pouhým názvem metody, která tuto metodu zavolala.
     *
     * @return Požadovaný název
     */
    public static String kdoVolá()
    {
        return kdoVolá( 1, 0 );
    }

//
//    /***************************************************************************
//     * Vrátí čistý název třídy bez názvů balíčků.
//     *
//     * @param c    Objekt Class třídy, jejíž název zjišťujeme
//     *
//     * @return     Čistý název třídy
//     */
//    public static String názevTřídy( Class c )
//    {
//        return c.getSimpleName();
//    }
//
//
//    /***************************************************************************
//     * Vrátí čistý název vlastní třídy zadané instance bez názvů balíčků.
//     *
//     * @param o    Odkaz na instanci, jejíž název třídy nás zajímá
//     *
//     * @return     Čistý název třídy
//     */
//    public static String názevTřídy( Object o )
//    {
//        return o.getClass().getSimpleName();
//            //odbalíčkuj( o.getClass().getName() );
//    }
//

    /***************************************************************************
     * Vyvolá výjimku oznamující, že metoda, z níž byla tato metoda volána,
     * není podporována.
     */
    public static void neumím()
    {
        UnsupportedOperationException uoe = new UnsupportedOperationException();
        StackTraceElement[] ste = uoe.getStackTrace();
        if( ste.length < 2 )
            throw new IllegalStateException(
                "Přímé volání metody \"neumim()\"" );
        StackTraceElement metoda = ste[1];
        String název = metoda.getMethodName();
        String msg = "Metoda " + název + " není implementována!";
        StackTraceElement[] st2 = new StackTraceElement[ste.length - 2];
        System.arraycopy(ste, 2, st2, 0,  st2.length );
        uoe = new UnsupportedOperationException( msg );
        uoe.setStackTrace( st2 );
        throw uoe;
    }


    /***************************************************************************
     *  Převede plný název třídy na její název bez balíčků.
     *
     * @param  plný     Plný název třídy včetně názvů balíčků
     * @return          Čistý název třídy
     */
    public static String odbalíčkuj( String plný )
    {
        return plný.substring( 1+plný.lastIndexOf('.') );
    }


    /***************************************************************************
     * Na standardní výstup vypíše vzprávu o vstupu do této metody
     * následovanou na dalším řádku zadaným textem.
     *
     * @param hloubka Hloubka dotazu na volanou metodu
     * @param text  Text, vypsaný za řádkem s oznámením vstupu do metody
     *              Je-li text "" nebo null, řádek se nevygeneruje.
     */
    public static void oznamStart( int hloubka, String text )
    {
        System.out.println("\n>>>>> Start: " + kdoVolá( hloubka, 3 ));
        if( (text != null)  &&  !text.equals("") )
            System.out.println( "      " + text );
    }


    /***************************************************************************
     * Na standardní výstup vypíše vzprávu o vstupu do této metody
     * následovanou na dalším řádku zadaným textem.
     *
     * @param text  Text, vypsaný za řádkem s oznámením vstupu do metody
     *              Je-li text "" nebo null, řádek se nevygeneruje.
     */
    public static void oznamStart( String text )
    {
        oznamStart( 2, text );
    }


    /***************************************************************************
     * Na standardní výstup vypíše vzprávu o vstupu do této metody.
     */
    public static void oznamStart()
    {
        oznamStart( 2, null );
    }


    /***************************************************************************
     * Na standardní výstup vypíše zadaný text následovný vzprávou
     * o ukončení volající metody.
     *
     * @param hloubka Hloubka dotazu na volanou metodu
     * @param text  Text, vypsaný před řádkem s oznámením výstupu z metody
     *              Je-li text "" nebo null, řádek se nevygeneruje.
     */
    public static void oznamReturn( int hloubka, String text )
    {
        if( (text != null)  &&  !text.equals("") )
            System.out.println( "      " + text );
        System.out.println("<<<<< STOP: " + kdoVolá( hloubka, 3 ) + "\n" );
    }


    /***************************************************************************
     * Na standardní výstup vypíše zadaný text následovný vzprávou
     * o ukončení volající metody.
     *
     * @param text  Text, vypsaný před řádkem s oznámením výstupu z metody
     *              Je-li text "" nebo null, řádek se nevygeneruje.
     */
    public static void oznamReturn( String text )
    {
        oznamReturn( 2, text );
    }


    /***************************************************************************
     * Na standardní výstup vypíše vzprávu o ukončení volající metody.
     */
    public static void oznamReturn()
    {
        oznamReturn( 2, null );
    }


//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Třída P není určena k tomu, aby měla nějaké instance.
     * Konstruktor je zde pouze kvůli tomu, aby bylo možno definovat jejího
     * pototmka, který množinu metod rozšíří. Vzhledem k nemožnosti vytvářet
     * instance ale ale musejí být všechny metody definovány jako statické.
     */
    private Dbg()
    {}


//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =====================================
//== PŘEKRYTÉ METODY IMPLEMENTOVANÝCH ROZHRANÍ =================================
//== PŘEKRYTÉ ABSTRAKTNÍ METODY RODIČOVSKÉ TŘÍDY ===============================
//== PŘEKRYTÉ KONKRÉTNÍ METODY RODIČOVSKÉ TŘÍDY ================================
//== NOVĚ ZAVEDENÉ METODY INSTANCÍ =============================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
////+ main
//
//    private static void badatel2()
//    {
//        badatel();
//    }
//
//
//    private static void neumím2()
//    {
//        neumím();
//    }
//
//
//    private static void badatel()
//    {
//        oznamStart();
//        for( int i=0;   i < 5;   i++ )
//        {
//            System.out.println("-- Hloubka " + i );
//            System.out.println("badatel -        met: " + kdoVolá(0, i) );
//            System.out.println("badatel -    T ř.met: " + kdoVolá(1, i) );
//            System.out.println("badatel - bal.Tř.met: " + kdoVolá(2, i) );
//        }
//        oznamReturn();
//    }
//
//    /***************************************************************************
//     * @param args
//     */
//    public static void main( String[] args )
//    {
//        oznamStart();
//        System.out.println("main: " + kdoVolá(0) );
//        System.out.println("main: " + kdoVolá(1) );
//        System.out.println("main: " + kdoVolá(2) );
//        badatel2();
//        try
//        { neumím();
//        }
//        catch( Exception e )
//        {
//            e.printStackTrace();
//        }
//        try
//        { neumím2();
//        }
//        catch( Exception e )
//        {
//            e.printStackTrace();
//        }
//        oznamReturn();
//    }
////- main
}
