/*
 * Solo.java
 *
 * @author  RUP
 * Created on 6. červen 2002, 18:18
 */

package robot;

/**
 *
 */
public class Solo extends java.lang.Object {

    private static robot.Robot karel = new robot.Robot();


    protected static void krok() {
        karel.move();
    }

    protected static void vlevoVbok() {
        karel.turnLeft();
    }

    protected static void poloz() {
        karel.put();
    }

    protected static void zvedni() {
        karel.pick();
    }

    protected static void konec() {
        System.exit(0);
    }

    protected static boolean jeZnacka() {
        return karel.isMarker();
    }

    protected static boolean jeZed() {
        return karel.isWall();
    }

    protected static boolean jeVychod() {
        return karel.isEast();
    }

    public static void zmenDvorek( String[] layout ) {
        robot.Robot.changeWorld( layout );
    }
    
    protected static void rychle() {
        karel.quick();
    }
    
    protected static void vratRychlost() {
        karel.returnSpeed();
    }

}//public class Solo extends java.lang.Object
