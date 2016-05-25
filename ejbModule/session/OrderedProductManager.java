package session;

import java.util.List;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.OrderedProduct;

@Stateless
public class OrderedProductManager extends OrderedProductFacade {

    private static final Logger LOG = LoggerFactory.getLogger(OrderedProductManager.class);
    
    public void doDelete() {
        LOG.info("--> doDelete.");
        OrderedProduct orderedProduct = findLastOrderedProduct();
        removeOrderedProduct(orderedProduct);
        LOG.info("<-- doDelete.");
    }

    private OrderedProduct findLastOrderedProduct() {
        LOG.info("--> findLastOrderedProduct()");
        List<OrderedProduct> orderedProducts = findAll();
        OrderedProduct orderedProduct = orderedProducts.get(0);
        LOG.info("<-- findLastOrderedProduct(). orderedProduct=" + orderedProduct);
        return orderedProduct;
    }

    private void removeOrderedProduct(OrderedProduct orderedProduct) {
        LOG.info("--> removeOrderedProduct(). orderedProduct=" + orderedProduct);
        remove(orderedProduct);
        LOG.info("<-- removeOrderedProduct(). orderedProduct=" + orderedProduct);
    }
    
}
