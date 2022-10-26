import javafx.scene.layout.GridPane;

public class CreateCell
{

    final int width = 70;
    final int hight = 60;

    private int x = 0;
    private int y = 0;

    private int rowCount = 0,collumnCount = 0;

    Cell cell;

    Thread thread;

    //private Rectangle[] rectangles;

    private boolean recSaved = false;

    int cellRow,cellCollumn;

    

    public void generate(GridPane root,int cellRow, int cellCollumn, double possibility, int speed)
    {
        //rectangles = new Rectangle[cellRow * cellCollumn];

        this.cellRow = cellRow;
        this.cellCollumn = cellCollumn;

        Cell[][] tab = new Cell[cellCollumn][cellRow];

        for(int i = 0; i < cellRow * cellCollumn; i++)
        {
            if(x >= cellCollumn * width)
            {
                y += hight; 
                x = 0;
            }
            
            rowCount = y / hight;
            collumnCount = x / width;
            cell = new Cell(root, x, y, rowCount, collumnCount);
            //Making array that contains all rectangles created.
            //rectangles[i] = cell.getRectangle();
            //System.console().printf("Hello \n");
            //Giving parameters to Cell class
            cell.getPosibilityAndSpeed(possibility, speed);

            tab[collumnCount][rowCount] = cell;
            
        
            x += width;
            
        }
        x = 0;
        y = 0;
        for(int k = 0; k < cellRow; k++)
        {

            for(int i = 0; i < cellCollumn; i++)
            {
                Cell left, right, up, down;
                left = tab[(x - 1 + cellCollumn) % cellCollumn][y];
                right = tab[(x + 1) % cellCollumn][y];
                up = tab[x][(y - 1 + cellRow) % cellRow];
                down = tab[x][(y + 1) % cellRow];

                tab[i][k].setNeighbours(left, right, up, down);
                thread = new Thread(tab[i][k]);
                thread.start();
                x++;
            }
            x = 0;
            y++;
                
        }

        //po wygenerowaniu kazdemu cell z tablicy dajemy mu sasiadow
        //i uruchamiamy thready.
        recSaved = true;
    }

    public int getCellRow()
    {
        return cellRow;
    }

    public int getCellCollumn()
    {
        return cellCollumn;
    }

    public boolean isSaved()
    {
        return recSaved;
    }

    //public Rectangle[] getArray()
    //{
        //return rectangles;
    //}
    
}
