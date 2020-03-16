


import model.Registry;
import view.Console;
import view.IView;
import view.View;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
    	
        Registry a_registry = new Registry();
        IView a_view = new View();
        Console a_console = new Console();
        a_console.start(a_registry, a_view);

    }
}