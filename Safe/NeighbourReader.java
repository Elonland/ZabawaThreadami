import javafx.scene.paint.Color;

public class NeighbourReader 
{

    Cell[] colorArray = new Cell[4];

    public Color getNeighbourColor(Cell[] tab)
    {
        colorArray = tab;
        //po ogarnieciu sasiadow wyjmij z nich kolory wylicz srednia i gotowe!   
        double averageRed = 0, averageBlue = 0, averageGreen = 0;
        for(int i = 0; i < 4; i++)
        {
            averageRed += ((Color)(colorArray[i].getRectangle().getFill())).getRed();
            averageGreen += ((Color)(colorArray[i].getRectangle().getFill())).getGreen();
            averageBlue += ((Color)(colorArray[i].getRectangle().getFill())).getBlue();
        }
        averageRed = averageRed/4;
        averageBlue = averageBlue/4;
        averageGreen = averageGreen/4;

        Color c = new Color(averageRed, averageGreen, averageBlue, 1);
        return c;
    }

}