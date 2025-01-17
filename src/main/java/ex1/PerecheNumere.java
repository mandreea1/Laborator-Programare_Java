package ex1;

public class PerecheNumere {
    private int a;
    private int b;
    public PerecheNumere(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public PerecheNumere() {}
    public int getA() {return a;}
    public int getB() {return b;}
    public void setA(int a) {this.a = a;}
    public void setB(int b) {this.b = b;}

    @Override
    public String toString() {
        return a+" "+b;
    }
    /**
     *dacă cele două numere care
     * formează perechea sunt numere consecutive în șirul lui Fibonaci
     * @return Fibonaci
     */
    public boolean consecutiveFibonaci()
    {
        int temp;
        if(a<0 || b<0)
            return false;
        if(a<b)
        {
            temp = a;
            a = b;
            b = temp;
        }
        while(a>1){
            a=a-b;
            temp = a;
            a = b;
            b = temp;
        }
        if(a==1 && b==1)
            return true;
        else
            return false;
    }
    /**
     *cel mai mic multiplu comun al celor 2 numere
     * @return CMMMC
     */
    public int cmmmc()
    {
        int p = a;
        int q = b;
        while((a%b)!=0)
        {
            int r = a % b;
            a = b;
            b =r;
        }
        return p*q/b;
    }
    /**
     *dacă cele două numere
     * au suma cifrelor egala se va returna true
     * @return Suma cifre egala
     */
    public boolean sumaCifreEgala()
    {
        int s1=0,s2=0;
        while(a!=0)
        {
            s1+=a%10;
            a=a/10;
        }
        while(b!=0)
        {
            s2+=b%10;
            b=b/10;
        }
        if(s1==s2)
            return true;
        else return false;
    }
    /**
     *dacă cele două numere
     * au au acelasi numar de cifre pare se va returna true
     * @return Acelasi numar de cifre pare
     */
    public boolean cifrePareEgale()
    {
        int s1=0,s2=0;
        while(a!=0)
        {
            if(((a%10))%2==0)
            {
                s1++;
            }
            a=a/10;
        }
        while(b!=0)
        {
            if(((b%10))%2==0)
            {
                s2++;
            }
            b=b/10;
        }
        if(s1==s2)
            return true;
        else return false;
    }
}
