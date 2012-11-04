package xvodl00;

import robot.cz.Robot;

/**
 * Instance třídy <b><code>Program</code></b> předtavují vnitřní reprezentac.
 *
 * @author jméno autora
 * @version 0.00.000, 0.0.2008
 */
public class Program {
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================

    static Robot robot;

    static {
        String[] world$ = {
            "00000000000000",
            "0##0#########0",
            "###0######00#0",
            "0#000000g####0",
            "0############0",
            "0#00#0#0000##0",
            "0##0#000##0##0",
            "00000#0###0##0",
            "0###000##00000"
        };
        Robot.pripravDvorek(world$);
    }
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
    private final PosloupnostPrikazu posloupnostPříkazů;

//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY ATRIBUTŮ TŘÍDY ==========================================
//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY ===========================================
    /**
     * *************************************************************************
     *
     */
    public static Robot getRobot() {
        return robot;
    }

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================
    /**
     * *************************************************************************
     *
     */
    public Program(PosloupnostPrikazu pp) {
        posloupnostPříkazů = pp;
    }

//== ABSTRAKTNÍ METODY =========================================================
//== PŘÍSTUPOVÉ METODY INSTANCÍ ================================================
//== OSTATNÍ NESOUKROMÉ  METODY INSTANCÍ =======================================
    /**
     * *************************************************************************
     *
     */
    public void proveď() {
        robot = new Robot();
        try {
            posloupnostPříkazů.proveď();
        } catch (Skok skok) {
            throw new RuntimeException("Nezachycený skok");
        }
        //IO.zpráva("Hotovo");
        robot.odstran();
        robot = null;
    }

//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTOVACÍ METODY A TŘÍDY ==================================================
    /**
     * *************************************************************************
     * Testovací metoda.
     */
    public static void test() {
        // Zde je definována vnitřní reprezentace programu
        PosloupnostPrikazu posl = new PosloupnostPrikazu(AtomickaProcedura.krok);

        // Definice jendnoduché funkce "neníZeď"
        // - funkce obrací logiku Atomické funkce jeZeď pomocí podmínky
        //   se dvěmi větvemi
        DefiniceFunkce funkce = new DefiniceFunkce(
                new Identifikator("neníZeď"),
                new PosloupnostPrikazu(new Podminka(
                    AtomickaFunkce.jeZeď,
                    new PosloupnostPrikazu(new ReturnHodnotu(false)), // pokud je před robotem zeď - vrátí false
                    new PosloupnostPrikazu(new ReturnHodnotu(true))
                ))); // jinak vrátí
        
        
        // Funkci vyhledáme pomocí jejího identifikátoru
        // - zkusíme vytvořit nový identifikátor a se stejnou hodntou
        //   a dle něj hledáme.
        DefiniceFunkce fNeníZeď = DefinovaneFunkce.getDefinice(new Identifikator("neníZeď"));

        Program pgm2 = new Program(
                // Hlavní posloupnost příkazů
                new PosloupnostPrikazu(
                    // While smyčka - hledáme v bludišti značku
                    new While(
                        // Dokud před námi není značka, tak hledáme dále.
                        AtomickaFunkce.neníZnačka,
                        
                        // Příkazy prováděné ve smyčce
                        new PosloupnostPrikazu(
                            // Vnořené while
                            new While(
                                // zde používáme odkaz na dříve vytvořenou funkci "neníZeď"
                                fNeníZeď,
                                // Pokud jsme nenarazili na zeď, tak jdeme dále
                                new PosloupnostPrikazu(
                                    AtomickaProcedura.krok,
                                    // Podmínka - pokud jsme na rozcestí, tak vybereme náhodný směr
                                    new Podminka(
                                        AtomickaFunkce.jeRozcestí,
                                        new PosloupnostPrikazu(AtomickaProcedura.náhodnýSměrBezZdi)
                                    )
                                )
                            ),
                            // Došli jsme na konec "chodby", je třeba vybrat jiný náhodný směr
                            AtomickaProcedura.náhodnýSměrBezZdi
                        )),
                    // Jdeme na značku a končíme
                    AtomickaProcedura.krok,
                    AtomickaProcedura.zvedni
                ));

        //IO.zpráva("Připraveno");
        pgm2.proveď();
    }
    
    /**
     * Tato funkce definuje vnitřní reprezentaci programu "Bludička"
     * a spustí ji.
     */
    public static void testBliudička() {
        Program pgm = new Program(
                new PosloupnostPrikazu(new IPrikaz[]{new Opakovani(4,
                    new PosloupnostPrikazu(new IPrikaz[]{
                        AtomickaProcedura.krok,
                        AtomickaProcedura.vlevoVbok,}))}));
        pgm.proveď();
    }

    /**
     * @param args Parametry příkazového řádku - nepoužité.
     */
    public static void main(String[] args) throws Exception {
        test();
    }
}
