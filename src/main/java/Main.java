
import visualization.GUI;


public class Main{

    public static void main(final String[] args) throws Exception {
        try {
            GUI.main(null);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}