module com.factorial.swirl_factorial {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;

    opens com.factorial.swirl_factorial to javafx.fxml;
    exports com.factorial.swirl_factorial;
}