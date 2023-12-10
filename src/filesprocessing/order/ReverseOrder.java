package filesprocessing.order;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class ReverseOrder implements Order {
    protected Order orderToReverse;

    ReverseOrder(Order order){
        this.orderToReverse = order;
    }

    @Override
    public void sort(File[] filesList) {
        this.orderToReverse.sort(filesList);
        Collections.reverse(Arrays.asList(filesList));
    }
}
