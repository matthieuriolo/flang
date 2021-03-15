module flang {
    requires javafx.fxml;
    requires javafx.controls;
    opens ch.ffhs.fac.flang.javafx to javafx.graphics, javafx.fxml;
    exports ch.ffhs.fac.flang.javafx;
}