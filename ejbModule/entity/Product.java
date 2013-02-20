/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author zagorod
 */
@Entity
@Table(name = "product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description")})
public class Product implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    
    @JoinTable(name = "similarity", joinColumns = {
        @JoinColumn(name = "primary_product_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "similar_product_id", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> similarProducts;

    @ManyToMany(mappedBy = "similarProducts", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> primaryProducts;

    public Product() {
	}
    
    public Product(String name, String description) {
    	this.name = name;
    	this.description = description;
    }

    public void addSimilarProduct(Product similarProduct) {
    	System.out.println("[Product] in addSimilarProduct(): product to add  : " + similarProduct.toString() + "; this = " + this.toString());

    	getSimilarProducts().add(similarProduct);
    	similarProduct.getSimilarProducts().add(this);
    	
    	System.out.println(" ");
    	System.out.println("[Product] in addSimilarProduct(): similarProducts = " + similarProducts);
    	System.out.println("[Product] in addSimilarProduct(): going to persist data!");
    }
    
    public void becomeSimilarProductOf(Product primaryProduct) {
    	getPrimaryProducts().add(primaryProduct);
    	primaryProduct.getPrimaryProducts().add(this);
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Product> getSimilarProducts() {
        return similarProducts;
    }

    public void setSimilarProducts(Set<Product> similarProducts) {
        this.similarProducts = similarProducts;
    }

    public Set<Product> getPrimaryProducts() {
        return primaryProducts;
    }

    public void setPrimaryProducts(Set<Product> primaryProducts) {
        this.primaryProducts = primaryProducts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Product[ id=" + id + " ]";
    }
    
}
