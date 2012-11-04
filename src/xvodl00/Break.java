package xvodl00;

/**
 * Tato třída představuje příkaz <code>break</code> který je definovaný v námi
 * použité gramatice "Programovacího jazyka robota.
 * 
 * Je implementovaná pomocí vyhození výjimky SkokBreak, která v programu
 * probublá na místo, kde bude zachycena. Může být zachycena v metodě
 * <code>proveď()</code> třídy While (tj. může přerušit While cyklus).
 * 
 * Pokud není zachycena v některé While smyčce, tak program "spadne".
 *
 * @author Lukáš Voda (xvodl00)
 */
public class Break implements IPrikaz {

    @Override
    public void proveď() throws SkokBreak {
        throw new SkokBreak();
    }
    
}
