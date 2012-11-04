package robot;


/**
 * The interface describing the main set of robot's methods.
 */
public interface IRobot extends IKarel{

    public void move();
    public void turnLeft();
    public void put();
    public void pick();

    public boolean isMarker();
    public boolean isWall();
    public boolean isEast();
    public boolean isRobot();

    public void quick();
    public void returnSpeed();
    public void setDelay( int ms );

    //public static void pause( double seconds );

    public boolean isInAction();
    public void    turnOff();
    public void    turnOn( int x, int y, int dir );

    public int   getX ();
    public int   getY ();
    public int   getDirection();
    public int   getColor();
    public int   getDelay();
    public Robot getRobotAhead();

}//public interface IRobot extends IKarel
