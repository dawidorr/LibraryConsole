package pl.biblioteka.library.app;

public class LibraryApp {
    public static void main(String[] args) {
        final String APP_NAME = "Biblioteka";
        System.out.println(APP_NAME);
        LibraryControl libControl = new LibraryControl();
        libControl.controlLoop();
        System.out.println("yes");
    }
}
