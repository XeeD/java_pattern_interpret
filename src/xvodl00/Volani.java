package xvodl00;

/**
 * Instance třídy představuje příkaz Volání procedury, který je definován v
 * gramatice jazyka. Konstruktor požaduje jako svůj parametr identifikátor
 * definované procedury. Dle identifikátoru nalezne danou proceduru v seznamu
 * všech definovaných procedur. Pokud v seznamu není, nezachycujeme vyhozenou
 * výjimku, ale program místo toho necháme spadnout.
 *
 * @author Lukáš Voda
 */
public class Volani implements IPrikaz {

    private IProcedura procedura;

    public Volani(Identifikator identifikátor) {
        this.procedura = DefinovaneProcedury.getDefinice(identifikátor);
    }

    @Override
    public void proveď() throws Skok {
        procedura.proveď();
    }
}
