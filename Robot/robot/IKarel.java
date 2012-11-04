package robot;


/**
 * The interface describing the main set of robot's methods.
 */
public interface IKarel{

    public void move();
    public void turnLeft();
    public void put();
    public void pick();

    public boolean isMarker();
    public boolean isWall();
    public boolean isEast();
    public boolean isRobot();

}//public interface IKarel
