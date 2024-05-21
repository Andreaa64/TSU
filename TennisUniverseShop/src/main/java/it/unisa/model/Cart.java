package main.java.it.unisa.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<ProductBean> products;
	
	public Cart() {
		products = new ArrayList<ProductBean>();
	}
	
	public synchronized void addProduct(ProductBean product) {
		
		/*Product viene considerato un oggetto diverso da prod, quando aggiungiamo per la prima
		 * volta un prodotto dobbiamo controllare se possiamo o meno aggiungere il prodotto.
		*  Una volta aggiunto il proddotto nel carrello entra in gioco il secondo if, che 
		*  controllera se prod è == alla quantità, per capire se possiamo prendere più
		*  scorte dello stesso prodotto.
		*/
		if(product.getQuantita() == product.getQC()) {
			System.out.println("Errore! Scorte esaurite");
			return;
		}
		
		
		 for(ProductBean prod : products)  {
			 
			 if(prod.getIdP() == product.getIdP()) {
				 if(prod.getQuantita() == prod.getQC()) {
						System.out.println("Errore! Scorte esaurite");
						return;
					}
				 prod.incrementaquantita();
				 return;
			 }
		 }
		 
		 product.incrementaquantita();
		 products.add(product);
		
	}
	
	public synchronized void deleteProduct(ProductBean product) {
		for(ProductBean prod : products) {
			if(prod.getIdP() == product.getIdP()) {
				
				System.out.println("If: " + prod.getIdP() + "\n Quantità:" + prod.getQC());
				if(prod.getQC()>1) {
				prod.decrementoquantita();
				}
				else {
				products.remove(prod);
				}
				break;
			}
		}
 	}
	
	public float spesaTot() {
		float somma=0;
		for(ProductBean prod: products) {
			somma = somma + (prod.getPrezzo()*prod.getQC());
		}
		return somma;
	}
	
	public List<ProductBean> getProducts() {
		return  products;
	}
	
    public void elimina() {
    	products.clear();
    }
}
