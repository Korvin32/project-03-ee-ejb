package pkg1;

import javax.ejb.Stateless;

@Stateless
public class EJBClass1 {

	public EJBClass1() {
		System.out.println("Stateless Session Bean 'EJBClass1' - constructed!");
	}
	
}
