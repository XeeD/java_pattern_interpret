package robot;

/**
 *  The set of possible robot's colors.
 *  @author       Rudolf PECINOVSKÝ
 *  @version      1.4
 */
public interface IColor {

    //All atributes are implicitly public static final

    int BLACK   = 0;
    int BLUE    = 1;
    int ROT     = 2;
    int MAGENTA = 3;
    int GREEN   = 4;
    int CYAN    = 5;
    int YELLOW  = 6;
    int WHITE   = 7;

    int COLORS  = 8;

    String[] color$ = { "BLACK",  "BLUE",  "ROT",     "MAGENTA",
                        "GREEN",  "CYAN",  "YELLOW",  "WHITE" };

}//public interface IColor
