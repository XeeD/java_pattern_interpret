package rup.česky.společně;

import java.util.HashMap;
import java.util.Map;


/*******************************************************************************
 * Třída slouží k převodů znaků a řetězců do znakové sady ASCII.
 * Její metody konvertují zadané texty či znaky na řetězce
 * obsahující pouze znaky s kódem menším než 128.
 * Znaky s kódem větším než 127 převedou na jejich ASCII ekvivalenty
 * (včetně možnosti rozepsat znak na více znaků, např. ß -> ss, « -> <<)
 * nebo na tvar {@code \}{@code uXXXX}.
 *
 * @author    Rudolf Pecinovský
 * @version   1.00.000,  18.2.2005
 */
public final class ToASCII
{
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================

    /** Mapa s převody znaků do ASCII. */
    private static final Map<Character,String> PŘEVOD =
                                           new HashMap<Character, String>(64);
    static {
        String[][] dvojice = {
            {"Á", "A"},  {"á", "a"},    {"Ä", "AE"}, {"ä", "ae"},
            {"Č", "C"},  {"č", "c"},    {"Ď", "D"},  {"ď", "d"},
            {"É", "E"},  {"é", "e"},    {"Ě", "E"},  {"ě", "e"},
            {"Í", "I"},  {"í", "i"},
            {"Ĺ", "L"},  {"ĺ", "l"},    {"Ľ", "L"},  {"ľ", "l"},
            {"Ň", "N"},  {"ň", "n"},    {"Ó", "O"},  {"ó", "o"},
            {"Ô", "O"},  {"ô", "o"},    {"Ö", "OE"}, {"ö", "oe"},
            {"Ŕ", "R"},  {"ŕ", "r"},    {"Ř", "R"},  {"ř", "r"},
            {"Š", "S"},  {"š", "s"},    {"Ť", "T"},  {"ť", "t"},
            {"Ú", "U"},  {"ú", "u"},    {"Ů", "U"},  {"ü", "ue"},
            {"Ü", "UE"}, {"ü", "ue"},
            {"Ý", "Y"},  {"ý", "y"},
            {"Ž", "Z"},  {"ž", "z"},
            {"ß", "ss"}, {"©", "(c)"},  {"®", "(R)"},
            {"‹", "<"},  {"›", ">"},    {"«", "<<"}, {"»", ">>"},
            {"„", "\""}, {"“", "\""},   {"”", "\""},
            {"‚", "\'"}, {"‘", "\'"},   {"’", "\'"},
            {"×", "x"},  {"÷", ":"},
            {"–", "-"},  {"—", "-"},    //ndash, mdash
            {"¦", "|"},
        };
        for( String[] ss : dvojice ) {
            PŘEVOD.put( new Character(ss[0].charAt(0)),  ss[1] );
        }
    }



//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ========================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ========================================
//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     * Zkonvertuje zadaný řetězec na jeho ekvivalent
     * obsahující pouze znaky s kódem menším než 128.
     * Znaky s kódem větším než 127 převede na jejich ASCII ekvivalenty
     * (včetně možnosti rozepsat znak na více znaků, např. ß -> ss, « -> <<)
     * nebo na tvar {@code \}{@code uXXXX}.
     *
     * @param text Text určený k převodu
     * @return  Převedený text
     */
    public static String text( CharSequence text )
    {
        final int DÉLKA = text.length();
        final StringBuilder sb = new StringBuilder(DÉLKA);
        for( int i = 0;   i < DÉLKA;   i++ ) {
            char c = text.charAt(i);
            if( c < 128 ) {
                sb.append(c);
            }else if( PŘEVOD.containsKey(c) ) {
                sb.append( PŘEVOD.get(c) );
            }else {
                sb.append( rozepiš(c) );
            }
        }
        return sb.toString();
    }


    /***************************************************************************
     * Zkonvertuje zadaný znak na jeho ekvivalent s kódem menším než 128.
     * Znaky s kódem větším než 127 převede na jejich ASCII ekvivalenty
     * (včetně možnosti rozepsat znak na více znaků, např. ß -> ss, « -> <<)
     * nebo na tvar {@code \}{@code uXXXX}.
     *
     * @param c konvertovaný znak
     * @return ekvivalent zadaného znaku bez diakritiky
     */
    public static String znak( char c )
    {
            if( c < 128 ) {
                return (Character.toString(c));
            }else if( PŘEVOD.containsKey(c) ) {
                return PŘEVOD.get(c);
            }else {
                return rozepiš(c);
            }
    }



//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================

    /** Soukromy konstruktor bránící vytvoření instance. */
    private ToASCII() {}



//== ABSTRAKTNÍ METODY =========================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =====================================
//== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ========================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================

    /***************************************************************************
     *
     */
    private static String rozepiš(char c) {
        return String.format( "\\u%04x", (int)c );
    }



//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== INTERNÍ DATOVÉ TYPY =======================================================
//== TESTOVACÍ METODY A TŘÍDY ==================================================
//
//    /***************************************************************************
//     * Testovací metoda.
//     */
//    public static void test()
//    {
//        CharSequence txt = new StringBuilder(
//                           "Příliš žluťoučký kůň úpěl ďábelské ódy.\n" +
//                           "PŘÍLIŠ ŽLUŤOUČKÝ KŮŇ ÚPĚL ĎÁBELSKÉ ÓDY.\n" +
//                           "[müßli](§-ß-¤) «Göthe»" );
//        System.out.println(txt);
//        String bhc = ToASCII.text( txt );
//        System.out.println(bhc);
//    }
//    /** @param args GUI příkazového řádku - nepoužívané. */
//    public static void main(String args[]) {
//        test();
//    }
}
