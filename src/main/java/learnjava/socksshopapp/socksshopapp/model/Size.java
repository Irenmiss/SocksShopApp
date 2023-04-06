package learnjava.socksshopapp.socksshopapp.model;

public enum Size {
    XS(23),
    S(25),
    M(27),
    L(29),
    XL(31);
    private Integer socksSize;

    Size(Integer socksSize) {
        this.socksSize = socksSize;
    }

    public Integer getSocksSize() {
        return socksSize;
    }

    public void setSizeNum(Integer socksSize) {
        this.socksSize = socksSize;
    }
}
