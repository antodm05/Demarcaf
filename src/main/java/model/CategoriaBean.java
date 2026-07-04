package model;

import java.io.Serializable;

public class CategoriaBean implements Serializable {

    private int idCategoria;
    private String nome;

    public CategoriaBean() {

    }

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    
    
}