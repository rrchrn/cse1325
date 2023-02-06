public enum Type {
    Dog(12), Cat(15), Tiger(9), Bear(25);

    private double lifespan;

    private Type(double lifespan) {
        this.lifespan = lifespan;
    }

    public double lifespan() {
        return lifespan;
    }
}
