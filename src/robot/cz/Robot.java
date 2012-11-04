/**
 * Title: The component for teaching of programming<p> Description: Project
 * implements the idea of world with robots, which recogizes only a small amount
 * of commands. By teaching the robots teache the pupils themself to program.<p>
 * Copyright: Copyright (c) 2001 by Rudolf PECINOVSKÝ<p> Company: Pecinovský &
 * Partners<p>
 *
 * @author Rudolf PECINOVSKÝ
 * @version 1.4
 */
package robot.cz;

import java.util.ArrayList;
import java.util.List;
import robot.RWindow;
import xvodl00.Program;

/**
 * Hlavní třída projektu
 */
public class Robot implements IRobot, ISmer, IBarva {
//==============================================================================

//===== Constants =============================================================
    protected static final int MAX_ZNACEK = RWindow.getMaxMarkers();
    protected static final int ZED = -1;
    protected static final String TITULEK = "Svět robotů";
//===== Static attributes =====================================================
    /**
     * Proměnná ovlivňuje, zda se při vzbuzení vlákna ze spánku vytikne
     * posloupnost volání (printStackTrace)
     */
    public boolean stackTrace = false;
    /**
     * The area, in which the robots live. This area is shared by all created
     * robots.
     */
    protected static int[][] znacek;
    /**
     * The horizontal dimension of the robots' world.
     */
    protected static int xMax;
    /**
     * The vertical dimension of the robots' world.
     */
    protected static int yMax;
    /**
     * The locations of particular robots.
     */
    protected static Robot[][] robot;
    /**
     * The increments of the robot position by krok in particular smer;
     */
    protected static final int[][] INCREMENT = {
        {+1, +0}, //East
        {+0, +1}, //North
        {-1, +0}, //West
        {+0, -1} //South
    }; //protected static final int [][] INCREMENT =

    private static void initializeStaticAttributes() {
        xMax = znacek.length - 1;
        yMax = znacek[0].length - 1;
        robot = new Robot[xMax + 1][yMax + 1];
        /*//TEST
         System.err.println( "static attributes: xMax=" + xMax + ", yMax=" + yMax +
         " robot=" + robot + "[" + robot.length +
         "][" + robot[0].length + "]" );
         /*-*/
    }//private static void initializeStaticAttributes()
//===== Nonstatic attributes ==================================================
    /**
     * Position of the robot in the area. The bottom left corner of the area has
     * position [0;0].
     */
    protected int xpos, ypos;
    /**
     * The smer, in which is the robot turned
     */
    protected int smer;
    /**
     * The number of robot's color
     */
    protected int color;
    /**
     * The number of miliseconds for waiting before next step/vlevoVbok
     */
    protected int delay;
    /**
     * If pocetZrychleni > 0, the robot will not wait before make next step or
     * turn.
     */
    protected int pocetZrychleni;
    /**
     * The flag indicating if the robot is present int the world.
     */
    protected boolean vAkci;

//===== Constructors ==========================================================
    /**
     * Puts the robot into the standard position in left bottom corner. Robot is
     * turned to the east.
     */
    public Robot() {
        this(0, 0, VYCHOD, 7);
    } //Robot()

    /**
     * Puts the robot into the given position [xr;yr] and turns it to the given
     * smer dir. If there is no robot's world yet one of dimension 10×10 is
     * created.
     *
     * @param xr Horizontal position of the robot.
     * @param yr Vertical position of the robot.
     * @param dirr Direction in whith the robot looks.
     * @param colorr Color of the robot.
     */
    public Robot(int xr, int yr, int dirr, int colorr) {
        /*//TEST
         System.err.println( "Constructor is called: xr=" + xr + ", yr=" + yr +
         ", dirr=" + dirr + ", color=" + color +
         " (xMax=" + xMax + ", yMax=" + yMax + ")." );
         System.err.println( "robot=" + robot );
         /*-*/
        xpos = xr;
        ypos = yr;
        smer = dirr;
        color = colorr;
        delay = 250;
        pocetZrychleni = 0;
        if (znacek == null) {
            pripravDvorek(10, 10);
        }
        if ((xr < 0) || (xr > xMax)
                || (yr < 0) || (yr > yMax)
                || (colorr < 0) || (color > BAREV)
                || (dirr < 0) || (dirr > SMERU)) {
            RobotException e = new RobotException(
                    "Konstruktor obdržel špatné parametry.");
            e.printStackTrace();
            System.exit(1);
        }
        if (znacek[xpos][ypos] == ZED) {
            throw new RobotException(
                    "Robot nemůže být usazen na zeď.");
        }
        if (robot[xpos][ypos] != null) {
            throw new RobotException(
                    "Robot nemůže být umístěn na dvorek - \n"
                    + "na požadovaném místě je již jiný robot.");
        }
        robot[xpos][ypos] = this;
        vAkci = true;
        RWindow.setTitle(TITULEK);
        RWindow.drawRobot(xpos, ypos, smer, color);
    } //Robot( int xr, int yr, int dirr, int colorr )

//===== Public methods ========================================================
    /**
     * Prepares the robots' world according to given layout.
     *
     * @param layout Pattern describing the number of markers on the particular
     * position. Every position is represeted by single charater. 0 represent
     * empty field, 1 field with one marker and so on. If there are more markers
     * as nine, the letter A, B, C ... or a, b, c ... is used.
     */
    public static void pripravDvorek(String[] layout) {
        RWindow.setTitle(TITULEK);
        znacek = RWindow.prepareWindow(layout);
        initializeStaticAttributes();
    }//public static void pripravDvorek( String[] layout )

    public static void pripravDvorek(int nx, int ny) {
        RWindow.setTitle(TITULEK);
        znacek = RWindow.prepareWindow(nx, ny);
        initializeStaticAttributes();
    }//public static void pripravDvorek( String[] markers )

    public static void pripravDvorek(int[][] dvorek) {
        RWindow.setTitle(TITULEK);
        znacek = RWindow.prepareWindow(dvorek);
        initializeStaticAttributes();
    }//public static void pripravDvorek( String[] markers )

    public static void zmenDvorek(String[] layout) {
        for (int x = 0; x <= xMax; x++) {
            for (int y = 0; y <= yMax; y++) {
                if ((robot[x][y] != null) && ((x != 0) || (y != 0))) {
                    throw robot[x][y].new RobotException(
                            "V měněném dvorku smí být pouze nejvýše jeden robot, "
                            + "a to v základní pozici.");
                }//if
            }//for y
        }//for x
        Robot r = robot[0][0];
        if (r != null) {
            RWindow.drawField(0, 0, znacek[0][0]);
        }
        try {
            RWindow.destroyWindow();
        } catch (Exception e) {
            System.err.println("Chyba při změně dvorku!");
            e.printStackTrace();
        }
        znacek = RWindow.prepareWindow(layout);
        initializeStaticAttributes();
        if (r != null) {
            robot[0][0] = r;
            RWindow.drawRobot(0, 0, r.getSmer(), r.getBarva());
        }
    }//public static void zmenDvorek( String[] layout ) 

    public static int getMaxZnacek() {
        return MAX_ZNACEK;
    }

    public static char getZnakZed() {
        return RWindow.getWallChar();
    }

    public int getX() {
        return xpos;
    }

    public int getY() {
        return ypos;
    }

    public int getSmer() {
        return smer;
    }

    public void setSmer(int novýSměr) {
        smer = novýSměr;
    }

    public List<Integer> getVolnéSměry() {
        List<Integer> volnéSměry = new ArrayList<>();

        for (int i = 0; i < Robot.SMERU; i++) {
            if (!jeZedVeSmeru(i)) {
                volnéSměry.add(i);
            }
        }
        
        return volnéSměry;
    }

    public int getBarva() {
        return color;
    }

    public int getCekani() {
        return delay;
    }
    private static final int MIN_DELAY = 0;//10;
    private static final int MAX_DELAY = 9999;

    public void setCekani(int ms) {
        if ((ms < MIN_DELAY) || (ms > MAX_DELAY)) {
            RobotException e = new RobotException(
                    "Požadovaná doba je mimo povolené meze.");
            e.printStackTrace();
            System.exit(1);
        }
        delay = ms;
    }//public void nastavCekani( int ms )

    /**
     * Moves robot to the next field.
     *
     * @throws UnexpectedWallException is throwed if there is a wall before the
     * robot.
     * @throws RobotsColisionException is throwed if there is another robot
     * before the mooving one.
     */
    public void krok() {
        if (pocetZrychleni == 0) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.err.println("\nSpaní vlákna " + Thread.currentThread()
                        + " bylo přerušeno v metodě krok()");
                if (stackTrace) {
                    e.printStackTrace();
                }
            }
        }
        int x2 = xpos + INCREMENT[smer][0];
        int y2 = ypos + INCREMENT[smer][1];
        if (x2 < 0 || x2 > xMax || y2 < 0 || y2 > yMax
                || znacek[x2][y2] == ZED) {
            throw new RobotException(
                    "Robot nemůže udělat krok, protože je před ním zeď.");
        } else if (robot[x2][y2] != null) {
            throw new RobotException(
                    "Robot nemůže udělat krok, protože je před ním jiný robot.");
        } else {
            robot[xpos][ypos] = null;
            RWindow.drawField(xpos, ypos, znacek[xpos][ypos]);
            xpos = x2;
            ypos = y2;
            robot[xpos][ypos] = this;
            RWindow.drawRobot(xpos, ypos, smer, color);
        }
    }//public void krok()

    /**
     * Turns robot 90 degrees clockwise
     */
    public void otoč(int otáčka) {
        if (pocetZrychleni == 0) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.err.println("\nSpaní vlákna " + Thread.currentThread()
                        + " bylo přerušeno v metodě vlevoVbok()");
                if (stackTrace) {
                    e.printStackTrace();
                }
            }
        }
        int novýSměr = smer + otáčka;
        smer = novýSměr < 0 ? SMERU + (novýSměr % SMERU) : (smer + otáčka) % SMERU;
        RWindow.drawRobot(xpos, ypos, smer, color);
    }

    /**
     * Turns robot 90 degrees clockwise
     */
    public void vlevoVbok() {
        otoč(1);
    }//public void vlevoVbok()

    /**
     * Turns robot 90 degrees clockwise
     */
    public void vpravoVbok() {
        otoč(-1);
    }//public void vlevoVbok()

    /**
     * Robot pushes next marker under himself. The field can hold up to 9
     * markers.
     */
    public void poloz() {
        if (++znacek[xpos][ypos] > MAX_ZNACEK) {
            throw new RobotException(
                    "Robot nemůžeč položit značku - pole je již plné.");
        }
    }//public void poloz()

    /**
     * Robot pops marker from the field he is staying on
     */
    public void zvedni() {
        if (--znacek[xpos][ypos] < 0) {
            throw new RobotException(
                    "Robot nemůže zvednout značku - pole je prázdné.");
        }
    }//public void zvedni()

    /**
     * Set the rychle action without waiting before next krok or turn.
     */
    public void rychle() {
        pocetZrychleni++;
    }//public void rychle()

    /**
     * Returns the wait-mode before the last call rychle method.
     */
    public void vratRychlost() {
        pocetZrychleni--;
        if (pocetZrychleni < 0) {
            throw new RobotException(
                    "Příliš mnoho požadavků na vrácení rychlosti.");
        }
    }//public void vratRychlost()

    /**
     * Waits for the given amount of seconds.
     *
     * @param seconds The wainting time in seconds
     */
    public static void cekej(double seconds) {
        try {
            Thread.sleep((long) (1000 * seconds));
            //wait( (long)(1000*seconds) );
        } catch (InterruptedException e) {
            System.err.println("Přerušeno!");
        }
    }//public void cekej( double seconds )

    /**
     * Tests if there is some marker under robot.
     *
     * @return If there is some marker under robot, it returns <b>true</b>.
     * Otherwise it returns <b>false</b>.
     */
    public boolean jeZnacka() {
        return znacek[xpos][ypos] > 0;
    }//public boolean jeZnacka()

    /**
     * Tests if there is some wall before robot.
     *
     * @return If there is some wall before robot, it returns <b>true</b>.
     * otherwise it returns <b>false</b>.
     */
    public boolean jeZed() {
        return jeZedVeSmeru(smer);
    } //boolean jeZed()

    public boolean jeZedVeSmeru(int smer) {
        int x2 = xpos + INCREMENT[smer][0];
        int y2 = ypos + INCREMENT[smer][1];
        return (x2 < 0 || xMax < x2
                || y2 < 0 || yMax < y2
                || znacek[x2][y2] == ZED);
    }

    /**
     * Tests if the robot looks to the East.
     *
     * @return If robot looks to the East, it returns <b>true</b>, otherwise it
     * returns <b>false</b>.
     */
    public boolean jeVychod() {
        return smer == VYCHOD;
    } //boolean jeZed()

    /**
     * Tests if there is some other robot before the testing one.
     *
     * @return If there is some robor before it, it returns <b>true</b>,
     * otherwise it returns <b>false</b>.
     */
    public boolean jeRobot() {
        int x2 = xpos + INCREMENT[smer][0];
        int y2 = ypos + INCREMENT[smer][1];
        return (0 <= x2) && (x2 <= xMax)
                && (0 <= y2) && (y2 <= yMax)
                && robot[x2][y2] != null;
    } //boolean jeRobot()

    /**
     *
     */
    public boolean jeNasazen() {
        return vAkci;
    }

    /**
     *
     */
    public static boolean jeVolne(int x, int y) {
        if (znacek == null) {
            pripravDvorek(10, 10);
        }
        return ((znacek[x][y] != ZED)
                && (robot[x][y] == null));
    }//public static boolean jeVolne( int x, int y )

    /**
     * Tests if there is some other robot before the testing one.
     *
     * @return If there is some robor before it, it returns <b>true</b>,
     * otherwise it returns <b>false</b>.
     */
    public Robot robotPred() {
        return robot[xpos + INCREMENT[smer][0]][ypos + INCREMENT[smer][1]];
    }//public Robot robotPred()

    /**
     * Turn the robot off and zvedni it up from the world.
     */
    public void odstran() {
        if (!vAkci) {
            throw new RobotException(
                    "Robot nemůže být odstraněn, protože není na dvorku.");
        }
        vAkci = false;
        robot[xpos][ypos] = null;
        RWindow.drawField(xpos, ypos, znacek[xpos][ypos]);
    }//public void odstran()

    /**
     * Put the robot on the given field in the world and turn it on.
     */
    public void nasad(int x, int y, int dir) {
        xpos = x;
        ypos = y;
        smer = dir;
        if (vAkci) {
            throw new RobotException(
                    "Robot nemůže být usazen protože na dvorku již je.");
        }
        if (robot[x][y] != null) {
            throw new RobotException(
                    "Robot nemůže být umístěn na dvorek - \n"
                    + "na požadovaném místě je již jiný robot.");
        }
        vAkci = true;
        robot[x][y] = this;
        RWindow.drawRobot(x, y, dir, color);
    }//public void nasad( int x, int y, int dir )

    /**
     * String representation of robot. Return color and position of robot, smer
     * in whith it looks and number of markers on the field it is staing on.
     */
    public String toString() {
        return /*"colorName[" + colorName.length +
                 "],  dirName[" + dirName.length +
                 "],  znacek[" + znacek.length +
                 "][" + znacek[xpos].length + "]\n" +
                 /**/ //        super.toString() + " - " + 
                barva$[color]
                + " robot na pozici [" + xpos + ";" + ypos
                + "] otočený na " + smer$[smer]
                + " a stojící na " + znacek[xpos][ypos] + " značkách je "
                + (vAkci ? "zapnut." : "vypnut.");
    }//public String toString()

//===== Private methods =======================================================
    /**
     * Temporary methods * private void printArray( String t, Object[] a ){
     * System.err.print( t + ": {" ); for( int i=0; i < a.length; i++ )
     * System.err.print( " [" + i + "]=" + a[i] ); System.err.println( " }" );
     * }//private void printArray( String t, Object[] a ) /*-
     */
//===== Exceptions ============================================================
    /**
     * The root clas for exceptions with default message containing information
     * on robot's position and other characteristics.
     */
    public class RobotException extends RuntimeException {

        protected RobotException() {
            super("\n" + Robot.this.toString());
        }//protected RobotException()

        protected RobotException(String msg) {
            super("\n" + Robot.this.toString() + "\n" + msg);
        }//protected RobotException( String msg )
    }//public class RobotException extends RuntimeException

//===== Testing methods =======================================================
    /**
     * The testing proceduro of the package
     */
    public static void main(String[] args) throws Exception {
        boura();
        demo();
    }//public static void main(String[] args) throws Exception

    private static void boura() {
        Robot.pripravDvorek(5, 8);
        Robot r = new Robot(3, 4, 0, 5);
        for (int i = 0; i < 4; i++) {
            int j = 0;
            while (!r.jeZed()) {
                r.krok();
                j++;
            }
            r.vlevoVbok();
            while (j-- > 0) {
                r.poloz();
            }
        }
        r.krok();
    }//private static void boura()

    private static void demo() {
        String[] world$ = {"00000000000",
            "01234567890",
            "0#########0",
            "0#abcdefg#0",
            "0#########0",
            "00000000000"};
        Robot.pripravDvorek(world$);
        Robot[] r = new Robot[8];
        r[0] = new Robot(0, 0, 0, 0);
        r[1] = new Robot(3, 0, 0, 1);
        r[2] = new Robot(10, 0, 1, 2);
        r[3] = new Robot(10, 2, 1, 3);
        r[4] = new Robot(10, 5, 2, 4);
        r[5] = new Robot(6, 5, 2, 5);
        r[6] = new Robot(0, 5, 3, 6);
        r[7] = new Robot(0, 2, 3, 7);
        //Robot rz = new Robot( 1, 2, 3, 7 );
        //Robot rr = new Robot();
        for (int ir = 0; ir < 8; r[ir++].rychle());
        for (int i = 1; i < 200; i++) {
            Robot.cekej(0.1);
            for (int j = 0; j < 8; j++) {
                //System.err.println( "i=" + i + ", j=" + j );
                if (r[j].jeZed()) {
                    r[j].vlevoVbok();
                }
                r[j].krok();
            }
        }
    }//private static void demo()
//==============================================================================
}//public class Robot implements ISmer, IBarva
