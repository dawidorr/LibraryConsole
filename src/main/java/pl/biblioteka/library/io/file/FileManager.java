package pl.biblioteka.library.io.file;

import pl.biblioteka.library.model.Library;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}
