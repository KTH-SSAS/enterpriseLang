package org.mal_lang.enterpriselang.test;

import core.Attacker;
import org.junit.jupiter.api.Test;

public class  TestTrustedRelationship extends EnterpriseLangTest{
    private static class TrustedThirdpartyModel {
    /*
        ThirdpartySoftware ----- Service ----- OS
      
        Attacker's entry point: thirdpartySoftware.attemptTrustedRelationship
    */
    
    public final ThirdpartySoftware thirdpartySoftware = new ThirdpartySoftware("thirdpartySoftware");
    public final OS os = new OS("os");
	public final Service service = new Service("service");

        public TrustedThirdpartyModel() {
		service.addOs(os);
        }
        
    }
    
    @Test
    public void testTrustedRelationship(){
        var model = new TrustedThirdpartyModel();

        Attacker attacker = new Attacker();
        attacker.addAttackPoint(model.thirdpartySoftware.attemptTrustedRelationship);
        attacker.attack();

	    model.thirdpartySoftware.trustedRelationship.assertCompromisedInstantaneously();
        model.os.validAccounts.assertCompromisedInstantaneously();
    }
}
