package taquin;

public class Main {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                login Login = new login();
                Login.setVisible(true);
                Login.setLocationRelativeTo(null);

            }
        });
    }
}
