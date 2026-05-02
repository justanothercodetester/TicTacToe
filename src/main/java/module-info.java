module org.TicTacToe {
    requires atlantafx.base;
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports org.TicTacToe;
    opens org.TicTacToe to javafx.fxml;
}