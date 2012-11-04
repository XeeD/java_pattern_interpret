package robot.cz;


/**
 * The interface describing the main set of robot's methods.
 */
public interface IKarel {

    void krok();
    void vlevoVbok();
    void poloz();
    void zvedni();

    boolean jeZnacka();
    boolean jeZed();
    boolean jeVychod();
    boolean jeRobot();

}
