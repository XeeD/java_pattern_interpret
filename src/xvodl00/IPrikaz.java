package xvodl00;

/**
 * Instance rozhraní <b><code>IPříkaz</code><b> představují jednotlivé příkazy,
 * tak jak jsou definovány v gramatice jazyka.
 * 
 * @author Lukáš Voda (xvodl00)
 */
public interface IPrikaz {

    public void proveď() throws Skok;
}
