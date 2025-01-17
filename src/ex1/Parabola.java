package ex1;

public class Parabola {
    private int a;
    private int b;
    private int c;
    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double[] getVarf()
    {
        double x=(double) -b/(2*a);
        double y=(double) (-b*b+4*a*c)/(4*a);
        return new double[] {x,y};
    }
    @Override
    public String toString() {
        return "f(x)="+a+"x^2+"+b+"x+"+c;
    }

    public double[] mijlocDreapta(Parabola p)
    {
        double[] v1=this.getVarf();
        double[] v2=p.getVarf();

        double x=(v1[0]+v2[0])/2;
        double y=(v1[1]+v2[1])/2;

        return new double[] {x,y};
    }
    public static double[] mijlocDreapta2(Parabola p1, Parabola p2)
    {
        double[] v1=p1.getVarf();
        double[] v2=p2.getVarf();

        double x=(v1[0]+v2[0])/2;
        double y=(v1[1]+v2[1])/2;

        return new double[] {x,y};
    }
    public double lungimeSegment(Parabola p)
    {
        double[] v1=this.getVarf();
        double[] v2=p.getVarf();

        return Math.hypot((v2[0]-v1[0]),(v2[1]-v1[1]));
    }

    public static double lungimeSegment2(Parabola p1, Parabola p2)
    {
        double[] v1=p1.getVarf();
        double[] v2=p2.getVarf();

        return Math.hypot((v2[0]-v1[0]),(v2[1]-v1[1]));
    }

}
