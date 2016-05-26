package pkg1;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EJBClass1 {

    private static final Logger LOG = LoggerFactory.getLogger(EJBClass1.class);
    
	public EJBClass1() {
		LOG.info("Stateless Session Bean 'EJBClass1' - constructed!");
	}
	
}
