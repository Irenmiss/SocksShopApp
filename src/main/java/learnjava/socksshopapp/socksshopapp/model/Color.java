package learnjava.socksshopapp.socksshopapp.model;

public enum Color {
    BLACK("Черный"),
    WHITE("Белый"),
    RED("Красный"),
    BLUE("Синий"),
    GREEN("Зеленый"),
    GREY("Серый");
    private String socksColor;

    Color(String socksColor) {
        this.socksColor = socksColor;
    }

    public String getSocksColor() {
        return socksColor;
    }
}
