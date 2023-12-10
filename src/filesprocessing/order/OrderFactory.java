package filesprocessing.order;

public class OrderFactory {
    private static final String REVERSE = "REVERSE";
    private static final int NAME = 0;
    private static final int SUFFIX = 1;

    public static Order createOrder (String[] orderInput) throws IllegalOrderNameException {
        String nameOfOrder = orderInput[NAME];
        Order order;
        switch (nameOfOrder){
            case "abs":
                order = new Abs();
                break;
            case "type":
                order = new Type();
                break;
            case "size":
                order = new Size();
                break;
            default:
                throw new IllegalOrderNameException();
        }
        if (REVERSE.equals(orderInput[SUFFIX]))
            return new ReverseOrder(order);
        return order;
    }
}