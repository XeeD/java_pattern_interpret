/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

import robot.cz.Robot;

/**
 * *****************************************************************************
 * Instance třídy <b><code>Program</code></b> předtavují ...
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
        Program pgm = new Program(
                new PosloupnostPrikazu(new IPrikaz[]{new Opakovani(4,
                    new PosloupnostPrikazu(new IPrikaz[]{
                        AtomickaProcedura.krok,
                        AtomickaProcedura.vlevoVbok,}))}));

        PosloupnostPrikazu posl = new PosloupnostPrikazu(AtomickaProcedura.krok);

        DefiniceFunkce funkce = new DefiniceFunkce(
                new Identifikator("neníZeď"),
                new PosloupnostPrikazu(new Podminka(
                AtomickaFunkce.jeZeď,
                new PosloupnostPrikazu(new ReturnHodnotu(false)), // pokud je před robotem zeď - vrátí false
                new PosloupnostPrikazu(new ReturnHodnotu(true))))); // jinak vrátí
        
        DefiniceFunkce fNeníZeď = DefinovaneFunkce.getDefinice(new Identifikator("neníZeď"));

        Program pgm2 = new Program(
                new PosloupnostPrikazu(
                    new While(
                        AtomickaFunkce.neníZnačka,
                        new PosloupnostPrikazu(
                            new While(
                                fNeníZeď,
                                new PosloupnostPrikazu(
                                    AtomickaProcedura.krok,
                                    new Podminka(
                                        AtomickaFunkce.jeRozcestí,
                                        new PosloupnostPrikazu(AtomickaProcedura.náhodnýSměrBezZdi)
                                    )
                                )
                            ),
                            AtomickaProcedura.náhodnýSměrBezZdi
                        )),
                    AtomickaProcedura.krok
                ));

        //IO.zpráva("Připraveno");
        pgm2.proveď();
    }

    /**
     * @param args Parametry příkazového řádku - nepoužité.
     */
    public static void main(String[] args) throws Exception {
        test();
    }
}
