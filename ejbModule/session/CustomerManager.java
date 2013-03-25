package session;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Address;
import entity.Customer;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CustomerManager {

    @PersistenceContext(unitName = "webApp_001PU")
    private EntityManager em;
    
    @Resource
    private SessionContext context;
	
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int registerCustomer(Customer customer, Address address) {
    	//TODO check for username, email and addresses duplicates!
    	try {
        	//add customer
        	em.persist(customer);
        	//add address
        	address.setCustomer(customer);
        	em.persist(address);
        	return customer.getId();
		} catch (Exception e) {
			context.setRollbackOnly();
			return 0;
		}
    }
}
