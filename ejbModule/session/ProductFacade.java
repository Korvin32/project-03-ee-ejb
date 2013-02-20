/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Product;

/**
 *
 * @author zagorod
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "webApp_001PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public void addSimilarProduct(Product primProduct, Product simProduct) {
    	primProduct.addSimilarProduct(simProduct);
    	getEntityManager().merge(primProduct);
    }
}
