import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* This program sorts a given list of beans based on its name, then color,
   then weight, and finally price (see problem #12 in file with Nov 2018 for a more detailed
   description of the problem). The input for these types of problems are in the
   form of data files.
 */

class Bean {
    private String type;
    private String color;
    private double price;
    private int weight;

    public Bean() {
        type = "";
        color = "";
        price = 0.0;
        weight = 0;
    }

    public Bean(String type, String color, double price, int weight) {
        this.type = type;
        this.color = color;
        this.price = price;
        this.weight = weight;
    }

    public String getType() { return type; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public int getWeight() { return weight; }
    public void setType(String type) { this.type = type;}
    public void setColor(String color) {this.color = color; }
    public void setPrice(double price) {this.price = price; }
    public void setWeight(int weight) {this.weight = weight;}

    public String formatBean(){
        return type + " " + color + " " + weight + "g" + " " + formatMoney();
    }

    public String formatMoney() {
        String moneyString = "$"+price;
        if(findNumberOfZeroesAfterDecimal(moneyString) == 1) {
            moneyString += "0";
        }
        return moneyString;
    }

    public int findNumberOfZeroesAfterDecimal(String moneyString) {
        return moneyString.substring(moneyString.indexOf(".")+1).length();
    }
}

public class BeanManager {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("beanmanager.dat"));
        ArrayList<Bean> beans = new ArrayList<Bean>();
        int numberOfDataSets = scan.nextInt();
        scan.nextLine();

        for(int i=0; i<numberOfDataSets; i++) {
            int numberOfBeans = scan.nextInt();
            scan.nextLine();
            for (int j = 0; j < numberOfBeans; j++) {
                String line = scan.nextLine();
                Scanner scanner = new Scanner(line);
                Bean currentBean = new Bean("","",0.0,0);
                currentBean.setType(scanner.next());
                currentBean.setColor(scanner.next());
                String price = scanner.next();
                currentBean.setPrice(Double.parseDouble(price.substring(price.indexOf("$") + 1)));
                String weight = scanner.next();
                currentBean.setWeight(Integer.parseInt(weight.substring(0, weight.indexOf("g"))));
                //System.out.println(currentBean.getType() + " " + currentBean.getColor() + " " + currentBean.getPrice() + " " + currentBean.getWeight());
                beans.add(currentBean);
            }

            for (int k = 0; k < beans.size() - 1; k++) { //name, color, weight, price
                if (!checkIfNamesAreSame(beans.get(k).getType(), beans.get(k + 1).getType())) {
                    int indexToComeFirstN = compareNames(beans, k, k + 1);
                    if (indexToComeFirstN == k + 1) {
                        //swap
                        Bean temp = beans.get(k + 1);
                        beans.set(k + 1, beans.get(k));
                        beans.set(k, temp);
                    }
                } else {
                    //color
                    if (!checkifColorsAreSame(beans.get(k).getColor(), beans.get(k + 1).getColor())) {
                        int indexToComeFirstC = compareColors(beans, k, k + 1);
                        if (indexToComeFirstC == k + 1) {
                            //swap
                            Bean temp = beans.get(k + 1);
                            beans.set(k + 1, beans.get(k));
                            beans.set(k, temp);
                        }
                    } else {
                        //weight
                        if (!checkIfWeightsAreSame(beans.get(k).getWeight(), beans.get(k + 1).getWeight())) {
                            int indexToComeFirstW = compareWeights(beans, k, k + 1);
                            if (indexToComeFirstW == k + 1) {
                                //swap
                                Bean temp = beans.get(k + 1);
                                beans.set(k + 1, beans.get(k));
                                beans.set(k, temp);
                            }
                        } else {
                            //price
                            if (!checkIfPricesAreSame(beans.get(k).getPrice(), beans.get(k + 1).getPrice())) {
                                int indexToComeFirstP = comparePrices(beans, k, k + 1);
                                if (indexToComeFirstP == k + 1) {
                                    //swap
                                    Bean temp = beans.get(k + 1);
                                    beans.set(k + 1, beans.get(k));
                                    beans.set(k, temp);
                                }
                            }
                        }
                    }
                }
            }
            for (int l = 0; l < beans.size(); l++) {
                System.out.print(beans.get(l).formatBean());
                if(l != beans.size()-1) {
                    System.out.println();
                }
            }
            if(i != numberOfDataSets - 1) {
                System.out.println();
                System.out.println();
            }
            beans.clear();
        }
    }

    public static boolean checkIfNamesAreSame(String name1, String name2) {
        if(name1.equals(name2)) {
            return true;
        }
        return false;
    }

    public static boolean checkifColorsAreSame(String color1, String color2) {
        if(color1.equals(color2)) {
            return true;
        }
        return false;
    }

    public static boolean checkIfWeightsAreSame(int weight1, int weight2) {
        if(weight1 == weight2) {
            return true;
        }
        return false;
    }

    public static boolean checkIfPricesAreSame(double price1, double price2) {
        if(price1 == price2) {
            return true;
        }
        return false;
    }

    public static int compareNames(ArrayList<Bean> beans, int index1, int index2) {

        if(beans.get(index1).getType().length() < beans.get(index2).getType().length()) {
            return index1;
        }
        else if(beans.get(index1).getType().length() > beans.get(index2).getType().length()) {
            return index2;
        }
        else {
            for(int i=0; i<beans.get(index1).getType().length(); i++) {
                if(beans.get(index1).getType().charAt(i) < beans.get(index2).getType().charAt(i)) {
                    return index1;
                }
                else if(beans.get(index2).getType().charAt(i) < beans.get(index1).getType().charAt(i)) {
                    return index2;
                }
            }
        }
        return 0;
    }

    public static int compareColors(ArrayList<Bean> beans, int index1, int index2) {

        if(beans.get(index1).getColor().length() < beans.get(index2).getColor().length()) {
            return index1;
        }
        else if(beans.get(index1).getColor().length() > beans.get(index2).getColor().length()) {
            return index2;
        }
        else {
            for(int i=0; i<beans.get(index1).getColor().length(); i++) {
                if(beans.get(index1).getColor().charAt(i) < beans.get(index2).getColor().charAt(i)) {
                    return index1;
                }
                else if(beans.get(index2).getColor().charAt(i) < beans.get(index1).getColor().charAt(i)) {
                    return index2;
                }
            }
        }
        return 0;
    }

    public static int compareWeights(ArrayList<Bean> beans, int index1, int index2) {

        if(beans.get(index1).getWeight() < beans.get(index2).getWeight()) {
            return index1;
        }
        else {
            return index2;
        }
    }

    public static int comparePrices(ArrayList<Bean> beans, int index1, int index2) {

        if(beans.get(index1).getPrice() < beans.get(index2).getPrice()) {
            return index1;
        }
        else {
            return index2;
        }
    }
}