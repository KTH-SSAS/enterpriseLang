package org.mal_lang.enterpriselang.test;

import core.Attacker;
import org.junit.jupiter.api.Test;

public class TestCloudServiceAccess extends EnterpriseLangTest{
    private static class CloudServiceAccessModel {
    /*
        ThirdpartySoftware ----- Service
      
        Attacker's entry point: CloudService.applicationAccessToken
    */
    
    public final CloudService cloudService = new CloudService("cloudService");
    //public final OS os = new OS("os");
	public final Service service = new Service("service");

        public CloudServiceAccessModel() {
		//service.addOs(os);
        }
        
    }
    
    @Test
    public void TestCloudServiceAccess(){
        var model = new CloudServiceAccessModel();

        Attacker attacker = new Attacker();
        attacker.addAttackPoint(model.cloudService.applicationAccessToken);
        attacker.attack();

	    model.cloudService.accessCloudBasedServiceResources.assertCompromisedInstantaneously();
    }
}
