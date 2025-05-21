package application;

import ConnectionFactory.ConnectionDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {

    private static Stage stage;
    private static Scene mainScene;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        try {
            Connection conn = ConnectionDatabase.getConnection();
            if (conn != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
                conn.close();
            } else {
                System.err.println("Falha ao estabelecer conexão com o banco de dados.");
            }

            Parent root = FXMLLoader.load(getClass().getResource("/View/ViewLogin.fxml"));
            mainScene = new Scene(root, 800, 600);
            primaryStage.setTitle("Login - GESTAO");

            Image icon = new Image(getClass().getResourceAsStream("/img/icone.jpg"));
            primaryStage.getIcons().add(icon);

            primaryStage.setScene(mainScene);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Erro durante a inicialização: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void TelaHome() throws IOException {
        FXMLLoader fxmlHome = new FXMLLoader();
        fxmlHome.setLocation(Main.class.getResource("/View/ViewPrincipal.fxml"));
        Parent telaHome = fxmlHome.load();
        mainScene = new Scene(telaHome);
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return stage;
    }
}