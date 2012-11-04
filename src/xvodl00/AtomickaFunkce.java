/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xvodl00;

import java.util.List;

/**
 *
 * @author XeeD
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
