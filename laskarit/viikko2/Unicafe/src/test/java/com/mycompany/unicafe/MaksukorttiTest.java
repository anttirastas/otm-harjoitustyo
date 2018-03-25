package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, this.kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        this.kortti.lataaRahaa(4);
        assertEquals(14, this.kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi() {
        this.kortti.otaRahaa(3);
        assertEquals(7, this.kortti.saldo());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        this.kortti.otaRahaa(15);
        assertEquals(10, this.kortti.saldo());
    }
    
    @Test
    public void metodiPalauttaaTrueJosRahatRiittivatJaMuutenFalse() {
        assertTrue(this.kortti.otaRahaa(7));
        assertFalse(this.kortti.otaRahaa(17));
    }
    
    @Test
    public void toStringMetodiToimii() {
        assertEquals("saldo: 0.10", this.kortti.toString());
        this.kortti.lataaRahaa(150);
        assertEquals("saldo: 1.60", this.kortti.toString());
    }
}
