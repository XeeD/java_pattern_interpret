package xvodl00;

/**
 * Podmínka může mít jednu nebo dvě "větve". Pokud je daná pouze jedna větev
 * podmínky, tak tato větev je spuštěna, pokud funkce, vrací hodnotu
 * <code>true</code>.
 *
 * Pokud jsou definovány obě větve podmínky, tak druhá větev je spuštěna, pokud
 * funkce navrátí hodnotu
 * <code>false</code>
 *
 * @author Lukáš Voda (xvodl00)
 */
public class Podminka implements IPrikaz {

    private IFunkce funkce;
    private PosloupnostPrikazu posloupnostPříkazůPravda;
    private PosloupnostPrikazu posloupnostPříkazůNepravda;

    /**
     * Konstruktor pro podmínku pouze s větví
     * <code>true</code>
     *
     * @param funkce
     * @param posloupnostPříkazů
     */
    public Podminka(IFunkce funkce, PosloupnostPrikazu posloupnostPříkazů) {
        this.funkce = funkce;
        this.posloupnostPříkazůPravda = posloupnostPříkazů;
    }

    /**
     * Konstruktor pro podmínku s větvemi
     * <code>true</code> i
     * <code>false</code>.
     *
     * @param funkce
     * @param posloupnostPříkazůPravda
     * @param posloupnostPříkazůNepravda
     */
    public Podminka(IFunkce funkce, PosloupnostPrikazu posloupnostPříkazůPravda,
            PosloupnostPrikazu posloupnostPříkazůNepravda) {
        this.funkce = funkce;
        this.posloupnostPříkazůPravda = posloupnostPříkazůPravda;
        this.posloupnostPříkazůNepravda = posloupnostPříkazůNepravda;
    }

    /**
     * Provedení příkazu Podmínka. Je spuštěna buď první větev (pokud funkce
     * podmínky vrací true) nebo druhá větev (pokud je definována a pokud funkce
     * vrací false).
     *
     * @throws Skok
     */
    @Override
    public void proveď() throws Skok {
        if (funkce.jePravda()) {
            posloupnostPříkazůPravda.proveď();
        } else if (posloupnostPříkazůNepravda != null) {
            posloupnostPříkazůNepravda.proveď();
        }
    }
}
