package xvodl00;

/**
 * Instance třídy <code>Return</code> představují příkazy <code>return</code>,
 * které jsou definovány v gramatice jazyka.
 * 
 * Tento příkaz může být použit uvnitř těla procedury pro přeřušení jejího
 * běhu (například při splnění/nesplnění nějaké podmínky).
 * 
 * Implementován je pomocí výjimky SkokReturn, která je vyhozena při provádění
 * příkazu. Tato výjimka by měla být zachycena prováděnou procedurou a měla
 * by ukončit její běh.
 *
 * @author Lukáš Voda (xvodl00)
 */
public class Return implements IPrikaz {

    @Override
    public void proveď() throws SkokReturn {
        throw new SkokReturn();
    }
    
}
