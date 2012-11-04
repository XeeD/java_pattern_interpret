package rup.česky.vzor_interpret.kalkulačka_pův;

import rup.česky.společně.UEX;

/*******************************************************************************
 * Instance třídy <code>Binární</code> přestavují výraz
 * reprezentující binární operaci.
 *
 * @author Rudolf PECINOVSKÝ
 * @version 0.00.000,  4.10.2007
 */
abstract public class Binární implements IAritmVýraz, Cloneable
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================

    /** Levý operand.  */ protected IAritmVýraz levý;
    /** Pravý operand. */ protected IAritmVýraz pravý;
    /** Textová podoba kódu, jehož reprezentací je daný výraz. */
    private String kód;
    private char   operátor;


//== NESOUKROMÉ METODY TŘÍDY ===================================================

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Vytvoří nový výraz binární operace.
     *
     * @param levý  Levý operand
     * @param pravý Pravý operand
     */
    public Binární( char operátor, IAritmVýraz levý, IAritmVýraz pravý )
    {
        this.operátor = operátor;
        this.levý  = levý;
        this.pravý = pravý;
    }


//== ABSTRAKTNÍ METODY =========================================================

    /***************************************************************************
     * {@inheritDoc}
     */
    abstract protected double vyhodnoť( double levý, double pravý );


//== NESOUKROMÉ METODY INSTANCÍ ================================================

    /***************************************************************************
     * {@inheritDoc}
     */
    public Binární kopie()
    {
        Binární kopie;
        try { kopie = (Binární)this.clone();
        } catch (CloneNotSupportedException ex) {throw new UEX(ex);}
        kopie.levý  = levý .kopie();
        kopie.pravý = pravý.kopie();
        return kopie;
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public IAritmVýraz nahraď( String proměnná, IAritmVýraz výraz )
    {
        Binární subst;
        try { subst = (Binární)this.clone();
        } catch (CloneNotSupportedException ex) {throw new UEX(ex);}
        subst.levý  = levý .nahraď( proměnná, výraz );
        subst.pravý = pravý.nahraď( proměnná, výraz );
        subst.kód   = null;
        return subst;
    }


    /***************************************************************************
     * {@inheritDoc}
     */
    public double vyhodnoť( Kontext ctx )
    {
        double levý  = this.levý .vyhodnoť( ctx );
        double pravý = this.pravý.vyhodnoť( ctx );
        return vyhodnoť( levý, pravý );
    }


    /***************************************************************************
     * Vrátí řetězcovou podobu hodnoty výrazu.
     */
    public String toString()
    {
        if( kód == null )
            kód = "(" + levý + ") " + operátor + " (" + pravý + ")";
        return kód;
    }


//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
}
