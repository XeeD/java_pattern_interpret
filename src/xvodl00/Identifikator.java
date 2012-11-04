/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

import java.util.Objects;

/**
 *
 * @author XeeD
 */
public class Identifikator {

    final private String jméno;

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
    
    private boolean jeIdentifikátor(String jméno) {
        return jméno.matches("^[\\p{L}\\p{M}_][\\p{L}\\p{M}_\\d]+$");
    }

    /**
     * @return the jméno
     */
    public String getJméno() {
        return jméno;
    }
}
