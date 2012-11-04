/**
 * Title:        The component for teaching of programming<p>
 * Description:  Project implements the idea of world with robots,
 *               which recogizes only a small amount of commands.
 *               By teaching the robots the pupils learn to program.<p>
 * Copyright:    Copyright (c) 2001 - 2002 by Rudolf PECINOVSKÝ<p>
 * @author       Rudolf PECINOVSKÝ
 * @version      1.4
 */
package robot;


/**
 * The main class of the project.
 */
public class Robot implements IDirection, IColor, IRobot {
//==============================================================================



//===== Constants =============================================================

    protected static final int MAX_MARKERS = RWindow.getMaxMarkers();

    protected static final int WALL = -1;



//===== Static attributes =====================================================

    /**
     *  The area, in which the robots live.
     *  This area is shared by all created robots.
     */
    protected static int [][] markers;


    /**
     *  The horizontal dimension of the robots' world.
     */
    protected static int xMax;


    /**
     *  The vertical dimension of the robots' world.
     */
     protected static int yMax;


    /**
     *  The locations of particular robots.
     */
    protected static Robot[][] robot;


    /**
     *  The increments of the robot position by move in particular direction.
     */
    protected static final int [][] increment = {
        { +1, +0 }, //East
        { +0, +1 }, //North
        { -1, +0 }, //West
        { +0, -1 }  //South
    }; //protected static final int [][] increment =


    private static void initializeStaticAttributes() {
        xMax    = markers.length - 1;
        yMax    = markers[0].length - 1;
        robot   = new Robot[xMax+1][yMax+1];
        /*//TEST
        pel( "static attributes: xMax=" + xMax + ", yMax=" + yMax +
             " robot=" + robot + "[" + robot.length +
             "][" + robot[0].length + "]" );
        /*-*/
    }//private static void initializeStaticAttributes()



//===== Nonstatic attributes ==================================================

    /**
     *  Position of the robot in the area.
     *  The bottom left corner of the area has position [0;0].
     */
    protected  int xpos, ypos;

    /**
     *  The direction, in which is the robot turned
     */
    protected  int direction;

    /**
     *  The number of robot's color
     */
    protected  int color;


    /**
     *  The number of miliseconds for waiting before next step/turnLeft
     */
    protected int delay;


    /**
     * If speedLevel > 0, the robot will not wait before make next step or turn.
     */
    protected int speedLevel;


    /**
     *  The flag indicating if the robot is present int the world.
     */
    protected boolean inAction;



//===== Constructors ==========================================================

    /** Puts the robot into the standard position in left bottom corner.
     *  Robot is turned to the east.
     */
    public Robot() {
        this( 0, 0, EAST, 7 );
    } //Robot()


    /** Puts the robot into the given position [xr;yr] and turns it
     *  to the given direction dir. If there is no robot's world yet
     *  one of dimension 10×10 is created.
     *  @param xr Horizontal position of the robot.
     *  @param yr Vertical position of the robot.
     *  @param dirr Direction in whith the robot looks.
     *  @param colorr Color of the robot.
     */
    public Robot( int xr, int yr, int dirr, int colorr ) {
        /*//TEST
        pel( "Constructor is called: xr=" + xr + ", yr=" + yr +
             ", dirr=" + dirr + ", color=" + color +
             " (xMax=" + xMax + ", yMax=" + yMax + ")." );
        pel( "robot=" + robot );
        /*-*/
        xpos      = xr;
        ypos      = yr;
        direction = dirr;
        color     = colorr;
        delay     = 250;
        speedLevel= 0;
        if( markers == null ) prepareWorld( 10, 10 );
        if( (xr     < 0)  ||  (xr    > xMax)       ||
            (yr     < 0)  ||  (yr    > yMax)       ||
            (colorr < 0)  ||  (color > COLORS)     ||
            (dirr   < 0)  ||  (dirr  > DIRECTIONS)  )
        {
            RobotException e = new RobotException(
                "Constructor have got wrong parameters." );
            e.printStackTrace();
            System.exit( 1 );
        }
        if( markers[xpos][ypos] == WALL )
            throw new RobotException(
                "Robot cannot be placed on the wall." );
        if( robot[xpos][ypos] != null )
            throw new RobotException(
                "Robot cannot be put in the world - \n" +
                "there is another robot on required place." );
        robot[xpos][ypos] = this;
        inAction  = true;
        RWindow.drawRobot( xpos, ypos, direction, color );
    } //Robot( int xr, int yr, int dirr, int colorr )



//===== Public methods ========================================================

    /**
     *  Prepares the robots' world according to given layout.
     *  @param layout Pattern describing the number of markers on the
     *                particular position. Every position is represeted by
     *                single charater. 0 represent empty field, 1 field with
     *                one marker and so on.
     *                If there are more markers as nine,
     *                the letter A, B, C ... or a, b, c ... is used.
     */
    public static void prepareWorld( String[] layout ) {
        markers = RWindow.prepareWindow( layout );
        initializeStaticAttributes();
    }//public static void prepareWorld( String[] layout )


    public static void prepareWorld( int nx, int ny ) {
        markers = RWindow.prepareWindow( nx, ny );
        initializeStaticAttributes();
    }//public static void prepareWorld( String[] markers )


    public static void changeWorld( String[] layout ) {
        for( int x=0;   x <= xMax;   x++ )  {
            for( int y=0;   y <= yMax;   y++ ) {
                if( (robot[x][y] != null) && ((x != 0) || (y != 0)) ) {
                    throw robot[x][y].new RobotException(
                        "There is only one allowed robot in the changed world. " +
                        "This robot should stay at [0][0]");
                }//if
            }//for y
        }//for x
        Robot r = robot[0][0];
        if( r != null )  RWindow.drawField( 0, 0, markers[0][0] );
        try {
            RWindow.destroyWindow();
        }
        catch( Exception e ) {
            System.err.println( "Error by the world changing!" );
            e.printStackTrace();
        }
        markers = RWindow.prepareWindow( layout );
        initializeStaticAttributes();
        if( r != null )  {
            robot[0][0] = r;
            RWindow.drawRobot(0, 0, r.getDirection(), r.getColor() );
        }
    }//public static void zmenDvorek( String[] layout )

    public static int  getMaxMarkers() { return MAX_MARKERS; }
    public static char getWallChar()   { return RWindow.getWallChar(); }


    public int  getX ()        { return xpos; }
    public int  getY ()        { return ypos; }
    public int  getDirection() { return direction; }
    public int  getColor()     { return color; }
    public int  getDelay()     { return delay; }


    private static final int MIN_DELAY = 10;
    private static final int MAX_DELAY = 9999;

    public void setDelay( int ms ) {
        if( (ms < MIN_DELAY)  ||  (ms > MAX_DELAY) ) {
            RobotException e = new RobotException(
                "The requested delay is out of allowed bounds." );
            e.printStackTrace();
            System.exit( 1 );
        }
        delay = ms;
    }//public void setDelay( int ms )


    /**
     *  Moves robot to the next field.
     *  @throws UnexpectedWallException is throwed if there is a wall
     *          before the robot.
     *  @throws RobotsColisionException is throwed if there is another robot
     *          before the mooving one.
     */
    public void move() {
        if( speedLevel == 0 ) {
            try {
                Thread.sleep( delay );
            }catch( InterruptedException e ) {
                pel( "Interrupted!" );
            }
        }
        int x2 = xpos + increment[direction][0];
        int y2 = ypos + increment[direction][1];
        if( x2 < 0  ||  x2 > xMax  ||  y2 < 0  ||  y2 > yMax ||
            markers[x2][y2] == WALL )
            throw new RobotException(
                "Robot cannot move - it met the unexpected wall.");
        else if( robot[x2][y2] != null )
            throw new RobotException(
                "Robot cannot move - it met the unexpecetd second robot." );
        else{
            robot[xpos][ypos] = null;
            RWindow.drawField( xpos, ypos, markers[xpos][ypos] );
            xpos = x2;
            ypos = y2;
            robot[xpos][ypos] = this;
            RWindow.drawRobot( xpos, ypos, direction, color );
        }
    }//public void move()


    /**
     *  Turns robot 90 degrees clockwise
     */
    public void turnLeft() {
        if( speedLevel == 0 ) {
            try {
                Thread.sleep( delay );
            }catch( InterruptedException e ) {
                pel( "Interrupted!" );
            }
        }
        direction = (direction + 1) % DIRECTIONS;
        RWindow.drawRobot( xpos, ypos, direction, color );
    }//public void turnLeft()


    /**
     *  Robot pushes next marker under himself.
     *  The field can hold up to 9 markers.
     */
    public void put() {
        if( ++markers[xpos][ypos] > MAX_MARKERS )
            throw new RobotException(
                "Robot cannot put the marker - this field is full." );
    }//public void put()


    /**
     * Robot pops marker from the field he is staying on
     */
    public void pick() {
        if( --markers[xpos][ypos] < 0 )
            throw new RobotException(
                "Robot cannot pick the marker - this field is empty." );
    }//public void pick()


    /**
     *  Set the quick action without waiting before next move or turn.
     */
    public void quick() {
        speedLevel++;
    }//public void quick()


    /**
     *  Returns the wait-mode before the last call quick method.
     */
    public void returnSpeed() {
        speedLevel--;
        if( speedLevel < 0 )
            throw new RobotException(
                "Too much attempts to return the speed." );
    }//public void returnSpeed()


    /** Waits for the given amount of seconds.
     *  @param seconds The wainting time in seconds
     */
    public static void pause( double seconds ) {
        try {
            Thread.sleep( (long)(1000*seconds) );
            //wait( (long)(1000*seconds) );
        }catch( InterruptedException e ) {
            pel( "Interrupted!" );
        }
    }//public void wait( double seconds )


    /** Tests if there is some marker under robot.
     *  @return If there is some marker under robot, it returns <b>true</b>.
     *           Otherwise it returns <b>false</b>.
     */
    public boolean isMarker() {
        return markers[xpos][ypos] > 0;
    }//public boolean isMarker()


    /** Tests if there is some wall before robot.
     *  @return If there is some wall before robot, it returns <b>true</b>.
     *           otherwise it returns <b>false</b>.
     */
    public boolean isWall() {
        int x2 = xpos + increment[direction][0];
        int y2 = ypos + increment[direction][1];
        return( x2 < 0   ||   xMax < x2   ||
                y2 < 0   ||   yMax < y2   ||
                markers[x2][y2] == WALL );
    } //boolean isWall()


    /** Tests if the robot looks to the East.
     *  @return If robot looks to the East, it returns <b>true</b>,
     *           otherwise it returns <b>false</b>.
     */
    public boolean isEast() {
        return direction == EAST;
    } //boolean isWall()


    /** Tests if there is some other robot before the testing one.
     *  @return If there is some robor before it, it returns <b>true</b>,
     *           otherwise it returns <b>false</b>.
     */
    public boolean isRobot() {
        int x2 = xpos + increment[direction][0];
        int y2 = ypos + increment[direction][1];
        return (0 <= x2)  &&  (x2 <= xMax)  &&
               (0 <= y2)  &&  (y2 <= yMax)  &&
               robot[x2][y2] != null;
    } //boolean isRobot()


    /**
     *
     */
    public boolean isInAction() { return inAction; }


    /**
     *  Tests if it is possible tu put a robot on the given field.
     *  If there is no robot worls yet, the method let prepare
     *  the implicit one.
     */
    public static boolean isFieldReady( int x, int y ) {
        if( markers == null ) prepareWorld( 10, 10 );
        return( (markers[x][y] != WALL)   &&
                (robot[x][y]   == null) );
    }


    /** Tests if there is some other robot in front of the testing one.
     *  @return If there is some robor ahead it, it returns <b>true</b>,
     *           otherwise it returns <b>false</b>.
     */
    public Robot getRobotAhead() {
        return robot[xpos + increment[direction][0]]
                    [ypos + increment[direction][1]];
    }//public Robot getRobotAhead()


    /**
     *  Turn the robot off and pick it up from the world.
     */
    public void turnOff() {
        if( !inAction )
            throw new RobotException(
                "Robot cannot be turned off - it is not in action yet.");
        inAction = false;
        robot[xpos][ypos] = null;
        RWindow.drawField( xpos, ypos, markers[xpos][ypos] );
    }//public void turnOff()


    /**
     *  Put the robot on the given field in the world and turn it on.
     */
    public void turnOn( int x, int y, int dir ) {
        xpos = x;
        ypos = y;
        direction = dir;
        if( inAction )
            throw new RobotException(
                "Robot cannot be turned on - it is still on action." );
        if( robot[x][y] != null )
            throw new RobotException(
                "Robot cannot be putted in the world - \n" +
                "there is another robot on required place." );
        inAction = true;
        robot[x][y] = this;
        RWindow.drawRobot( x, y, dir, color );
    }//public void turnOff()


    /**
     *  String representation of robot. Return color and position of robot,
     *  direction in whith it looks and number of markers on the field
     *  it is staing on.
     */
    public String toString() {
        return /*"colorName[" + colorName.length +
               "],  dirName[" + dirName.length +
               "],  markers[" + markers.length +
               "][" + markers[xpos].length + "]\n" +
               /**/
        super.toString() + " - " + color$[color] +
               " robot placed on [" + xpos + ";" + ypos +
               "] turned to " + direction$[direction] +
               " staying on " + markers[xpos][ypos] + " markers is tuned " +
               (inAction  ?  "on."  :  "off.");
    }//public String toString()



//===== Private methods =======================================================

    //Aliases for debug prints
//    private static void pe ( String s ){ System.err.print  ( s ); }
    private static void pel( String s ){ System.err.println( s ); }


    /** Temporary methods *
    private void printArray( String t, Object[] a ){
        pe( t + ": {" );
        for( int i=0;   i < a.length;   i++ )
            pe( " [" + i + "]=" + a[i] );
        pel( " }" );
    }//private void printArray( String t, Object[] a ) /*-*/



//===== Exceptions ============================================================


    /**
     *  The root clas for exceptions with default message containing information
     *  on robot's position and other characteristics.
     */
    public class RobotException extends RuntimeException {

        protected RobotException() {
            super( "\n" + Robot.this.toString() ) ;
        }//protected RobotException()

        protected RobotException( String msg ) {
            super( "\n" + Robot.this.toString() + "\n" + msg );
        }//protected RobotException( String msg )

        /*
        protected RobotException( Robot r ) {
            r.super( "\n" + Robot.this.toString() ) ;
        }//protected RobotException()

        protected RobotException( Robot r, String msg ) {
            r.super( "\n" + Robot.this.toString() + "\n" + msg );
        }//protected RobotException( String msg )
        /**/

    }//public class RobotException extends RuntimeException






//===== Testing methods =======================================================
//     /**
//      * The testing proceduro of the package
//      */
//     public static void main(String[] args) throws Exception {
//         demo();
//     }//public static void main(String[] args) throws Exception


    private static void demo(){
        String[] world$ = { "00000000000",
                            "01234567890",
                            "0#########0",
                            "0#abcdefg#0",
                            "0#########0",
                            "00000000000" };
        Robot.prepareWorld( world$ );
        Robot[] r = new Robot[8];
        r[0] = new Robot( 0, 0, 0, 0 );
        r[1] = new Robot( 3, 0, 0, 1 );
        r[2] = new Robot(10, 0, 1, 2 );
        r[3] = new Robot(10, 2, 1, 3 );
        r[4] = new Robot(10, 5, 2, 4 );
        r[5] = new Robot( 6, 5, 2, 5 );
        r[6] = new Robot( 0, 5, 3, 6 );
        r[7] = new Robot( 0, 2, 3, 7 );
        //Robot rz = new Robot( 1, 2, 3, 7 );
        //Robot rr = new Robot();
        for( int ir=0;   ir < 8;   r[ir++].quick() );
        for( int i=1;   i < 200;   i++ ) {
            Robot.pause( 0.1 );
            for( int j=0;   j < 8;   j++ ) {
                //pel( "i=" + i + ", j=" + j );
                if( r[j].isWall() ) r[j].turnLeft();
                r[j].move();
            }
        }
    }//private static void demo()


//==============================================================================
}//public class Robot implements IDirection, IColor, IRobot, Serializable
