package org.TicTacToe;

import atlantafx.base.theme.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {

    private String currentText = "X";

    @FXML
    private Button btn00;
    @FXML
    private Button btn01;
    @FXML
    private Button btn02;
    @FXML
    private Button btn10;
    @FXML
    private Button btn11;
    @FXML
    private Button btn12;
    @FXML
    private Button btn20;
    @FXML
    private Button btn21;
    @FXML
    private Button btn22;

    public void importGame() {
        if (!btn00.isDisable()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you wish to import a game? You will loose this current game.");
            alert.setTitle("Confirmation");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {

                FileChooser chooser = new FileChooser();
                chooser.setTitle("Import game");
                chooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("TicTacToe Game File", "*.tttgame")
                );
                File gameFile = chooser.showOpenDialog(Main.getWindow());
                if (gameFile != null) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(gameFile))) {
                        String[] row1 = reader.readLine().split(", ");
                        String[] row2 = reader.readLine().split(", ");
                        String[] row3 = reader.readLine().split(", ");
                        String currentText = reader.readLine();

                        loadGame(row1, row2, row3, currentText);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void exportGame() {
        if (btn00.isDisable()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game already completed");
            alert.setContentText("Unable to export file. The current game has already been finished.");
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Export game");
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("TicTacToe Game File", "*.tttgame")
            );
            chooser.setInitialFileName("game.tttgame");
            File gameFile = chooser.showSaveDialog(Main.getWindow());

            if (gameFile != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(gameFile))) {
                    writer.write(getTextOf(btn00) + ", " + getTextOf(btn01) + ", " + getTextOf(btn02));
                    writer.newLine();
                    writer.write(getTextOf(btn10) + ", " + getTextOf(btn11) + ", " + getTextOf(btn12));
                    writer.newLine();
                    writer.write(getTextOf(btn20) + ", " + getTextOf(btn21) + ", " + getTextOf(btn22));
                    writer.newLine();
                    writer.write(currentText);
                } catch (IOException e) {
                    gameFile.delete();
                    System.out.println("error saving file");
                }
            }
        }
    }

    public void requestNewGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you wish to start a new game?");
        alert.setTitle("Confirmation");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            startNewGame();
        }
    }

    public void startNewGame() {
        currentText = "X";
        btn00.setText("");
        btn01.setText("");
        btn02.setText("");
        btn10.setText("");
        btn11.setText("");
        btn12.setText("");
        btn20.setText("");
        btn21.setText("");
        btn22.setText("");
        setButtonsDisabled(false);
    }

    public void loadGame(String[] row1, String[] row2, String[] row3, String currentText) {
        btn00.setText(reverseGetTextOf(row1[0]));
        btn01.setText(reverseGetTextOf(row1[1]));
        btn02.setText(reverseGetTextOf(row1[2]));
        btn10.setText(reverseGetTextOf(row2[0]));
        btn11.setText(reverseGetTextOf(row2[1]));
        btn12.setText(reverseGetTextOf(row2[2]));
        btn20.setText(reverseGetTextOf(row3[0]));
        btn21.setText(reverseGetTextOf(row3[1]));
        btn22.setText(reverseGetTextOf(row3[2]));
        this.currentText = currentText;
    }

    public void toggle00() {
        if (btn00.getText().isBlank()) {
            btn00.setText(currentText);
            switchCurrentText();
            checkWinner();
        }
    }

    public void toggle01() {
        if (btn01.getText().isBlank()) {
            btn01.setText(currentText);
            switchCurrentText();
            checkWinner();
        }
    }

    public void toggle02() {
        if (btn02.getText().isBlank()) {
            btn02.setText(currentText);
            switchCurrentText();
            checkWinner();
        }
    }

    public void toggle10() {
        if (btn10.getText().isBlank()) {
            btn10.setText(currentText);
            switchCurrentText();
            checkWinner();
        }
    }

    public void toggle11() {
        if (btn11.getText().isBlank()) {
            btn11.setText(currentText);
            switchCurrentText();
            checkWinner();
        }
    }

    public void toggle12() {
        if (btn12.getText().isBlank()) {
            btn12.setText(currentText);
            switchCurrentText();
            checkWinner();
        }
    }

    public void toggle20() {
        if (btn20.getText().isBlank()) {
            btn20.setText(currentText);
            switchCurrentText();
            checkWinner();
        }
    }

    public void toggle21() {
        if (btn21.getText().isBlank()) {
            btn21.setText(currentText);
            switchCurrentText();
            checkWinner();
        }
    }

    public void toggle22() {
        if (btn22.getText().isBlank()) {
            btn22.setText(currentText);
            switchCurrentText();
            checkWinner();
        }
    }

    public void switchCurrentText() {
        if (currentText.equals("X")) {
            currentText = "O";
        } else {
            currentText = "X";
        }
    }

    public void checkWinner() {
        String[][] board = {
                {btn00.getText(), btn01.getText(), btn02.getText()},
                {btn10.getText(), btn11.getText(), btn12.getText()},
                {btn20.getText(), btn21.getText(), btn22.getText()}
        };
        checkBoard(board);
    }

    void checkBoard(String[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].isBlank() &&
                    board[i][0].equals(board[i][1]) &&
                    board[i][1].equals(board[i][2])) {

                endGame(board[i][0]);
                return;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (!board[0][j].isBlank() &&
                    board[0][j].equals(board[1][j]) &&
                    board[1][j].equals(board[2][j])) {

                endGame(board[0][j]);
                return;
            }
        }

        // Check main diagonal
        if (!board[0][0].isBlank() &&
                board[0][0].equals(board[1][1]) &&
                board[1][1].equals(board[2][2])) {

            endGame(board[0][0]);
            return;
        }

        // Check anti-diagonal
        if (!board[0][2].isBlank() &&
                board[0][2].equals(board[1][1]) &&
                board[1][1].equals(board[2][0])) {

            endGame(board[0][2]);
        }
    }

    public void endGame(String winner) {
        System.out.println(winner + " won the game!");
        setButtonsDisabled(true);
        int returnVal = WinnerAlert.show(winner);
        if (returnVal == 1) {
            startNewGame();
        }
    }

    public void setButtonsDisabled(boolean status) {
        btn00.setDisable(status);
        btn01.setDisable(status);
        btn02.setDisable(status);
        btn10.setDisable(status);
        btn11.setDisable(status);
        btn12.setDisable(status);
        btn20.setDisable(status);
        btn21.setDisable(status);
        btn22.setDisable(status);

    }

    public String getTextOf(Button btn) {
        if (btn.getText().isBlank()) {
            return " ";
        } else {
            return btn.getText();
        }
    }

    public String reverseGetTextOf(String str) {
        if (str.equals(" ")) {
            return "";
        }
        return str;
    }

    public void close() {
        Main.close();
    }

    private static class WinnerAlert {
        public static int show(String winner) {
            AtomicInteger returnVal = new AtomicInteger(0);

            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Winner!");
            window.setMinWidth(250);

            Label label = new Label(winner + " HAS WON THE GAME!");

            Button Close = new Button("Close");
            Close.setOnAction(e -> {
                window.close();
            });

            Button newGame = new Button("New game");
            newGame.setOnAction(e -> {
                returnVal.set(1);
                window.close();
            });

            VBox layout = new VBox();
            layout.getChildren().add(label);

            HBox buttons = new HBox(Close, newGame);
            layout.getChildren().add(buttons);

            layout.setAlignment(Pos.CENTER);
            layout.setSpacing(10);
            buttons.setAlignment(Pos.CENTER);
            buttons.setSpacing(15);

            Scene scene = new Scene(layout, 200, 100);

            window.setScene(scene);
            window.setResizable(false);

            window.showAndWait();

            return returnVal.get();
        }
    }
}
