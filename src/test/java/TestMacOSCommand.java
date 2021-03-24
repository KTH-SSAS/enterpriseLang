package org.mal_lang.enterpriselang.test;

import core.Attacker;
import org.junit.jupiter.api.Test;

public class TestMacOSCommand extends EnterpriseLangTest {
    private static class MacOSCommandModel {

        /*
        attack scenario source: https://us-cert.cisa.gov/ncas/alerts/TA18-086A

                                        
               UserAccount -- OS -- MacOS

        Attacker's entry point: UserAccount.userRights
        */

        public final UserAccount userAccount = new UserAccount("userAccount");
        public final OS os = new OS("os"); // We assume all defenses are disabled for OS. We can enable some of them, then the corresponding attack steps can not be reached.  
        public final MacOS macOS = new MacOS("macOS");

        public MacOSCommandModel() {
            os.addUserAccount(userAccount);
        }
    }

    @Test 
    public void testMacOSCommand() {
            var model = new MacOSCommandModel();

            Attacker attacker = new Attacker();
            attacker.addAttackPoint(model.userAccount.userRights);
            attacker.attack();

            model.os.commandAndScriptingInterpreter.assertCompromisedInstantaneously();
            model.macOS.commandAndScriptingInterpreter.assertCompromisedInstantaneously();
            model.macOS.appleScript.assertCompromisedInstantaneously();
    }  
}