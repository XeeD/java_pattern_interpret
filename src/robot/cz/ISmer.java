package robot.cz;

/**
 *  Směry, do nichž může být robot natočen
 */
public interface ISmer {

    //Všechny atributy jsou implicitně public static final
    int VYCHOD = 0;
    int SEVER  = 1;
    int ZAPAD  = 2;
    int JIH    = 3;

    int SMERU  = 4;

    String[] smer$ = { "VYCHOD", "SEVER", "ZAPAD", "JIH" };

}//public interface ISmer
