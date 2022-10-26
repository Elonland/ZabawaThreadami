
import java.util.Random;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;

/**
 * @brief it is responsible for the creation of rectangles and changing colours.
 */
public class Cell extends Rectangle implements Runnable
{
    private static final int width = 70;
    private static final int hight = 60;
    private Rectangle rectangle;
    private Color c;

    private double possibility;
    private int speed;

    Random random = new Random();
    private double r,g,b;

    int x,y;

    GridPane grid;

    
    /**
     * @brief constructor imidiatelly creates rectangle and fills it with random colour.
     * @param grid method uses it to assign rectangles there and to manage layout. 
     * @param x coordinate
     * @param y coordinate
     * @param row row in which rectangle is created
     * @param collumn collumn in which rectangle is created
     */
    public Cell(GridPane grid ,int x, int y, int row, int collumn)
    {
        this.grid = grid;

        this.x = x;
        this.y = y;

        
        r = random.nextDouble(); 
        g = random.nextDouble();
        b = random.nextDouble();
        c = new Color(r, g, b, 1);

        rectangle = new Rectangle(x,y,width,hight);
        
        rectangle.setFill(c);
        GridPane.setConstraints(rectangle, collumn, row); //collumn, row
        grid.getChildren().add(rectangle); 
        
    }

    public void getPosibilityAndSpeed(double possibility, int speed)
    {
        this.possibility = possibility;
        this.speed = speed;
    }

    public Rectangle getRectangle()
    {
        return rectangle;
    }

    Cell[] tab = new Cell[4];
    /**
     * @brief saves found neighbours by createcell
     */
    public void setNeighbours(Cell left, Cell right, Cell up, Cell down)
    {
        tab[0] = left;
        tab[1] = right;
        tab[2] = up;
        tab[3] = down;
    }

    public Cell[] getNeighbours()
    {
        return tab;
    }


    //Tworzymy obiekt neighbourReadera
    NeighbourReader read = new NeighbourReader();

    /**
     * @brief finds colour, it can generate colour when requirements are met or it can
     * get the average colour of its neighbours.
     * @param id used for control, to know which thread is working
     */
    synchronized public void changeColor(int id)
    {
        int a = random.nextInt(10);
        if(a <= (possibility * 10) -1) // it must be 0.x where x < 10
        {
            r = random.nextDouble(); 
            g = random.nextDouble();
            b = random.nextDouble();
            c = new Color(r, g, b, 1);
        }
        else
        {
            //Pobierz kolory sasiadow
            c = read.getNeighbourColor(tab);

        }
        Platform.runLater( () -> {rectangle.setFill(c);}); 
        System.console().printf("thread: " + id + " changed color \n");
    }

    
    /**
     * @brief used by thread used for changing colour.
     */
    @Override
    public void run()
    {
        int id = x/width + 1 + (y/hight * 6); 
        
        double multiplier = random.nextDouble() + 0.5;
        int i = 0;
        try
        {
            while(i < 10)
            {
                System.console().printf("thread: " + id + "\n");
            
                synchronized (grid){  changeColor(id);  }
             
                Thread.sleep((int)(speed * multiplier));
                i++;
            }  
        }
        catch(InterruptedException exception)
        {
            System.console().printf("interrupted \n");
        }
        
    }
}
