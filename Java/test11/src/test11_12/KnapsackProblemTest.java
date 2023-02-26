package test11_12;

public class KnapsackProblemTest {
    public static void main(String[] args) {
        int[] weight = {1, 5, 4, 8, 6};
        int[] value = {30,40,50,80,50};
        KnapsackProblem knapsackProblem = new KnapsackProblem(weight, value, 15);
        knapsackProblem.solve();
        knapsackProblem.showAttemp();
        knapsackProblem.showItems();
    }
}

class KnapsackProblem {
    int itemCount;
    int[] weight;
    int[] value;
    int maxWeight;
    int[][] attemp;
    int[][] path;

    public KnapsackProblem(int[] weight, int[] value, int maxWeight) {
        if (weight.length != value.length) {
            throw new RuntimeException("weight与value长度不匹配");
        }
        this.weight = weight;
        this.value = value;
        this.itemCount = value.length;
        this.maxWeight = maxWeight;
        attemp = new int[itemCount + 1][maxWeight + 1];
        path = new int[itemCount + 1][maxWeight + 1];
    }

    public void solve() {
        for (int i = 1; i < attemp.length; i++) {
            for (int j = 1; j < attemp[i].length; j++) {
                if (weight[i - 1] > j) {
                    attemp[i][j] = attemp[i - 1][j];
                } else {
                    if (attemp[i - 1][j] < value[i - 1] + attemp[i - 1][j - weight[i - 1]]) {
                        attemp[i][j] = value[i - 1] + attemp[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    } else {
                        attemp[i][j] = attemp[i - 1][j];
                    }
                }
            }
        }
    }

    public void showAttemp(){
        for(int[] i : attemp){
            for(int j : i){
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    public void showItems(){
        int i = path.length - 1;
        int j = path[0].length - 1;
        while(i > 0 && j > 0){
            if(path[i][j] == 1){
                System.out.println("第" + i + "个物品放到了背包中");
                j -= weight[i-1];
            }
            i--;
        }
    }
}
