package rup.česky.vzor_interpret.robot_0;



/*******************************************************************************
 * Instance rozhraní <b><code>IAkce</code><b> představují ...
 *
 * @author    jméno autora
 * @version   0.00.000,  0.0.2008
 */
public interface IAkce
{
//== KONSTANTY =================================================================
//== METODY ====================================================================

    /***************************************************************************
     *
     */
    public IAkce kopie();


    /***************************************************************************
     *
     */
    public IAkce nahraď( String procedura, IAkce akce );


    /***************************************************************************
     *
     */
    public IAkce proveď();


//== VNOŘENÉ TŘÍDY =============================================================
}
