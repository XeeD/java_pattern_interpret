package rup.česky.společně;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/*******************************************************************************
 * Knihovní třída IO obsahuje sadu jednoduchých metod
 * pro okenní vstup a výstup.
 *
 * @author    Rudolf Pecinovský
 * @version   1.00.000,  18.2.2004
 */
public final class IO
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

    private static Frame rodič = new Frame();


//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================
//== OSTATNÍ METODY TŘÍDY ======================================================

    /***************************************************************************
     * Počká zadaný počet milisekund.
     * Na přerušení nijak zvlášť nereaguje - prostě skončí dřív.
     *
     * @param milisekund   Počet milisekund, po něž se má čekat.
     */
    public static void čekej( int milisekund )
    {
        try {
            Thread.sleep( milisekund);
        }catch( InterruptedException e) {
            throw new RuntimeException( "Čekání bylo přerušeno", e );
        }
    }


    /***************************************************************************
     * Umístí na zadanou pozici společného rodiče všech dialogových oken;
     * dialogové okna se pak budou snažit vystředit vůči pozici rodiče.
     *
     * @param x  Vodorovná souřadnice
     * @param y  Svislá souřadnice
     */
    public static void rodičNa( int x, int y )
    {
        rodič.setLocation( x,  y );
    }



    /***************************************************************************
     * Umožní ručně umístit společného rodiče všech dialogových oken;
     * dialogové okna se pak budou snažit vystředit vůči pozici rodiče.
     */
    public static void umísti()
    {
        System.out.println("Pokouším se umístit rodiče");
        final boolean[] hotovo = { false };
        final Thread brzda = new Thread() {
            public void run() {
                while( !hotovo[0] ) {
                    synchronized( hotovo ) {
                        try { hotovo.wait(); }
                        catch( InterruptedException ie ){
                            System.err.println(
                                "Někdo přerušil čekání na hotovo");
                        }
                    }
                }
            }
        };
        if( rodič == null )
            rodič = new Frame();
        final Button tlačítko = new Button( "HOTOVO" );
        tlačítko.addActionListener(
            new ActionListener() {
                public void actionPerformed( ActionEvent ae ) {
                    rodič.remove( tlačítko );
                    synchronized( hotovo ) {
                        hotovo[0] = true;
                        hotovo.notify();
                    }
                    uspiRodiče();
                }
            }
        );
        rodič.add( tlačítko );
        rodič.pack();
        probuďRodiče();
        brzda.start();
        try{ brzda.join(); }
        catch( InterruptedException ie ){
            System.err.println("Někdo přerušil čekání na brzdu");
        }
        System.out.println("Konec pokusu o umístění rodiče");
    }


    /***************************************************************************
     * Zobrazí dialogové okno se zprávou a umožní uživateli odpovědět
     * ANO, NE nebo STORNO. Vrátí informaci o tom, jak uživatel odpověděl.
     * Odpovědě-li STORNO nebo zavře-li dialog, ukončí program.
     *
     * @param dotaz   Zobrazovaný text otázky.
     *
     * @return true odpověděl-li uživatel ANO, false odpověděl-li NE
     */
    public static boolean souhlas( Object dotaz )
    {
        return souhlas( "Dotaz", dotaz);
    }


    /***************************************************************************
     * Zobrazí dialogové okno se zprávou a umožní uživateli odpovědět
     * ANO, NE nebo STORNO. Vrátí informaci o tom, jak uživatel odpověděl.
     * Odpovědě-li STORNO nebo zavře-li dialog, ukončí program.
     *
     * @param dotaz   Zobrazovaný text otázky.
     * @param nadpis   Nadpis v titulkové liště dialogového okna.
     *
     * @return true   Odpověděl-li uživatel ANO, false odpověděl-li NE
     */
    public static boolean souhlas( String nadpis, Object dotaz )
    {
        probuďRodiče();
        int odpověď = JOptionPane.showOptionDialog(
            rodič,      //Rodičovská komponenta
            dotaz,      //Otázka, na kterou má uživatel odpovědět
            nadpis,     //Titulek okna
            0,          //Optiona type
            JOptionPane.QUESTION_MESSAGE, //Message type
            null,       //Icon
            new Object[] { "ANO", "NE", "STORNO"},
            "ANO"
             );
        if( (odpověď == JOptionPane.CANCEL_OPTION)  ||
            (odpověď == JOptionPane.CLOSED_OPTION) )
        {
            System.exit(0);
        }
        uspiRodiče();
        return (odpověď == JOptionPane.YES_OPTION);
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání reálné hodoty;
     * při zavření okna nebo stisku tlačítka Cancel ukončí aplikaci.
     *
     * @param výzva        Text, který se uživateli zobrazí.
     * @param doubleImpl   Implicitní hodnota.
     *
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota.
     */
    public static double zadej( Object výzva, double doubleImpl )
    {
        return Double.parseDouble( zadej( výzva, ""+doubleImpl ).trim() );
   }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání celočíselné hodoty;
     * při zavření okna nebo stisku tlačítka Cancel ukončí aplikaci.
     *
     * @param výzva     Text, který se uživateli zobrazí.
     * @param intImpl   Implicitní hodnota.
     *
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota.
     */
    public static int zadej( Object výzva, int intImpl )
    {
        return Integer.parseInt( zadej( výzva, ""+intImpl ).trim() );
    }


    /***************************************************************************
     * Zobrazí dialogové okno s výzvou k zadání textové hodoty;
     * při zavření okna nebo stisku tlačítka Cancel ukončí aplikaci.
     *
     * @param výzva        Text, který se uživateli zobrazí.
     * @param stringImpl   Implicitní hodnota.
     *
     * @return Uživatelem zadaná hodnota, resp. potvrzená implicitní hodnota.
     */
    public static String zadej( Object výzva, String stringImpl )
    {
        probuďRodiče();
        String odpověd = JOptionPane.showInputDialog(rodič, výzva, stringImpl );
        if( odpověd == null )
            System.exit(0);
        uspiRodiče();
        return odpověd;
    }


    /***************************************************************************
     * Zobrazí dialogové okno se zprávou a počká, až je uživatel odklepne;
     * při zavření okna ukončí aplikaci.
     *
     * @param text   Zobrazovaný text.
     */
    public static void zpráva( Object text )
    {
        probuďRodiče();
        int odpověď = JOptionPane.showConfirmDialog(
            rodič,    //Rodičovská komponenta
            text,     //Zpráva, kteoru uživateli sdělujeme
            "Zpráva", //Titulek okna
            JOptionPane.DEFAULT_OPTION,      //Optiona type - OK
            JOptionPane.INFORMATION_MESSAGE  //Message type
             );
        if( (odpověď == JOptionPane.CLOSED_OPTION) )
            System.exit(0);
        uspiRodiče();
    }


//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Třída IO je knihovní třídou a proto není určena k tomu,
     * aby měla nějaké instance.
     */
    private IO() {}


//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =====================================
//== PŘEKRYTÉ METODY IMPLEMENTOVANÝCH ROZHRANÍ =================================
//== PŘEKRYTÉ ABSTRAKTNÍ METODY RODIČOVSKÉ TŘÍDY ===============================
//== PŘEKRYTÉ KONKRÉTNÍ METODY RODIČOVSKÉ TŘÍDY ================================
//== NOVĚ ZAVEDENÉ METODY INSTANCÍ =============================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Nastaví rodičovské okno jako viditelné.
     */
    private static void probuďRodiče()
    {
        if( rodič != null )
            rodič.setVisible( true );
    }


    /***************************************************************************
     * Nastaví rodičovské okno jako neviditelné.
     */
    private static void uspiRodiče()
    {
        if( rodič != null )
            rodič.setVisible( false );
    }



//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
////+ main
//
//    /***************************************************************************
//     * Ověřuje možnost umístit rodiče dialogového okna (a tím i dialogové
//     * okno) na požadované místo na obrazovce.
//     */
//    public static void testUmístěníRodiče()
//    {
//        rodičNa( 500,  500 );
//        zpráva( "První pokus" );
//        rodičNa( -1000, 500 );
//        zpráva( "Druhý pokus" );
//    }
//
//
//    /***************************************************************************
//     * @param args
//     */
//    public static void main( String[] args ) {
//        testUmístěníRodiče();
//    }
////- main
}
