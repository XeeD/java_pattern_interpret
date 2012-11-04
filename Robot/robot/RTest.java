/**
 * Title:        The component for teaching of programming<p>
 * Description:  Project implements the idea of world with robots,
 * which recogizes only a small amount of commands.
 * By teaching the robots teache the pupils themself to program.<p>
 * Copyright:    Copyright (c) Rudolf PECINOVSKÝ<p>
 * Company:      Pecinovský & Partners<p>
 * @author       Rudolf PECINOVSKÝ
 * @version      1.3
 */

package robot;

public class RTest {

    public static void main(String[] args) throws Exception {
        /*
        prepareWindow( 4, 2 );
        System.exit(0);
        /*-*/
        /*+*/
        String[] world$ = { "12345678", "9ABCDEFG", "ij#ij#ji", "########",
                            "12345678", "9ABCDEFG", "ijjijiji", "87654321" };
        /*-*/
        /*+*
        String[] world$ = { "1234", "5678" };
        /*-*/
//        RWindowPův.prepareWindow( world$ );
//        int[][] iw = RWindowNew.prepareWindow( world$ );
//        int ny = world$.length;
        int nx = world$[0].length();
        /*+*/
        for( int c=0;   c < nx;   c++ ) {
            for( int d=0;   d < 4;   d++ ) {
                //RWindowNew.drawRobot( c, d, d, c );
            }
        }
        /*-*/
    }//public static void main(String[] args) throws Exception


//==============================================================================
}//public class RWindow
