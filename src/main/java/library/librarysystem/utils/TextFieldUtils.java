package library.librarysystem.utils;

import javafx.scene.control.TextFormatter;

public class TextFieldUtils {
    public static TextFormatter<Integer> integerTextFormatter() {
        return new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*"))
                return null;
            else
                return c;
        }
        );
    }
}
