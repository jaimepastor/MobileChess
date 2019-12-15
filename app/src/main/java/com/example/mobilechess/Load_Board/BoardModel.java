package com.example.mobilechess.Load_Board;

public class BoardModel {

    private String id;
    private String name;
    private String fen;

    public BoardModel(String id, String name, String fen){
        this.id = id;
        this.name = name;
        this.fen = fen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }
}
