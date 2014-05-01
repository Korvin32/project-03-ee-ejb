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
import javax.persistence.Query;

import entity.Address;
import entity.Customer;
import exception.NoSuchCustomerException;
import exception.WronPasswordException;

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
    
    public Customer checkLoginData(String login, String password) throws NoSuchCustomerException, WronPasswordException {
    	Query query = em.createNamedQuery("Customer.findByLogin", Customer.class);
    	query.setParameter("login", login);
    	
    	Customer customer;
    	
    	try {
        	customer = (Customer) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("[CustomerManager] - ERROR - Exception: " + e.getMessage());
			throw new NoSuchCustomerException("No such user registered!: '" + login + "'");
		}
    	
    	if (customer != null && !customer.getPassword().equals(password)) {
    		throw new WronPasswordException("Wrong password for '" + login + "' entered!");
    	}
    	return customer;
    }
    
    public Customer updateCustomerData(Customer customer) {
    	System.out.println("in updateCustomerData(): " + customer);
    	return em.merge(customer);
    }
}
