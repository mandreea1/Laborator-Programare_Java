package teste;

import ex1.PerecheNumere;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestePerechiNumere {
    @Test
    void test1_fibonaci()
    {
        PerecheNumere p=new PerecheNumere(2,3);
        assertTrue(p.consecutiveFibonaci());
    }
    @Test
    void test2_fibonaci()
    {
        PerecheNumere p=new PerecheNumere(1,3);
        assertFalse(p.consecutiveFibonaci());
    }
    @Test
    void test3_fibonaci()
    {
        PerecheNumere p=new PerecheNumere(0,-1);
        assertFalse(p.consecutiveFibonaci());
    }
    @Test
    void test1_cmmmc()
    {
        PerecheNumere p=new PerecheNumere(3,5);
        assertEquals(15,p.cmmmc());
    }
    @Test
    void test2_cmmmc()
    {
        PerecheNumere p=new PerecheNumere(4,4);
        assertEquals(4,p.cmmmc());
    }
    @Test
    void test3_cmmmc()
    {
        PerecheNumere p=new PerecheNumere(6,3);
        assertEquals(6,p.cmmmc());
    }
    @Test
    void test1_sumaCifre()
    {
        PerecheNumere p=new PerecheNumere(123,312);
        assertTrue(p.sumaCifreEgala());
    }
    @Test
    void test2_sumaCifre()
    {
        PerecheNumere p=new PerecheNumere(0,0);
        assertTrue(p.sumaCifreEgala());
    }
    @Test
    void test3_sumaCifre()
    {
        PerecheNumere p=new PerecheNumere(123,3121);
        assertFalse(p.sumaCifreEgala());
    }
    @Test
    void test1_cifrePare()
    {
        PerecheNumere p=new PerecheNumere(246,846);
        assertTrue(p.cifrePareEgale());
    }
    @Test
    void test2_cifrePare()
    {
        PerecheNumere p=new PerecheNumere(246,156);
        assertFalse(p.cifrePareEgale());
    }
    @Test
    void test3_cifrePare()
    {
        PerecheNumere p=new PerecheNumere(111,333);
        assertTrue(p.cifrePareEgale());
    }
}
