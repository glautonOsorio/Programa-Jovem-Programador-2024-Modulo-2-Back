package com.jovemcarro.dto;

public class ModeloDTO {

    private Long idModelo;
    private String nome;
    private FabricanteDTO fabricante;

    public ModeloDTO(Long idModelo, String nome, FabricanteDTO fabricante) {
        this.idModelo = idModelo;
        this.nome = nome;
        this.fabricante = fabricante;
    }

    public ModeloDTO() {
    }

    public Long getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Long idModelo) {
        this.idModelo = idModelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FabricanteDTO getFabricante() {
        return fabricante;
    }

    public void setFabricante(FabricanteDTO fabricante) {
        this.fabricante = fabricante;
    }

}
