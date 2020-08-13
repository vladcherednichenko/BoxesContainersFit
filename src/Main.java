import packing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static HashMap<String, Integer> containersWeightMap = new HashMap<>();

    public static void main(String [] args){

        //String[] test = {"hello,6,6,6,1,20", "hello2,20,20,1,1,5","hello3,17,17,1,1,6"};


        containersWeightMap.put("S", 68);
        containersWeightMap.put("M", 106);
        containersWeightMap.put("L", 153);
        containersWeightMap.put("XL", 274);

        // initialization
        List<Container> containers = new ArrayList<>();
        containers.add(new Container("S", 13, 13, 9, 1000000)); // x y z and weight
        containers.add(new Container("M", 17, 17, 9, 1000000)); // x y z and weight
        containers.add(new Container("L", 25, 25, 9, 1000000)); // x y z and weight
        containers.add(new Container("XL", 25, 25, 18, 1000000)); // x y z and weight

        List<BoxItem> products = getBoxListFromArgs(args);

        if(products.size() == 0) {

            //System.out.println("Error parsing input parameters");
            return;

        }

        Packager packager = LargestAreaFitFirstPackager.newBuilder().withContainers(containers).withRotate3D().build();
        List<Container> match = packager.packList(products, 50, System.currentTimeMillis() + 100000);

        printResult(match);

//        for (Container c: match) {
//            System.out.print(c.getName());
//            System.out.print(" ");
//            System.out.println(c.toString());
//        }

    }

    static void printResult(List<Container> containers){

        System.out.print("{\n\"containers\":[");

        for(Container container : containers){

            System.out.print("\n\t{");
            System.out.print("\n\t\"name\":" + "\"" +container.getName()+"\"" + ",");
            // count container weight
            int weight = 0;
            for(Level level: container.getLevels()){

                for (Placement placement : level) {

                    weight += placement.getBox().getWeight() ;

                }

            }
            weight+= containersWeightMap.get(container.getName());
            System.out.print("\n\t\"wg\":" + weight + ",");
            System.out.print("\n\t\"w\":" + container.getWidth() + ",");
            System.out.print("\n\t\"d\":" + container.getDepth());
            System.out.print("\n\t\"h\":" + container.getHeight() + ",");



            //System.out.print("\n\t\"boxes\":[");

//            int pos = 0;
//            int size = 0;


            //for(Level level: container.getLevels()){

//                for (Placement placement : level) {
//                    size ++;
//                }}

//            for(Level level: container.getLevels()){
//
//                for (Placement placement : level) {
//
//                    System.out.print("\n\t\t{");
//                    Box box = placement.getBox();
//                    System.out.print("\n\t\t\"name\":" + box.getName() + ",");
//                    System.out.print("\n\t\t\"w\":" + box.getWidth() + ",");
//                    System.out.print("\n\t\t\"h\":" + box.getHeight() + ",");
//                    System.out.print("\n\t\t\"d\":" + box.getDepth());
//                    System.out.print("\n\t\t}");
//                    if(pos != size-1) System.out.print(",");
////                    System.out.print("pos =" + pos +"size = " + level.size());
//                    pos++;
//
//                }
//
//            }

            //System.out.print("\n\t]}");
            System.out.print("\n\t}");
            if(containers.indexOf(container) != containers.size()-1) System.out.print(",");

        }

        System.out.print("\n]}");

    }

    static List<BoxItem> getBoxListFromArgs(String [] args){

        List<BoxItem> boxList = new ArrayList<>();

        for (String s : args){

            String[] parts = s.split(",");
            if(parts.length != 6){
//                System.out.println("wrong box configuration parameters amount: "+ parts.length);
//                System.out.println(parts[0]);
                System.out.println();
                return new ArrayList<>();
            }

            boxList.add(new BoxItem(new Box(
                    parts[0],
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[4])
                    ), Integer.parseInt(parts[5])));

        }

        return boxList;

    }

}
