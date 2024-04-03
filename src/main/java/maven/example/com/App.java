package maven.example.com;

import maven.example.com.tools.Manager;

public class App {
    public static void main(String[] args) {
        Manager.initialization(App.class.getResourceAsStream("/CHANGE_THIS.properties"));
        System.out.println("Good!");
    }
}
