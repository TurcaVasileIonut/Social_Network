package utils;

import java.awt.Desktop;
import java.io.File;

import javafx.stage.Stage;

public class FileChooser {

    private Desktop desktop = Desktop.getDesktop();

    public File getFilePath(final Stage stage) {
        stage.setTitle("File Chooser Sample");

        final javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();

        File file = fileChooser.showOpenDialog(stage);
        return file;
    }
}