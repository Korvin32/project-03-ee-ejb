/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author zagorod
 */
@Embeddable
public class OrderedProductPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_order")
    private int customerOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product")
    private int product;

    public OrderedProductPK() {
    }

    public OrderedProductPK(int customerOrder, int product) {
        this.customerOrder = customerOrder;
        this.product = product;
    }

    public int getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(int customerOrder) {
        this.customerOrder = customerOrder;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) customerOrder;
        hash += (int) product;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderedProductPK)) {
            return false;
        }
        OrderedProductPK other = (OrderedProductPK) object;
        if (this.customerOrder != other.customerOrder) {
            return false;
        }
        if (this.product != other.product) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderedProductPK[ customerOrder=" + customerOrder + ", product=" + product + " ]";
    }
    
}
