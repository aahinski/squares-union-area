import java.util.*;
import java.util.stream.Collectors;

public class SquaresUnionArea {

    public static class SquareInLayerInfo {
        private double x1;
        private double x2;

        public SquareInLayerInfo(double x1, double x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

        public double getX1() {
            return x1;
        }

        public double getX2() {
            return x2;
        }
    }

    public static class Layer {
        double width;
        List<SquareInLayerInfo> squareInLayerInfos;

        public Layer(double width, List<SquareInLayerInfo> squareInLayerInfos) {
            this.width = width;
            this.squareInLayerInfos = squareInLayerInfos;
        }

        public double getWidth() {
            return width;
        }

        public List<SquareInLayerInfo> getSquareInLayerInfos() {
            return squareInLayerInfos;
        }
    }

    public static List<Layer> divideInLayers(List<Square> squares) {
        List<Double> layersY = new ArrayList<>();
        for(Square s : squares) {
            layersY.add(s.getY1());
            layersY.add(s.getY2());
        }
        layersY = layersY.stream().distinct().collect(Collectors.toList());
        Collections.sort(layersY);

        List<Layer> layers = new ArrayList<>();
        for (int i = 0; i < layersY.size() - 1; i++) {
            List<SquareInLayerInfo> squareInLayerInfos = new ArrayList<>();
            for(Square s : squares) {
                if (s.getY1() <= layersY.get(i) && s.getY2() >= layersY.get(i + 1)) {
                    squareInLayerInfos.add(new SquareInLayerInfo(s.getX1(), s.getX2()));
                }
            }
            layers.add(new Layer(layersY.get(i + 1) - layersY.get(i), squareInLayerInfos));
        }

        return layers;
    }

    public static Double AreaOfLayer(Layer layer) {
        double area = 0.;
        if(layer.getSquareInLayerInfos().size() > 1) {
            for (int i = 0; i < layer.getSquareInLayerInfos().size() - 1; i++) {
                double l = layer.getSquareInLayerInfos().get(i).x1;
                double r = layer.getSquareInLayerInfos().get(i).x2;
                double tmp = layer.getSquareInLayerInfos().get(i + 1).x1;

                while (tmp <= r && i < layer.getSquareInLayerInfos().size() - 1) {
                    if (layer.getSquareInLayerInfos().get(i + 1).x2 >= r) {
                        r = layer.getSquareInLayerInfos().get(i + 1).x2;
                    }
                    tmp = layer.getSquareInLayerInfos().get(i + 1).x1;
                    i++;
                }

                area += r - l;
            }
        } else if (layer.getSquareInLayerInfos().size() == 1){
            double l = layer.getSquareInLayerInfos().get(0).x1;
            double r = layer.getSquareInLayerInfos().get(0).x2;
            area = r - l;
        }

        return area * layer.width;
    }
}
