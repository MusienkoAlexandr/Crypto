public class RSA {
    private int d;
    public int e;
    private int p;
    private int q;
    public RSA(int d, int p, int q) {
        this.d = d;
        this.p = p;
        this.q = q;
        this.e = Base.inverse(d, (p-1)*(q-1));
    }

}
