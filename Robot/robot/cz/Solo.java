/*
 * Solo.java
 *
 * @author  RUP
 * Created on 6. červen 2002, 18:18
 */

package robot.cz;

/**
 *
 */
public class Solo extends java.lang.Object {

    private static Robot karel = new Robot();


    protected static void krok() {
        karel.krok();
    }

    protected static void vlevoVbok() {
        karel.vlevoVbok();
    }

    protected static void poloz() {
        karel.poloz();
    }

    protected static void zvedni() {
        karel.zvedni();
    }

    protected static void konec() {
        System.exit(0);
    }

    protected static boolean jeZnacka() {
        return karel.jeZnacka();
    }

    protected static boolean jeZed() {
        return karel.jeZed();
    }

    protected static boolean jeVychod() {
        return karel.jeVychod();
    }

    public static void zmenDvorek( String[] layout ) {
        Robot.zmenDvorek( layout );
    }
    
    protected static void rychle() {
        karel.rychle();
    }
    
    protected static void vratRychlost() {
        karel.vratRychlost();
    }

}//public class Solo extends java.lang.Object
