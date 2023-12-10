package filesprocessing;

import filesprocessing.filter.Filter;
import filesprocessing.order.Order;

public class Section {
    private Filter filter;
    private Order order;

    Section(Filter filter, Order order) {
        this.filter = filter;
        this.order = order;
    }

    public boolean isPassFilter(java.io.File f){ return this.filter.isPass(f); }

    public void order(java.io.File[] filesList){ this.order.sort(filesList); }
}
