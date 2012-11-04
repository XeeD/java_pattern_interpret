package xvodl00;

import java.util.HashMap;
import java.util.Map;

/**
 * Tato třída slouží jako "zásobník" jednotlivých definovaných funkcí. Pokud je
 * vytvořena nová instance DefiniceFunkce, tak je zavolána metoda
 * <code>přidejDefinici()</code>, která tuto definici přidá do seznamu definic.
 *
 * Každá funkce je uložena pod svým identifikátorem. Tato třída nedovolí, aby
 * bylo pod jedním identifikátorem uloženo více definic funkcí, tj. není možné,
 * aby se dvě uživatelem definované funkce jmenovali stejně.
 *
 * @author Lukáš Voda (xvodl00)
 */
public class DefinovaneFunkce {
    static private Map<Identifikator, DefiniceFunkce> definovanéFunkce =
            new HashMap<>();
    
    
    /**
     * Není dovoleno vytvářet instance této třídy.
     */
    private DefinovaneFunkce() {
    }

    /**
     * Přidání definice funkce s daným jménem do seznamu funkcí.
     *
     * @param identifikátor
     * @param funkce
     * @throws xvodl00.DefinovaneFunkce.FunkceJizBylaDefinovana
     */
    static public void přidejDefinici(Identifikator identifikátor,
            DefiniceFunkce funkce)
            throws FunkceJizBylaDefinovana {

        if (definovanéFunkce.containsKey(identifikátor)) {
            throw new FunkceJizBylaDefinovana();
        }
        definovanéFunkce.put(identifikátor, funkce);
    }

    /**
     * Nalezení definované funkce s daným identifikátorem. Pokud funkce s
     * takovým jménem neexistuje, tak je vyhozena výjimka.
     *
     * @param identifikátor
     * @return definice funkce
     */
    static public DefiniceFunkce getDefinice(Identifikator identifikátor) {
        DefiniceFunkce funkce = definovanéFunkce.get(identifikátor);
        if (funkce == null) {
            throw new FunkceDanehoJmenaNenalezena();
        }
        return funkce;
    }

    static public class FunkceJizBylaDefinovana extends RuntimeException {
    }

    static public class FunkceDanehoJmenaNenalezena extends RuntimeException {
    }
}
