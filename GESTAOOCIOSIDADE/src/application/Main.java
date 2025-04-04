package application;

import ConnectionFactory.ConnectionDatabase; // Adapte para o seu pacote
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Teste de conexão com o banco de dados
            Connection conn = ConnectionDatabase.getConnection();
            if (conn != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
                conn.close(); // Feche a conexão imediatamente após o teste
            } else {
                System.err.println("Falha ao estabelecer conexão com o banco de dados.");
            }

            Parent root = FXMLLoader.load(getClass().getResource("/View/ViewLogin.fxml"));

            Scene scene = new Scene(root, 800, 600);

            primaryStage.setTitle("Login - GESTAO");

            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Erro durante a inicialização: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}