/**
 * Title:        Substition of the enumerated type of robots' directions.<p>
 * Description:  Supply the four main directions together with a field
 *               of this directions allowed the iteration over the directions
 *               and with the constant DIRECTIONS.<p>
 * Copyright:    Copyright (c) Rudolf PECINOVSKÝ<p>
 *
 * @author Rudolf PECINOVSKÝ
 * @version 1.4
 */
package robot;


/**
 *  Substitues enumerated type of robots' directions.
 *  Supplies the four main directions together with a field
 *  of this directions allowed the iteration over the directions
 *  and with the constant MAX_DIRECTIONS.
 */
public interface IDirection {

    //All atributes are implicitly public static final

    int EAST  = 0;
    int NORD  = 1;
    int WEST  = 2;
    int SOUTH = 3;

    int DIRECTIONS = 4;

    String[] direction$ = { "EAST", "NORD", "WEST", "SOUTH" };

}//public interface IDirection
