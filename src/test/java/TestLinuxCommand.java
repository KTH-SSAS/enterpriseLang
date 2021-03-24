package org.mal_lang.enterpriselang.test;

import core.Attacker;
import org.junit.jupiter.api.Test;

public class TestLinuxCommand extends EnterpriseLangTest {
    private static class LinuxCommandModel {

        /*
        attack scenario source: https://us-cert.cisa.gov/ncas/alerts/TA18-086A

                                        
               UserAccount -- OS -- Linux 

        Attacker's entry point: UserAccount.userRights
        */

        public final UserAccount userAccount = new UserAccount("userAccount");
        public final OS os = new OS("os"); // We assume all defenses are disabled for OS. We can enable some of them, then the corresponding attack steps can not be reached.  
        public final Linux linux = new Linux("linux");

        public LinuxCommandModel() {
            os.addUserAccount(userAccount);
        }
    }

    @Test 
    public void testLinuxCommand() {
            var model = new LinuxCommandModel();

            Attacker attacker = new Attacker();
            attacker.addAttackPoint(model.userAccount.userRights);
            attacker.attack();

            model.os.commandAndScriptingInterpreter.assertCompromisedInstantaneously();
            model.linux.commandAndScriptingInterpreter.assertCompromisedInstantaneously();
            model.linux.unixShell.assertCompromisedInstantaneously();
    }  
}