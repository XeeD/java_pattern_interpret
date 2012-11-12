package xvodl00;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import robot.cz.Robot;

/**
 * *****************************************************************************
 * Instance třídy <b><code>AtomickaProcedura</code></b> předtavují jednoduché
 * akce, které může námi nasazený robot provést. Jsou jediným způsobem, kterým
 * může námi vytvořený program "komunikovat" navenek - tj. ovládat robota.
 * 
 * Pokud je však spojíme s dalším prvky námi definované gramatiky, tak můžeme
 * vytvářet i složitější programy, jak je ukázáno v testovací metodě v třídě
 * <code>Program</code>.
 *
 * @author Lukáš Voda (xvodl00)
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
    vpravoVbok {
        @Override
        public void proveď() throws Skok {
            Program.getRobot().vpravoVbok();
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
    },
    náhodnýSměrBezZdiNeZpět {
        @Override
        public void proveď() throws Skok {
            Robot robot = Program.getRobot();
            List<Integer> volnéSměry = robot.getVolnéSměry();

            Integer opačnýSměr = (robot.getSmer() + (Robot.SMERU / 2)) % Robot.SMERU;
            if (volnéSměry.size() > 1) {
                // Pokud máme více východů, něž směr zpět, tak nepůjdeme zpět
                volnéSměry.remove(opačnýSměr);
            }
            
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
