import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * @brief class with main uses createcell generate method.
 * Also manages the looks (shows the stage)
 */
public class Grid extends Application
{
    @Override
    public void start(Stage stage)
    {

        final int width = 70;
        final int hight = 60;

        //Size of the table
        int cellRow = Integer.parseInt(getParameters().getNamed().get("rows"));
        int cellCollumn = Integer.parseInt(getParameters().getNamed().get("collumns"));
        double possibility = Double.parseDouble(getParameters().getNamed().get("chance"));
        int speed = Integer.parseInt(getParameters().getNamed().get("speed"));

        //System.console().printf("Hello \n");

        GridPane root = new GridPane();
        Scene scene = new Scene(root,cellCollumn * width,cellRow * hight);


        //NeighbourReader reader = new NeighbourReader();

        CreateCell createCell = new CreateCell();
        createCell.generate(root, cellRow, cellCollumn, possibility, speed);

        stage.setScene(scene);
        stage.show();  
        
    }

    public static void main(String[] args) 
    {
        Application.launch(args);
    }
}
