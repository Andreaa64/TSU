package main.java.it.unisa.model;

import java.sql.Date;

public class User {
	
	String Email;
	String Username;
	String Cognome;
	String Nome;
	String DataNascita;
	String Psw;
	
	public User() {
		Email = "";
		Username="";
		Cognome="";
		Nome="";
		DataNascita=null;
		Psw="";
	}

	public void setEmail(String e) {
		this.Email=e;
	}
	
	public void setUsername(String u) {
		this.Username = u;
	}
	
	public void setCognome(String c) {
		this.Cognome=c;
	}
	
	public void setNome(String n) {
		this.Nome=n;
	}
	
	public void setDataNascita(String d) {
		this.DataNascita=d;
	}
	
	public void setPsw(String p) {
		this.Psw=p;
	}
	
	public String getEmail() {
		return this.Email;
	}
	
	public String getUsername() {
		return this.Username;
	}
	
	public String getCognome() {
		return this.Cognome;
	}
	
	public String getNome() {
		return this.Nome;
	}
	
	public String getDataNascita() {
		return this.DataNascita;
	}
	
	public String getPsw() {
		return this.Psw;
	}
	
}
