package xvodl00;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author XeeD
 */
public class DefinovaneFunkce {

    static private Map<Identifikator, DefiniceFunkce> definovanéFunkce =
            new HashMap<>();

    static public void přidejDefinici(Identifikator identifikátor, DefiniceFunkce funkce) throws FunkceJizBylaDefinovana {
        if (definovanéFunkce.containsKey(identifikátor)) {
            throw new FunkceJizBylaDefinovana();
        }
        definovanéFunkce.put(identifikátor, funkce);
    }

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
