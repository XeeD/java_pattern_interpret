package xvodl00;

/**
 * Rozhraní, které implementují funkce, které vrací hodnotu. V definované
 * gramatice je rozdíl mezi procedurou a funkcí - funkce vrací hodnotu,
 * procedury nemají žádnou návratovou hodnotu.
 * 
 * @author Lukšá Voda (xvodl00)
 */
public interface IFunkce {
    
    /**
     * Tato metoda je volána při provádění smyčky While a v Podmínkách.
     * 
     * @return návratová pravdivostní hodnota definované funkce
     * @throws Skok
     */
    public boolean jePravda() throws Skok;
    
}
