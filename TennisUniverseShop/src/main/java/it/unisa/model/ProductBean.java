package main.java.it.unisa.model;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	int IdP;
	String descrizione;
	String colore;
	int prezzo;
	int quantita;  // quantità nel DataBase
	String tipo;
	String marca;
	String img;
	int qC;   //qC = quantità nel carello

	public ProductBean() {
		IdP=0;
		tipo="";
		marca="";
		colore = "";
		descrizione = "";
		quantita = 0;
		prezzo=0;
		qC = 0;
	}

	public void incrementaquantita() {
		this.qC++;
	}
	
	public void decrementoquantita() {
		this.qC--;
	}
	
	public int getQC () {
		return this.qC;
	}
	
	public int getIdP(){
		return IdP;
	}
	public void setIdP(int id){
		this.IdP=id;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String t) {
		this.tipo = t;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String d) {
		this.descrizione = d;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	

	public void setColore(String col){
		this.colore=col;
		
	}
	
	public void setImg(String i){
		this.img = i;
	}
	
	public String getColore(){
		return colore;
	}
	
	public void setMarca(String m){
		this.marca=m;
	}
		
	public String getMarca(){
		return marca;
	}

	public String getImg(){
		return img;
	}
	
	
	@Override
	public String toString() {
		return " (" + IdP + "), " + prezzo + " " + marca +" "+ colore + ". " + descrizione;
	}

}
