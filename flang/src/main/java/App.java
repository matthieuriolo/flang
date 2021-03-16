/**
 * Main class which starts the application
 * Because of a bug in maven/javafx the 
 * Main class and the javafx launcher need to separated
 * 
 * For more information please look at
 * https://stackoverflow.com/questions/52653836/maven-shade-javafx-runtime-components-are-missing
 * 
 * @author Matthieu Riolo
 *
 */
public class App {
    public static void main(String[] args) {
    	ch.ffhs.fac.flang.javafx.App.launchJavaFX(args);
    }
}