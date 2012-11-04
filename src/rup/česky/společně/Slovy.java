package rup.česky.společně;

/*******************************************************************************
 * Třída Slovy slouží k převodu celých čísel na textové řetězce
 * vyjadřující hodnotu zadaného čísla slovy.
 *
 * @author    Rudolf Pecinovský
 * @version   2.00.000,  11.3.2005
 */
public final class Slovy
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

    /** Největší číslo, které umí program převést na text. */
    public static final long MAX = Long.MAX_VALUE;

    private static final String jednotky[] = new String[]{
        "",        "jedna",    "dva",       "tři",      "čtyři",
        "pět",     "šest",     "sedm",      "osm",      "devět",
        "deset",   "jedenáct", "dvanáct",   "třináct",  "čtrnáct",
        "patnáct", "šestnáct", "sedmnáct",  "osmnáct",  "devatenáct"
    };

    private static final String desítky[] = new String[]{
        "",        "",         "dvacet",    "třicet",   "čtyřicet",
        "padesát", "šedesát",  "sedmdesát", "osmdesát", "devadesát"
    };

    private static final String stovky[] = new String[]{
        "",        "sto",      "dvě stě",   "tři sta",  "čtyři sta",
        "pět set", "šest set", "sedm set",  "osm set",  "devět set"
    };

    private static final String řády[][] = {
        null,
        { "tisíc",     "tisíce",   "tisíc"    },
        { "milion",    "miliony",  "milionů"  },
        { "miliarda",  "miliardy", "miliard"  },
        { "bilion",    "biliony",  "bilionů"  },
        { "biliarda",  "biliardy", "biliard"  },
        { "trilion",   "trliony",  "trilionů" },
    };

//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================
//== OSTATNÍ METODY TŘÍDY ======================================================

    /***************************************************************************
     * Metoda vrátí řetězec představující slovní vyjádření zadaného čísla.
     *
     * @param číslo  Číslo, které cheme vyjáídřit slovy.
     *
     * @return Slovní vyjádření zadaného čísla
     * @throws IllegalArgumentException je-li číslo příliš malé nebo veliké.
     */
    public static String slovy( long číslo )
    {
        if( číslo == 0 )
            return "nula";      //====================>
        if( (číslo < 0)  ||  (MAX < číslo) )
            throw new IllegalArgumentException(
                "Lze převádět pouze nezáporná čísla do " + MAX );
        return převeď( číslo );
    }

//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /***************************************************************************
     * Tato třída bude knihovní, bude obsahovat pouze statické metody,
     * takže od ní zakážeme vytvářet instance.
     */
    private Slovy()    {}

//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =====================================
//== PŘEKRYTÉ METODY IMPLEMENTOVANÝCH ROZHRANÍ =================================
//== PŘEKRYTÉ ABSTRAKTNÍ METODY RODIČOVSKÉ TŘÍDY ===============================
//== PŘEKRYTÉ KONKRÉTNÍ METODY RODIČOVSKÉ TŘÍDY ================================
//== NOVĚ ZAVEDENÉ METODY INSTANCÍ =============================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Metoda vrátí řetězec představující slovní vyjádření zadaného čísla.
     *
     * @param číslo  Číslo, které cheme vyjáídřit slovy.
     *
     * @return Slovní vyjádření zadaného čísla
     * @throws IllegalArgumentException je-li číslo příliš malé nebo veliké.
     */
    private static String stovky( int číslo )
    {
        if( číslo == 0 )
            return null;
        if( číslo < 20 )
            return jednotky[ číslo ];
        int jednotek = číslo % 10;
        int desítek  = číslo / 10;
        int stovek   = desítek / 10;
            desítek  = desítek % 10;
        return stovky[stovek] +
              (((stovek  > 0)  &&  (číslo%100 > 0))  ?  " "  :  "")  +
              ((desítek < 2)  ?  jednotky[ 10*desítek + jednotek ]
                              :  (desítky [ desítek ]) +
                                 ((jednotek > 0)  ?  " "  :  "")  +
                                 jednotky[ jednotek ]);
    }


    /***************************************************************************
     * Metoda vrátí řetězec představující slovní vyjádření zadaného čísla.
     *
     * @param číslo  Číslo, které cheme vyjáídřit slovy.
     *
     * @return Slovní vyjádření zadaného čísla
     * @throws IllegalArgumentException je-li číslo příliš malé nebo veliké.
     */
    private static String převeď( long číslo )
    {
        String[] texty = new String[ řády.length ];
        texty[0] = stovky( (int)(číslo % 1000) );
        int trojic = 1;
        while( (číslo /= 1000) > 0 )
        {
            int tříčíslí = (int)(číslo % 1000);
            if( tříčíslí == 0)
            {
                trojic++;
                continue;
            }
            String počet = stovky( tříčíslí );
            String řád;
            if( tříčíslí == 1)
            {
                řád = řády[trojic][0];
            }
            else if( tříčíslí <= 3 )
            {
                řád = řády[trojic][1];
                if( (tříčíslí == 2)  &&
                    (trojic > 2)     &&
                    ((trojic&1) == 1) )
                //Jsou to miliardy, biliardy, ...
                {
                    počet = "dvě";
                }
            }
            else
            {
                řád = řády[trojic][2];
            }
            texty[trojic] = počet + " " + řád;
            trojic++;
        }
        StringBuilder sb = new StringBuilder();
        for( int i = trojic-1;   i >= 0;   i-- )
        {
            if( texty[i] == null )
                continue;
            if( sb.length() > 0 )
                sb.append(" ");
            sb.append( texty[i] );
        }
//        String ret = sb.toString();
        return sb.toString();
    }


//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTY A METODA MAIN =======================================================
////+ main
//    public static void main( String[] args )
//    {
//        long[] číslo = { 0L, 1L, 12L, 34L, 567L, 8900L, 2345L, 678000L,
//            1000234L, 2000000034, 2345678901234567890L, MAX };
//        for( int i = 0;   i < číslo.length;   i++ )
//        {
//            long c = číslo[i];
//            System.out.printf( "%,d = %s\n", c, slovy( c ) );
//        }
//    }
////- main
}
