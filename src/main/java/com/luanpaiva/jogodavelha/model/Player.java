package com.luanpaiva.jogodavelha.model;

import javafx.scene.paint.Color;

public class Player {

	private String symbol;
	private Color color;

	public Player(String symbol, Color color) {
		super();
		this.symbol = symbol;
		this.color = color;
	}

	public String getSimbolo() {
		return symbol;
	}

	public void setSimbolo(String symbol) {
		this.symbol = symbol;
	}

	public Color getColor() {
		return color;
	}

}
