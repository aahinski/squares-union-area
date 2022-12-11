import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        List<Square> squares = new ArrayList<>();
        while(sc.hasNext()) {
            String[] coordinates = sc.nextLine().split(" ");
            squares.add(new Square(
                    Long.parseLong(coordinates[0]),
                    Long.parseLong(coordinates[1]),
                    Long.parseLong(coordinates[2]),
                    Long.parseLong(coordinates[3])
                    ));
        }

        List<SquaresUnionArea.Layer> layers = SquaresUnionArea.divideInLayers(squares);
        System.out.println("Areas");
        layers.stream().forEach(l -> System.out.println(SquaresUnionArea.AreaOfLayer(l)));
    }
}
