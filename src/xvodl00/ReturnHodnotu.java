package xvodl00;

/**
 * 
 *
 * @author Lukáš Voda (xvodl00)
 */
public class ReturnHodnotu implements IPrikaz {

    final private boolean hodnota;

    public ReturnHodnotu(boolean hodnota) {
        this.hodnota = hodnota;
    }

    @Override
    public void proveď() throws Skok {
        if (hodnota) {
            throw new SkokReturnTrue();
        } else {
            throw new SkokReturnFalse();
        }
    }
}
