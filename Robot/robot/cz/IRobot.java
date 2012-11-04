package robot.cz;


/**
 * The interface describing the main set of robot's methods.
 */
public interface IRobot extends IKarel{

    void krok();
    void vlevoVbok();
    void poloz();
    void zvedni();

    boolean jeZnacka();
    boolean jeZed();
    boolean jeVychod();
    boolean jeRobot();

    void rychle();
    void vratRychlost();
    void setCekani( int ms );

    //public static void pause( double seconds );

    boolean jeNasazen();
    void    odstran();
    void    nasad( int x, int y, int dir );

    int   getX ();
    int   getY ();
    int   getSmer();
    int   getBarva();
    int   getCekani();
    Robot robotPred();

}//public interface IRobot extends IKarel
