package xvodl00;

import java.util.List;

/**
 * Tento výčtový typ obsahuje atomické funkce, které jsou definovány
 * v gramatice jazyka. Každá z těchto funkcí vrací hodnotu <code>true</code>
 * nebo <code>false</code>.
 * 
 * Tyto funcke lze využít buď přímo v jednotlivých konstruktech programu
 * (Podmínka, While) nebo při definici vlastních funkcí.
 * 
 * @author Lukáš Voda (xvodl00)
 */
public enum AtomickaFunkce implements IFunkce {

    // jeZnačka | jeZed | jeRobot | jeVychod
    jeZnačka {
        @Override
        public boolean jePravda() throws Skok {
            return Program.getRobot().jeZnacka();
        }
    },
    jeZeď {
        @Override
        public boolean jePravda() throws Skok {
            return Program.getRobot().jeZed();
        }
    },
    jeRobot {
        @Override
        public boolean jePravda() throws Skok {
            return Program.getRobot().jeRobot();
        }
    },
    jeVýchod {
        @Override
        public boolean jePravda() throws Skok {
            return Program.getRobot().jeVychod();
        }
    },
    neníZnačka {
        @Override
        public boolean jePravda() throws Skok {
            return !Program.getRobot().jeZnacka();
        }
    },
    jeRozcestí {
        @Override
        public boolean jePravda() throws Skok {
            List<Integer> volnéSměry = Program.getRobot().getVolnéSměry();
            return volnéSměry.size() > 2;
        }
    }
}
