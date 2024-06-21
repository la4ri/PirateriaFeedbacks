package com.example.pirateriafeedbacks.model;


import com.google.gson.annotations.SerializedName;

public class Cardapio {
    @SerializedName("id_cardapio")
    private int id_cardapio;
    @SerializedName("tituloCardapio")
    private String tituloCardapio;
    @SerializedName("descricaoCardapio")
    private String descricaoCardapio;
    @SerializedName("tipoItemCardapio")
    private String tipoItemCardapio;

    public Cardapio(int id_cardapio, String tituloCardapio, String descricaoCardapio, String tipoItemCardapio) {
        this.id_cardapio = id_cardapio;
        this.tituloCardapio = tituloCardapio;
        this.descricaoCardapio = descricaoCardapio;
        this.tipoItemCardapio = tipoItemCardapio;
    }

    public int getId_cardapio() {
        return id_cardapio;
    }

    public void setId_cardapio(int id_cardapio) {
        this.id_cardapio = id_cardapio;
    }

    public String getTituloCardapio() {
        return tituloCardapio;
    }

    public void setTituloCardapio(String tituloCardapio) {
        this.tituloCardapio = tituloCardapio;
    }

    public String getDescricaoCardapio() {
        return descricaoCardapio;
    }

    public void setDescricaoCardapio(String descricaoCardapio) {
        this.descricaoCardapio = descricaoCardapio;
    }

    public String getTipoItemCardapio() {
        return tipoItemCardapio;
    }

    public void setTipoItemCardapio(String tipoItemCardapio) {
        this.tipoItemCardapio = tipoItemCardapio;
    }
}

