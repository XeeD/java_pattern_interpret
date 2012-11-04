package xvodl00;

import java.util.HashMap;
import java.util.Map;

/**
 * Tato třída slouží jako "zásobník" jednotlivých definovaných procedur. Pokud je
 * vytvořena nová instance DefiniceProcedury, tak je zavolána metoda
 * <code>přidejDefinici()</code>, která tuto definici přidá do seznamu definic.
 *
 * Každá procedura je uložena pod svým identifikátorem. Tato třída nedovolí, aby
 * bylo pod jedním identifikátorem uloženo více definic procedur, tj. není možné,
 * aby se dvě uživatelem definované procedura jmenovali stejně.
 *
 * @author Lukáš Voda (xvodl00)
 */
public class DefinovaneProcedury {
    static private Map<Identifikator, DefiniceProcedury> definovanéProcedury =
            new HashMap<>();
    
    
    /**
     * Není dovoleno vytvářet instance této třídy.
     */
    private DefinovaneProcedury() {
    }

    /**
     * Přidání definice procedura s daným jménem do seznamu procedur.
     *
     * @param identifikátor
     * @param procedura
     * @throws xvodl00.DefinovaneProcedura.ProceduraJizBylaDefinovana
     */
    static public void přidejDefinici(Identifikator identifikátor,
            DefiniceProcedury procedura)
            throws ProceduraJizBylaDefinovana {

        if (definovanéProcedury.containsKey(identifikátor)) {
            throw new ProceduraJizBylaDefinovana();
        }
        definovanéProcedury.put(identifikátor, procedura);
    }

    /**
     * Nalezení definované procedura s daným identifikátorem. Pokud procedura s
     * takovým jménem neexistuje, tak je vyhozena výjimka.
     *
     * @param identifikátor
     * @return definice procedura
     */
    static public DefiniceProcedury getDefinice(Identifikator identifikátor) {
        DefiniceProcedury procedura = definovanéProcedury.get(identifikátor);
        if (procedura == null) {
            throw new ProceduraDanehoJmenaNenalezena();
        }
        return procedura;
    }

    static public class ProceduraJizBylaDefinovana extends RuntimeException {
    }

    static public class ProceduraDanehoJmenaNenalezena extends RuntimeException {
    }
}
