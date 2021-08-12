package com.luanpaiva.jogodavelha.controller;

import java.util.Arrays;
import java.util.List;

import com.luanpaiva.jogodavelha.model.Player;
import com.luanpaiva.jogodavelha.util.Alerts;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameController {

	private final Player PLAYER_1 = new Player("X", Color.BLACK);
	private final Player PLAYER_2 = new Player("O", Color.DARKRED);
	private Player jogadorAtual;
	private int vitoriaPLAYER_1;
	private int vitoriaPLAYER_2;
	private int round;
	private boolean useImage = true;
	private boolean playComputer = false;
	private Player p[] = new Player[9];

	@FXML
	private Label posicao0;
	@FXML
	private Label posicao1;
	@FXML
	private Label posicao2;
	@FXML
	private Label posicao3;
	@FXML
	private Label posicao4;
	@FXML
	private Label posicao5;
	@FXML
	private Label posicao6;
	@FXML
	private Label posicao7;
	@FXML
	private Label posicao8;
	@FXML
	private Label ptsJ1;
	@FXML
	private Label ptsJ2;
	@FXML
	private Button btnNovoJogo;
	@FXML
	private Button btnZerarPontos;
	@FXML
	private ToggleButton btnTrocarSimbolo;
	@FXML
	private ToggleButton btnJogarContraIA;

	private List<Label> listLabels;

	@FXML
	void initialize() {

		listLabels = Arrays.asList(posicao0, posicao1, posicao2, posicao3, posicao4, posicao5, posicao6, posicao7,
				posicao8);

		ptsJ1.setText(Integer.toString(vitoriaPLAYER_1));
		ptsJ2.setText(Integer.toString(vitoriaPLAYER_2));
		jogadorAtual = PLAYER_1;
		newGame();
	}

	private void jogar(Label posicaoJogada) {

		switch (posicaoJogada.getId()) {
		case "posicao0":
			p[0] = jogadorAtual;
			break;
		case "posicao1":
			p[1] = jogadorAtual;
			break;
		case "posicao2":
			p[2] = jogadorAtual;
			break;
		case "posicao3":
			p[3] = jogadorAtual;
			break;
		case "posicao4":
			p[4] = jogadorAtual;
			break;
		case "posicao5":
			p[5] = jogadorAtual;
			break;
		case "posicao6":
			p[6] = jogadorAtual;
			break;
		case "posicao7":
			p[7] = jogadorAtual;
			break;
		case "posicao8":
			p[8] = jogadorAtual;
			break;
		}

		if (useImage) {
			if (jogadorAtual == PLAYER_1) {
				if (jogadorAtual.getSimbolo().equals("X")) {
					posicaoJogada.setGraphic(new ImageView("images/img1_100x100.png"));
				} else {
					posicaoJogada.setGraphic(new ImageView("images/img3_100x100.png"));
				}
			}

			if (jogadorAtual == PLAYER_2) {
				if (jogadorAtual.getSimbolo().equals("O")) {
					posicaoJogada.setGraphic(new ImageView("images/img2_100x100.png"));
				} else {
					posicaoJogada.setGraphic(new ImageView("images/img4_100x100.png"));
				}
			}
		} else {
			posicaoJogada.setText(jogadorAtual.getSimbolo());
		}

		if (winner(jogadorAtual)) {
			if (jogadorAtual == PLAYER_1) {
				ImageView image = jogadorAtual.getSimbolo().equals("X") ? new ImageView("images/img1_50x50.png")
						: new ImageView("images/img3_50x50.png");
				if (useImage) {
					Alerts.showAlerts(image, "Jogo da Velha", "Vitória!", "O jogador 1 é o vencedor!",
							AlertType.INFORMATION);
				} else {
					Alerts.showAlerts(null, "Jogo da Velha", "Vitória!", "O jogador 1 é o vencedor!",
							AlertType.INFORMATION);
				}
				vitoriaPLAYER_1++;
				ptsJ1.setText(Integer.toString(this.vitoriaPLAYER_1));
				endGame();
			} else if (jogadorAtual == PLAYER_2) {
				ImageView image = jogadorAtual.getSimbolo().equals("O") ? new ImageView("images/img2_50x50.png")
						: new ImageView("images/img4_50x50.png");
				if (useImage) {
					Alerts.showAlerts(image, "Jogo da Velha", "Vitória!", "O jogador 2 é o vencedor!",
							AlertType.INFORMATION);
				} else {
					Alerts.showAlerts(null, "Jogo da Velha", "Vitória!", "O jogador 2 é o vencedor!",
							AlertType.INFORMATION);
				}
				vitoriaPLAYER_2++;
				ptsJ2.setText(Integer.toString(this.vitoriaPLAYER_2));
				endGame();
			}
		} else {
			round++;
			if (round <= 9) {
				jogadorAtual = (jogadorAtual == PLAYER_1) ? PLAYER_2 : PLAYER_1;
				if (playComputer) {
					computer();
				}
			} else {
				Alerts.showAlerts(null, "Jogo da Velha", null, "Deu Velha!", AlertType.INFORMATION);
				endGame();
			}
		}

	}

	private boolean winner(Player player) {

		if ((p[0] == player && p[1] == player && p[2] == player) || (p[3] == player && p[4] == player && p[5] == player)
				|| (p[6] == player && p[7] == player && p[8] == player)
				|| (p[0] == player && p[3] == player && p[6] == player)
				|| (p[1] == player && p[4] == player && p[7] == player)
				|| (p[2] == player && p[5] == player && p[8] == player)
				|| (p[0] == player && p[4] == player && p[8] == player)
				|| (p[6] == player && p[4] == player && p[2] == player)) {
			return true;
		}
		return false;
	}

	private void computer() {
		if (jogadorAtual == PLAYER_2) {
			if (!jogadaIA()) {
				int randomPlay = (int) (Math.random() * 9);
				switch (randomPlay) {
				case 0:
					if (posicao0.getGraphic() == null && posicao0.getText().equals(""))
						jogarNaPosicao(posicao0);
					else
						computer();
					break;
				case 1:
					if (posicao1.getGraphic() == null && posicao1.getText().equals(""))
						jogarNaPosicao(posicao1);
					else
						computer();
					break;
				case 2:
					if (posicao2.getGraphic() == null && posicao2.getText().equals(""))
						jogarNaPosicao(posicao2);
					else
						computer();
					break;
				case 3:
					if (posicao3.getGraphic() == null && posicao3.getText().equals(""))
						jogarNaPosicao(posicao3);
					else
						computer();
					break;
				case 4:
					if (posicao4.getGraphic() == null && posicao4.getText().equals(""))
						jogarNaPosicao(posicao4);
					else
						computer();
					break;
				case 5:
					if (posicao5.getGraphic() == null && posicao5.getText().equals(""))
						jogarNaPosicao(posicao5);
					else
						computer();
					break;
				case 6:
					if (posicao6.getGraphic() == null && posicao6.getText().equals(""))
						jogarNaPosicao(posicao6);
					else
						computer();
					break;
				case 7:
					if (posicao7.getGraphic() == null && posicao7.getText().equals(""))
						jogarNaPosicao(posicao7);
					else
						computer();
					break;
				case 8:
					if (posicao8.getGraphic() == null && posicao8.getText().equals(""))
						jogarNaPosicao(posicao8);
					else
						computer();
					break;
				}
			}
		}
	}

	private boolean jogadaIA() {

		Player player = PLAYER_2;

		for (int i = 0; i < 2; i++) {

			if (p[0] == player && p[1] == player && p[2] == null) {
				jogarNaPosicao(posicao2);
				return true;
			} else if (p[3] == player && p[4] == player && p[5] == null) {
				jogarNaPosicao(posicao5);
				return true;
			} else if (p[6] == player && p[7] == player && p[8] == null) {
				jogarNaPosicao(posicao8);
				return true;
			} else if (p[0] == player && p[3] == player && p[6] == null) {
				jogarNaPosicao(posicao6);
				return true;
			} else if (p[1] == player && p[4] == player && p[7] == null) {
				jogarNaPosicao(posicao7);
				return true;
			} else if (p[2] == player && p[5] == player && p[8] == null) {
				jogarNaPosicao(posicao8);
				return true;
			} else if (p[2] == player && p[1] == player && p[0] == null) {
				jogarNaPosicao(posicao0);
				return true;
			} else if (p[5] == player && p[4] == player && p[3] == null) {
				jogarNaPosicao(posicao3);
				return true;
			} else if (p[8] == player && p[7] == player && p[6] == null) {
				jogarNaPosicao(posicao6);
				return true;
			} else if (p[6] == player && p[3] == player && p[0] == null) {
				jogarNaPosicao(posicao0);
				return true;
			} else if (p[7] == player && p[4] == player && p[1] == null) {
				jogarNaPosicao(posicao1);
				return true;
			} else if (p[8] == player && p[5] == player && p[2] == null) {
				jogarNaPosicao(posicao2);
				return true;
			} else if (p[0] == player && p[4] == player && p[8] == null) {
				jogarNaPosicao(posicao8);
				return true;
			} else if (p[6] == player && p[4] == player && p[2] == null) {
				jogarNaPosicao(posicao2);
				return true;
			} else if (p[8] == player && p[4] == player && p[0] == null) {
				jogarNaPosicao(posicao0);
				return true;
			} else if (p[2] == player && p[4] == player && p[6] == null) {
				jogarNaPosicao(posicao6);
				return true;
			} else if (p[0] == player && p[2] == player && p[1] == null) {
				jogarNaPosicao(posicao1);
				return true;
			} else if (p[3] == player && p[5] == player && p[4] == null) {
				jogarNaPosicao(posicao4);
				return true;
			} else if (p[6] == player && p[8] == player && p[7] == null) {
				jogarNaPosicao(posicao7);
				return true;
			} else if (p[0] == player && p[6] == player && p[3] == null) {
				jogarNaPosicao(posicao3);
				return true;
			} else if (p[1] == player && p[7] == player && p[4] == null) {
				jogarNaPosicao(posicao4);
				return true;
			} else if (p[2] == player && p[8] == player && p[5] == null) {
				jogarNaPosicao(posicao5);
				return true;
			} else if (p[0] == player && p[8] == player && p[4] == null) {
				jogarNaPosicao(posicao4);
				return true;
			} else if (p[6] == player && p[2] == player && p[4] == null) {
				jogarNaPosicao(posicao4);
				return true;
			}
			player = PLAYER_1;
		}
		return false;
	}

	private void newGame() {

		listLabels.stream().forEach(label -> {
			label.setText("");
			label.setGraphic(null);
			label.setDisable(false);
		});

		round = 1;

		for (int i = 0; i < p.length; i++) {
			p[i] = null;
		}

		if (playComputer) {
			computer();
		}

	}

	@FXML
	void newGame(MouseEvent event) {
		newGame();
	}

	private void endGame() {

		listLabels.stream().forEach(label -> {
			label.setDisable(true);
		});

		jogadorAtual = (jogadorAtual == PLAYER_1) ? PLAYER_2 : PLAYER_1;
	}

	@FXML
	void resetPoints(MouseEvent event) {
		vitoriaPLAYER_1 = 0;
		ptsJ1.setText(Integer.toString(this.vitoriaPLAYER_1));
		vitoriaPLAYER_2 = 0;
		ptsJ2.setText(Integer.toString(this.vitoriaPLAYER_2));
	}

	@FXML
	void btnJogarContraIA(MouseEvent event) {
		btnJogarContraIA.setText((btnJogarContraIA.getText().equals("JOG VS MAQ") ? "JOG VS JOG" : "JOG VS MAQ"));
		playComputer = (playComputer == true) ? (playComputer = false) : (playComputer = true);
	}

	public void jogarNaPosicao(Label posicaoJogada) {
		if (posicaoJogada.getGraphic() == null && posicaoJogada.getText().equals("")) {
			posicaoJogada.setTextFill(jogadorAtual.getColor());
			jogar(posicaoJogada);
		}
	}

	@FXML
	void changeSymbol(MouseEvent event) {
		this.PLAYER_1.setSimbolo((PLAYER_1.getSimbolo().toString().equals("X")) ? "♠" : "X");
		this.PLAYER_2.setSimbolo((PLAYER_2.getSimbolo().toString().equals("O")) ? "♥" : "O");
	}

	@FXML
	void posicao0(MouseEvent event) {
		jogarNaPosicao(posicao0);
	}

	@FXML
	void posicao1(MouseEvent event) {
		jogarNaPosicao(posicao1);
	}

	@FXML
	void posicao2(MouseEvent event) {
		jogarNaPosicao(posicao2);
	}

	@FXML
	void posicao3(MouseEvent event) {
		jogarNaPosicao(posicao3);
	}

	@FXML
	void posicao4(MouseEvent event) {
		jogarNaPosicao(posicao4);
	}

	@FXML
	void posicao5(MouseEvent event) {
		jogarNaPosicao(posicao5);
	}

	@FXML
	void posicao6(MouseEvent event) {
		jogarNaPosicao(posicao6);
	}

	@FXML
	void posicao7(MouseEvent event) {
		jogarNaPosicao(posicao7);
	}

	@FXML
	void posicao8(MouseEvent event) {
		jogarNaPosicao(posicao8);
	}

}
