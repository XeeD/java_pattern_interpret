package xvodl00;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import robot.cz.Robot;

/**
 * *****************************************************************************
 * Instance třídy <b><code>AtomickáProcedura</code></b> předtavují ...
 *
 * @author jméno autora
 * @version 0.00.000, 0.0.2008
 */
public enum AtomickaProcedura implements IProcedura {

    krok {
        @Override
        public void proveď() throws Skok {
            Program.getRobot().krok();
        }
    },
    vlevoVbok {
        @Override
        public void proveď() throws Skok {
            Program.getRobot().vlevoVbok();
        }
    },
    poloz {
        @Override
        public void proveď() throws Skok {
            Program.getRobot().poloz();
        }
    },
    zvedni {
        @Override
        public void proveď() throws Skok {
            Program.getRobot().zvedni();
        }
    },
    náhodnýSměr {
        @Override
        public void proveď() throws Skok {
            Random random = new Random();

            int otoček = random.nextInt(4);
            int směr = random.nextInt(2);

            String methodName;
            Class robotClass = Program.getRobot().getClass();

            if (směr == 1) {
                methodName = "vlevoVbok";
            } else {
                methodName = "vpravoVbok";
            }

            try {
                Method method = robotClass.getMethod(methodName);
                for (int i = 0; i < otoček; i++) {
                    method.invoke((Object) Program.getRobot());
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            }
        }
    },
    náhodnýSměrBezZdi {
        @Override
        public void proveď() throws Skok {
            Robot robot = Program.getRobot();
            List<Integer> volnéSměry = robot.getVolnéSměry();
            
            Random random = new Random();
            int novýSměr = volnéSměry.get(random.nextInt(volnéSměry.size()));
            
            robot.setSmer(novýSměr);
        }
    };
//== KONSTANTNÍ ATRIBUTY TŘÍDY =================================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===================================================
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==============================================
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ================================================
//== PŘÍSTUPOVÉ METODY ATRIBUTŮ TŘÍDY ==========================================
//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY ===========================================
//##############################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =============================================
//== ABSTRAKTNÍ METODY =========================================================
//== PŘÍSTUPOVÉ METODY INSTANCÍ ================================================
//== OSTATNÍ NESOUKROMÉ  METODY INSTANCÍ =======================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY ===========================================
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ========================================
//== VNOŘENÉ A VNITŘNÍ TŘÍDY ===================================================
//== TESTOVACÍ METODY A TŘÍDY ==================================================
//
//    /***************************************************************************
//     * Testovací metoda.
//     */
//    public static void test()
//    {
//         PosloupnostPříkazů instance = new PosloupnostPříkazů();
//    }
//    /** @param args Parametry příkazového řádku - nepoužité. */
//    public static void main( String... args ) { test(); }
}
