/**
 * Title:        The component for teaching of programming<p>
 * Description:  Project implements the idea of world with robots,
 * which recogizes only a small amount of commands.
 * By teaching the robots teache the pupils themself to program.<p>
 * Copyright:    Copyright (c) Rudolf PECINOVSKÝ<p>
 * Company:      Pecinovský & Partners<p>
 * 
 * @author       Rudolf PECINOVSKÝ
 * @version      1.05.001,  2.4.2003
 */

package robot;

import java.io.FileNotFoundException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.net.URL;
//import java.awt.MediaTracker;


/**
 *  The class responding for painting robots' world and his parts on the screen.
 *  This class knows how many markers is possible to put on the single field,
 *  and which colors of robots are allowed. 
 *  The class acts as singleton - it allows only one robots' world to exist.
 */
public class RWindow
{
//  == SOUKROME KONSTANTY ========================================================

    /** The path to the files with look of the field
     *  with particular number of markers.   */
    private static final String fieldsIconDirectory = "IMGF/";


    /** The path to the files with look of the robot
     *  turned in particular direction.     */
    private static final String robotsIconDirectory = "IMGR/";


    /** Array of file names of icons representing particular numbers
     *  of markers on the field.  */
    private static final String [] markerIconFileName =
    {
        "0.gif",
        "1.gif",
        "2.gif",
        "3.gif",
        "4.gif",
        "5.gif",
        "6.gif",
        "7.gif",
        "8.gif",
        "9.gif",
        "C0.gif",
        "C1.gif",
        "C2.gif",
        "C3.gif",
        "C4.gif",
        "C5.gif",
        "C6.gif",
        "C7.gif",
        "C8.gif",
        "C9.gif",
        "BrickWall.gif"
    };//protected static final String [] markerIconFileName =


    /** Characters representing robot paint in particular color.
     *  Size of this array determines the posible amount of different robots. */
    private static final String[] robotsColorChar =
    {
        "K", // 000 = Black
        "B", // 001 = Blue
        "R", // 010 = Red
        "M", // 011 = Magenta
        "G", // 100 = Green
        "C", // 101 = Cyan
        "Y", // 110 = Yellow
        "W"  // 111 = White
    };//protected static String[] robotsColorChar =


    /** The final parts of file name with look of the robot
     *  turned in particular direction.  */
    private static final String [] directionFileName =
    {
        "_EAST.gif",
        "_NORTH.gif",
        "_WEST.gif",
        "_SOUTH.gif"
    };//protected static String [] directionFileName =


//  == VEREJNE KONSTANTY =========================================================

    /** Number of posible robot colors. */
    public static final int NUM_OF_COLORS = robotsColorChar.length;


    /** Number of possible robot directions.   */
    public static final int DIR_NUM = directionFileName.length;


    /** Max allowed number of markers on the single field.  */
    public static int MAX_MARKERS = markerIconFileName.length - 2;


    /** The number represented presence of wall on the given field.  */
    public static final int WALL = -1;



//  == ATRIBUTY TRIDY ============================================================

    /** Icons with the looks of field with particular number of markers.  */
    private static ImageIcon[] fieldLook;

    /** The radix for conversion of character to the number of markers.  */
    private static int RADIX = (MAX_MARKERS < 10)  ? 10  :  MAX_MARKERS+1;


    /** Title of window with robot world.  */
    private static String title = "World of robots";


    /** Character representing the wall in the robot's world.  */
    private static char WALL_CHAR = '#';



//  == ATRIBUTY INSTANCI =========================================================
//  == PRISTUPOVE METODY ATRIBUTU TRIDY ==========================================

    /**
     *  Returns the number of markers putable on the single field.
     *  @return The number of markers putable on the single field.
     */
    public static final int getMaxMarkers()
    {
        return MAX_MARKERS;
    }//public static final getMaxMarkers()


    /**
     *  Returns the char for representing the wall in the string pattern.
     *  @returns The char for representing the wall in the string pattern.
     */
    public static final char getWallChar()
    {
        return WALL_CHAR;
    }//public static final char getWallChar()


    /**
     *  Sets the char for representing the wall.
     *  @param c The set char.
     */
    public static final void setWallChar( char c )
    {
        WALL_CHAR = c;
    }//public static final char getWallChar()


    /**
     *  Returns the title of the window with the robots' world.
     *  @returns The title of the window with the robots' world.
     */
    public static final String getTitle()
    {
        return title;
    }//public static final String getTitle()


    /**
     *  Sets the title of the window with the robots' world.
     *  @param title The set title of the window with the robots' world.
     */
    public static final void setTitle( String title )
    {
        RWindow.title = title;
    }//public static final void setTitle( String title )



//== OSTATNI METODY TRIDY ======================================================

    /**
     *  Convert the char representing the numbers of markers
     *  or wall to the coresponding integer.
     *  @param c The converted char. If there are more then nine markers on
     *           the field, they are signed by letters A, B, C ... or
     *           a, b, c ... The character must respect the max possible
     *           number of markers on one field.
     *  @returns The number of markers or flag of wall.
     */
    public static final int c2i ( char c )
    {
        int m;
        if(c == WALL_CHAR)
        {
            m = WALL;
        }
        else
        {
            m = Character.digit( c, RADIX );
            if( (m < 0) || (m > MAX_MARKERS) )
            {
                throw new RuntimeException(
                    "Unexpected character \"" + c + "\"" );
            }
        }
        return m;
    }//public static final int c2i ( char c )


    /**
     *  Draw on the given field the icon representing the wall or the given
     *  amount of markers. The Wall cannot be placed on the field with robot.
     *  @param x Horizontal position of the field.
     *  @param y Vertical position of the field.
     *  @param m Number of markers on the field.
     */
    public static void drawField( int x, int y, int m )
    {
        if( m == WALL )
        {
            if( robot[x][y] )
            {
                throw new RuntimeException (
                    "The wall cannot be placed on position with robot." );
            }
            m = MAX_MARKERS+1;
        }
        field[x][y].setIcon( fieldLook[m] );
        robot[x][y] = false;
        /*+old*
        fieldLook[m].paintIcon( window, g,
            x * ICON_SIZE + xL,  (yMax - y - 1)* ICON_SIZE + yT );
        /*-*/
    }//public static void drawField( int x, int y, int m )


    /**
     *  Draw on the given field the icon representing the robot
     *  int the given position turned in the given color and direction.
     *  @param x Horizontal position of the robot.
     *  @param y Vertical position of the robot.
     *  @param d Direction of the robot (0=East, 1=North, 2=West, 3=South)
     *  @param c Color of the robot (0=black, 1=blue, 2=red, 3=magenta,
     *           4=green, 5=cyan, 6=yellow, 7=white).
     */
    public static void drawRobot( int x, int y, int d, int c )
    {
        field[x][y].setIcon( robotLook[c][d] );
        robot[x][y] = true;
    }//public static void drawField( int x, int y, int m )




    /**
     *  Convert the vector with pattern strings to the array of int.
     *  @param s The vector containtng one string for every row of robots'
     *           world. The last string represent the row with index 0.
     *           The indexes in the horizontal direction correspond to the
     *           indexis of corresponding characters in the strings.
     */
    public static int[][] string2int( String[] s )
    {
        int ny = s.length;
        int nx = s[0].length();
        int[][] ii = new int[ nx ][ ny ];
        for( int y=0;   y < ny;   y++ )
        {
            if( s[y].length() != nx )
            {
                throw new RuntimeException(
                    "The given representation of robots' world " +
                    "is not a rectangular array.\n" +
                    "All rows must have the same number of fields - \n" +
                    "all pattern strings must have the same length."     );
            }
            for( int x=0;   x < nx;   x++ )
            {
                ii[x][y] = c2i(s[ny-y-1].charAt(x));
            }
        }
        return ii;
    }//public static int[][] string2int( String[] s )



    /**
     *  Convert array of chars representing the numbers of markers
     *  or wall to array of coresponding integers.
     *  @param c The converted array of chars. The char c[0][0] represents
     *           the field with coordinates [0;0] int the robots' world.
     */
    public static int[][] char2int( char[][] c )
    {
        int ni, nj;
        ni = c.length;
        int[][] ii = new int[ni][];
        for( int i=0;   i < ni;   i++ )
        {
            nj = c[i].length;
            ii[i] = new int[nj];
            for( int j=0;   j < nj;   j++ )
            {
                ii[i][j] = c2i( c[i][j] );
            }
        }
        return ii;
    }//public static int[][] char2int( char[][] c )



    /**
     *  If there is no window with Robots' world yet, it prepares the one.
     *  If there is one throws the IllegalStateException.
     *
     *  @param world Holds the numbers of markes on particular fields.
     *               The only allowed characters are numbers
     *               and the character representing the wall.<p>
     *               Every char represents the field with the same index.
     *  @returns Integer array containing the number of markers
     *           on particular field or -1 if there is a wall on the field.
     *  @throws  IllegalStateException if some robots' world exists yet.
     *  @see WALL_CHAR
     */
    public static int[][] prepareWindow( char[][] world )
    {
        return prepareWindow( char2int(world) );
    }//public static void prepareWindow( char[][] world )



    /**
     *  If there is no window with Robots' world yet, prepare the one.
     *  If there is one throws the IllegalStateException.
     *
     *  @param world Holds the numbers of markes on particular fields.
     *               The only allowed characters are numbers
     *               and the character representing the wall.<p>
     *         Every string in the field represents single row of the world.
     *         The first string (string with index 0) represetns the top row
     *         of the world, i.e. the row with tne meximal y.
     *  @returns Integer array containing the number of markers
     *           on particular field or -1 if there is a wall on the field.
     *  @throws  IllegalStateException if some robots' world exists yet.
     *  @see WALL_CHAR
     */
    public static int [][] prepareWindow( String[] world )
    {
        return prepareWindow( string2int( world ) );
    }//public static void prepareWindow( String[] world )



    /**
     *  If there is no window with Robots' world yet, it prepares the one.
     *  If there is one throws the IllegalStateException.
     *  @param iw Holds the numbers of markes on particular fields.
     *            The wall is represented as -1;
     *  @returns Reference to its parameter.
     *  @throws  IllegalStateException if some robots' world exists yet.
     */
    public static int[][] prepareWindow( int[][] world )
    {
        if( window != null )
        {
            throw new IllegalStateException (
                "Second attempt to build the robots world - " +
                "the robots world can be build only once." );
        }
        xMax  = world.length;
        yMax  = world[0].length;
        robot = new boolean[xMax][yMax];

        for( int i=1;   i < xMax;   i++ )
        {
            if( world[i].length != yMax )
            {
                throw new RuntimeException(
                   "The given representation of robots' world " +
                   "is not a rectangular array." );
            }
        }
        prepareFields();
        prepareRobots();
        window = new RFrame( world );
        window.setResizable(false);
        return world;
    }//public static void prepareWindow( int[][] world )



    /**
     *  If there is no window with Robots' world yet, it prepares the one.
     *  If there is one throws the IllegalStateException.
     *  @param iw holds the numbers of markes on particular fields.
     *  @returns Reference to its parameter.
     *  @throws  IllegalStateException if some robots' world exists yet.
     */
    public static int[][] prepareWindow( int xMax, int yMax )
    {
        return prepareWindow( new int[xMax][yMax] );
    }//public static int[][] prepareWindow( int xMax, int yMax )



    /**
     *  Destroys the existing robots' world. The must be free of robots.
     *  @throws IllegalStateException if there are some robots in the wold.
     */
    public static void destroyWindow() 
    {
        for( int x = 0;   x < xMax;   x++ )
            for ( int y = 0;  y < yMax;  y++ )
                if( robot[x][y] ) 
                {
                    throw new IllegalStateException (
                        "You cannot destroy the robots' world " +
                        "until it is free of robots." );
                }
        window.dispose();
        window = null;
    }//public static void destroyWindow() 



//===== Preparing the fields' looks ===========================================

    /** Pomocna pro ladeni nasledujici metody *
    private static String str( int i ) {
        if(      i == MediaTracker.ABORTED )    return "MediaTracker.ABORTED";
        else if( i == MediaTracker.ERRORED )    return "MediaTracker.ERRORED";
        else if( i == MediaTracker.COMPLETE)    return "MediaTracker.COMPLETE";
        else return "Unknown value: " + i;
    }//private String str( int i )  /*-*/
    
    /**
     *  Prepare icons representing the field looks.
     */
    protected static void prepareFields()
    {
        //File   file;
        //String path;
        fieldLook = new ImageIcon[markerIconFileName.length];
        try
        {
            Class cl = new RWindow().getClass();
            URL url;
//            ImageIcon im = null;
//			FileOutputStream fos = null;
//			InputStream is = null;
            
            for( int m=0;   m < markerIconFileName.length;   m++ )
            {
                //ImageIcon icon = null;
                String name = fieldsIconDirectory + markerIconFileName[m];
                
//                url = ClassLoader.getSystemResource( name );
//                url = cl.getResource( name );
                url = cl.getClassLoader().getResource( name );
                
//                System.out.println( "name=" + name + ";   Url=" + url );
                if( url == null )
                    throw  new FileNotFoundException(
                           "File \"" + fieldsIconDirectory + markerIconFileName[m] +
                           "\" not found" );
                fieldLook[m] = new ImageIcon( url, name );
//                im = fieldLook[m];
//                System.out.println("Icon: " + im + 
//                    " [" + im.getIconWidth() + "×" + im.getIconHeight() +
//                    "] - Status: " + str(im.getImageLoadStatus()) );
//                if( true /*+* im.getImageLoadStatus() != MediaTracker.COMPLETE /*-*/ ) {
//                	is = cl.getResourceAsStream( name );
//                	System.out.println( "Input stream: " + is );
//					fos = new FileOutputStream( "C:\\" + name );
//					System.out.println( "FileOutputStream: " + fos );
//					while( (is!=null) && (is.available() > 0)) {
//                        fos.write( is.read() );
//                    }
//                    is.close(); fos.close();
//                }
            }//for color
        }
        catch( /*FileNotFound*/Exception e )
        {
            throw new RuntimeException ( 
                "Files representing looks of fields without robot " +
                "has not been found." );
        }
    }//protected static void prepareFields()



//===== Preparing the robots' looks ===========================================

    /**
     *  Array with icon for every possible color and every possible direction
     *  of robot.
     */
    static ImageIcon[][] robotLook;


    /**
     *  Prepare icons representing the robot looks.
     *
     */
    protected static void prepareRobots()
    {
		String name0, name;
		robotLook = new ImageIcon[NUM_OF_COLORS][DIR_NUM];
//        ImageIcon im = null;
//        FileOutputStream fos = null;
//        InputStream is = null;

		try
		{
			Class cl = new RWindow().getClass();
			URL url;
			for( int color=0;   color < NUM_OF_COLORS;   color++ )
			{
				name0 = robotsIconDirectory + robotsColorChar[color];
				for( int dir=0;  dir < DIR_NUM;  dir++ )
				{
                    name = name0 + directionFileName[dir];
					url = cl.getClassLoader().getResource( name );
//                    url = cl.getResource( name2 );
					if( url == null )
                    {
                        System.out.println( "name=" + name + ";   Url=" + url );
						throw  new FileNotFoundException(
							   "File \"" + name + directionFileName[dir] +
							   "\" not found" );
                    }
					robotLook[color][dir] = new ImageIcon( url );
//                    im = robotLook[color][dir];
//                    System.out.print("Icon: " + im );
//                    System.out.println( 
//                        " [" + im.getIconWidth() + "×" + im.getIconHeight() +
//                        "] - Status: " + str(im.getImageLoadStatus()) );
                }//for dir
            }//for color
        }catch( FileNotFoundException e )
        {
            throw new RuntimeException ( 
                "Files representing looks of robots has not been found." );
        }
    }//protected static void prepareRobots()



//===== Private static attributes =================================================

     /**
      *  Dimension of constructed world.
      */
     private static int xMax, yMax;


    /**
     *  Window in whith the robots world is build.
     */
    private static RFrame window;


    /**
     *  Field remembering positions with robots.
     */
    private static boolean[][] robot;


    /**
     *  Every field is represented by one label;
     */
    private static JLabel[][] field;


    /**
     *  The offset of area for painting from frame borders
     */
    private static int xL=4, xR=4, yT=23, yB=4;


    /**
     *  The size of ikon representing single field
     */
    private static final int ICON_SIZE   = 32;



//== KONSTRUKTORY ==============================================================

    /**
     *  This class has no accesible constructor.
     */
    private RWindow() {}
    
    
    
//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY A IMPLEMENTOVANYCH ROZHRANI ===
//== OSTATNI PREKRYTE METODY RODICOVSKE TRIDY ==================================
//== VLASTNI METODY INSTANCI ===================================================
//== SOUKROME A POMOCNE METODY =================================================
//===== Window creating class ==================================================
//== VNORENE A VNITRNI TRIDY ===================================================

    /**
     *  Class responding for creating of window for the robot world.
     */
    private static class RFrame extends JFrame
    {

        /**
         *  Constructor creating the empty window
         */
        RFrame( int[][] world )
        {
            super( title );

            Container c = getContentPane();
            c.setLayout( new GridLayout( yMax, xMax ) );
            setSize( xMax*ICON_SIZE + xL + xR,
                     yMax*ICON_SIZE + yT + yB );
            setVisible( true );

            /* Test the thickness of the border.
             * If the thickness is not equal to the expected one,
             * the field can not precisely fit into prepared area.
             */
            Insets insets = getInsets();
            if( insets.left != xL  || insets.right  != xR  ||
                insets.top  != yT  || insets.bottom != yB     )
            {
                xL = insets.left;
                xR = insets.right;
                yT = insets.top;
                yB = insets.bottom;
                Dimension d = new Dimension( xMax*ICON_SIZE + xR + xL,
                                             yMax*ICON_SIZE + yT + yB );
                /*+TEST+*
                int xSize = xMax*ICON_SIZE + xL + xR;
                int ySize = yMax*ICON_SIZE + yT + yB;
                System.err.println( 
                    "New size: xSize=" + xSize + ", ySize=" + ySize +
                    ", xL=" + xL + ", xR=" + xR + ", yT=" + yT + ", yB=" + yB );
                /*-*/
                setSize( d );
            }//if other insets
/*+*/
            addWindowListener(
                new WindowAdapter()
                {
                    public void windowClosing( WindowEvent e )
                    {
                        window = null;
                        if( !getClass().getClassLoader().toString()
                            .startsWith("bluej") )
                                System.exit(0);
                    }
                });
/*=*
           //Equivalent of the previous part acceptable from JDK 1.3
            setDefaultCloseOperation( EXIT_ON_CLOSE );
/*-*/            
            field = new JLabel[xMax][yMax];
            for( int y=yMax-1;   y >= 0;     y-- )
                for( int x=0;        x < xMax;   x++ )
                {
                    int m = world[x][y];
                    if(m == WALL) m = MAX_MARKERS+1;
                    c.add( field[x][y] = new JLabel( fieldLook[m] ) );
                }
            show();
        }//public RFrame()

    }//protected class RFrame extends JFrame


//  == TESTY =====================================================================

      /**
       *  Solved exceptions:
       *   Second attempt to build the robots world
       *   Files representing looks of robots has not been found.
       *   Files representing looks of robots has not been found.
       *   The given representation of robots world is not a rectangular array.
       *   Unexpected character on the field
       *
       *  Task to test:
       */

      /**
       * The main testing procedure of the package
       */
      public static void main(String[] args) throws Exception
      {
          prepareWindow( 4, 2 );
          System.out.println("Window (4,2) prepared.");
          destroyWindow();
          System.out.println("Window (4,2) destroyed.");

          String[] world$ = { "12345678", "9ABCDEFG", "ij#ij#ji", "########",
                              "12345678", "9ABCDEFG", "ijjijiji", "87654321" };

          prepareWindow( world$ );
          System.out.println("Window world$ prepared.");
          destroyWindow();
          System.out.println("Window world$ destroyed.");

          int[][] iw = string2int( world$ );
          //int ny = world$.length;
          int nx = world$[0].length();

          prepareWindow( iw );//world$ );
          for( int c=0;   c < nx;   c++ )
          {
              for( int d=0;   d < 4;   d++ )
              {
                  drawRobot( c, d, d, c );
              }
          }
          for( int c=0;   c < nx;   c++ )
          {
              for( int d=0;   d < 4;   d++ )
              {
                  drawField( c, d, c+d );
              }
          }
      }//public static void main(String[] args) throws Exception
    
    

//==============================================================================
}//public class RWindow
