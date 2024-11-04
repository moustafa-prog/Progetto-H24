package Main;

import view.Login;
import Controller.LoginController;
import Modelo.UserDAO;
import Modelo.UserDAOimp;

public class MainApp {
    public static void main(String[] args) {
        @SuppressWarnings("rawtypes")
		UserDAO userDAO = new UserDAOimp();
        Login loginView = new Login();
        new LoginController(loginView, userDAO);

        loginView.setVisible(true);
    }
}
