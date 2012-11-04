package xvodl00;

import java.util.Objects;

/**
 * Identifikátor představuje v gramatice našeho jazyka buď název uživatelsky
 * definované procedury, nebo funkce.
 * 
 * Identifikátor je implementován tak, aby bylo možné vytvářet více jeho
 * instancí. Díky implementaci metody <code>equals()</code> je možné zjisit,
 * zda dvě různé instance identifikátoru představují stejný název.
 * 
 * Poznámka autora: Bylo by možná výhodnější implementovat statickou
 * tovární metodu třídy Identifikátor, která by pro každý daný název
 * vytvořila pouze jednu instanci Identifikátoru. Pokud by tato tovární
 * metoda byla zavolána znovu, tak by se již nevytvářela nová instance,
 * ale byla by vrácena instance již dříve vytvořená instance.
 *
 * @author Lukáš Voda (xvodl00)
 */
public class Identifikator {

    /**
     * Jméno identifikátoru je textový řetězec
     */
    final private String jméno;

    /**
     * Konstruktor vytvoří instanci Identifikátoru pouze pokud jméno
     * identifikátoru je platné dle pravidel definovaných v gramatice jazyka.
     * 
     * @param jméno 
     */
    public Identifikator(String jméno) {
        if (jeIdentifikátor(jméno)) {
            this.jméno = jméno;
        } else {
            throw new RuntimeException("Není platný identifikátor:" + jméno);
        }
    }

    @Override
    public boolean equals(Object jiný) {
        if (jiný == this) {
            return true;
        }
        
        if (jiný instanceof Identifikator) {
            Identifikator iJiný = (Identifikator) jiný;
            return iJiný.getJméno().equals(this.getJméno());
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.jméno);
        return hash;
    }
    
    /**
     * Metoda zjištuje, zda dané jméno je platným jménem identifikátoru.
     * 
     * V gramatice jazyka jsou platné identifikátory definovány takto:
     * ZobPismeno ::= Pismeno | _
     * Identifikátor ::= ZobPismeno [ZobPismeno | Číslice]...
     * 
     * @param jméno
     * @return 
     */
    private boolean jeIdentifikátor(String jméno) {
        return jméno.matches("^[\\p{L}\\p{M}_][\\p{L}\\p{M}_\\d]+$");
    }

    /**
     * @return Jméno identifikátoru
     */
    public String getJméno() {
        return jméno;
    }
}
