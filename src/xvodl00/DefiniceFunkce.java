package xvodl00;

/**
 * Tato třída představuje uživatelem definovanou funkci. Každá taková funkce se
 * sklád ze svého jména (identifikátoru) a těla. Tělo funkce se skládá z
 * posloupnosti příkazů. Kromě procedur mohou být prímo v tomto těle použit
 * příkaz ReturnHodnotu. Tento příkaz vyhodí výjimku, který je zachycena v
 * instanci této třídy a následně zpracována tak, aby funkce vrátila danou danou
 * hodnotu jako svou návratovou hodnotu.
 *
 * Funkce má definovanou metodu
 *
 * @author Lukáš Voda (xvodl00)
 */
public class DefiniceFunkce implements IFunkce {

    final private PosloupnostPrikazu posloupnostPříkazů;
    final private Identifikator identifikátor;

    /**
     * Konstruktor bere dva parametry - jméno funkce a její tělo.
     *
     * @param identifikátor
     * @param posloupnostPříkazů
     */
    public DefiniceFunkce(Identifikator identifikátor, PosloupnostPrikazu posloupnostPříkazů) {
        this.identifikátor = identifikátor;
        this.posloupnostPříkazů = posloupnostPříkazů;
        DefinovaneFunkce.přidejDefinici(identifikátor, this);
    }

    /**
     * Spuštění těla funkce a vrácení návratové hodnoty. Pokud je funkce špatně
     * definovaná a eobsahuje příkaz ReturnHodnotu, tak je vyhozena výjimka a
     * program by měl být ukončen.
     *
     * @return
     * @throws Skok
     */
    @Override
    public boolean jePravda() throws Skok {
        try {
            // Provedeme příkazy a pokusíme se zjistit, jakou hodnotu
            // máme vrátit jako pravdivostní hodnotu celé funkce.
            posloupnostPříkazů.proveď();

        } catch (SkokReturnTrue skok) {
            return true;

        } catch (SkokReturnFalse skok) {
            return false;
        }

        throw new RuntimeException("Funkce musí vracet hodnotu");
    }
}
