package library.librarysystem.ui;

import java.io.IOException;

public interface LibWindow {
	void init() throws IOException;
	boolean isInitialized();
	void isInitialized(boolean val);
}
