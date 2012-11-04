package robot.cz;

/**
 *  Množina možných barev robota
 */
public interface IBarva {

    //Všechny atributy jsou implicitně public static final
    int CERNY     = 0;
    int MODRY     = 1;
    int CERVENY   = 2;
    int FIALOVY   = 3;
    int ZELENY    = 4;
    int AZUROVY   = 5;
    int BLANKYTNY = 5;
    int ZLUTY     = 6;
    int BILY      = 7;

    int BAREV     = 8;

    String[] barva$ = { "CERNY",   "MODRY",    "CERVENY",  "FIALOVY",
                        "ZELENY",  "AZUROVY",  "ZLUTY",    "BILY"   };

}//public interface IBarva
