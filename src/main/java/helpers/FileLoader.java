package helpers;

import javafx.stage.FileChooser;

import java.io.File;

public class FileLoader {

    private static File file;
    private static FileChooser fileChooser;

    public static String loadPhoto() {
        fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterImg =
                new FileChooser.ExtensionFilter("Изображения", "*.JPG", "*.jpg", "*.JPEG", "*.jpeg", "*.PNG", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterImg);

        File file = fileChooser.showOpenDialog(null);
        return file.toURI().toString();
    }

    public static File saveExcel() {
        fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterExcel =
                new FileChooser.ExtensionFilter("Таблица", "*.xls");
        fileChooser.getExtensionFilters()
                .addAll(extFilterExcel);


        file = fileChooser.showSaveDialog(null);
        return file;
    }

    public static File saveWord() {
        fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterWord =
                new FileChooser.ExtensionFilter("Документ", "*.doc");
        fileChooser.getExtensionFilters()
                .addAll(extFilterWord);

        file = fileChooser.showSaveDialog(null);
        return file;
    }

    public static File saveXML() {
        fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterXML =
                new FileChooser.ExtensionFilter("XML", "*.xml");
        fileChooser.getExtensionFilters()
                .addAll(extFilterXML);

        file = fileChooser.showSaveDialog(null);
        return file;
    }
}
