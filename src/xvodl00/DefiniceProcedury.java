package xvodl00;

/**
 * Tato třída představuje uživatelem definovanou proceduru. Procedura se skládá
 * ze svého jména (identifikátoru) a z posloupnosti příkazů, která může být
 * přerušena příkazem <code>Return</code>.
 *
 *
 * @author Lukáš Voda (xvodl00)
 */
public class DefiniceProcedury implements IProcedura {

    final private Identifikator identifikátor;
    final private PosloupnostPrikazu posloupnostPříkazů;

    /**
     * Konstruktor bere dva parametry - identifikátor (název procedury)
     * a poslopunost příkazů (tělo procedury).
     * 
     * @param identifikátor
     * @param posloupnostPříkazů 
     */
    public DefiniceProcedury(Identifikator identifikátor, PosloupnostPrikazu posloupnostPříkazů) {
        this.identifikátor = identifikátor;
        this.posloupnostPříkazů = posloupnostPříkazů;
        DefinovaneProcedury.přidejDefinici(identifikátor, this);
    }

    /**
     * Provede tělo procedury. Provádění těla procedury (posloupnosti
     * příkazů) může být přerušeno pomocí příkazu <code>Return</code>
     * 
     * @throws Skok 
     */
    @Override
    public void proveď() throws Skok {
        try {
            posloupnostPříkazů.proveď();
        } catch (SkokReturn skok) {
        }
    }
}
