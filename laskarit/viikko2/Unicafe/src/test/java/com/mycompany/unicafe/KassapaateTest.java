/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author antti
 */
public class KassapaateTest {
    Kassapaate paate;
    Maksukortti korttiJollaTarpeeksiRahaa;
    Maksukortti korttiJollaLiianVahanRahaa;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        korttiJollaTarpeeksiRahaa = new Maksukortti(2000);
        korttiJollaLiianVahanRahaa = new Maksukortti(20);
    }
    
    @Test
    public void luodunPaatteenRahamaaraJaMyydytLounaatOikein() {
        assertEquals(100000, this.paate.kassassaRahaa());
        assertEquals(0, this.paate.maukkaitaLounaitaMyyty());
        assertEquals(0, this.paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoToimiiEdullisilleLounaille() {
        assertEquals(260, this.paate.syoEdullisesti(500));
        assertEquals(100240, this.paate.kassassaRahaa());
        assertEquals(1, this.paate.edullisiaLounaitaMyyty());
        
        assertEquals(200, this.paate.syoEdullisesti(200));
        assertEquals(100240, this.paate.kassassaRahaa());
        assertEquals(1, this.paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoToimiiMaukkailleLounaille() {
        assertEquals(100, this.paate.syoMaukkaasti(500));
        assertEquals(100400, this.paate.kassassaRahaa());
        assertEquals(1, this.paate.maukkaitaLounaitaMyyty());
        
        assertEquals(300, this.paate.syoMaukkaasti(300));
        assertEquals(100400, this.paate.kassassaRahaa());
        assertEquals(1, this.paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoToimiiEdullisilleLounaille() {
        assertTrue(this.paate.syoEdullisesti(korttiJollaTarpeeksiRahaa));
        assertEquals(1760, this.korttiJollaTarpeeksiRahaa.saldo());
        assertEquals(1, this.paate.edullisiaLounaitaMyyty());
        
        assertFalse(this.paate.syoEdullisesti(korttiJollaLiianVahanRahaa));
        assertEquals(20, this.korttiJollaLiianVahanRahaa.saldo());
        assertEquals(1, this.paate.edullisiaLounaitaMyyty());
        assertEquals(100000, this.paate.kassassaRahaa());
    }
    
    @Test
    public void korttiostoToimiiMaukkailleLounaille() {
        assertTrue(this.paate.syoMaukkaasti(korttiJollaTarpeeksiRahaa));
        assertEquals(1600, this.korttiJollaTarpeeksiRahaa.saldo());
        assertEquals(1, this.paate.maukkaitaLounaitaMyyty());
        
        assertFalse(this.paate.syoMaukkaasti(korttiJollaLiianVahanRahaa));
        assertEquals(20, this.korttiJollaLiianVahanRahaa.saldo());
        assertEquals(1, this.paate.maukkaitaLounaitaMyyty());
        assertEquals(100000, this.paate.kassassaRahaa());
    }
    
    @Test
    public void rahanLatausKortilleToimiiOikein() {
        this.paate.lataaRahaaKortille(korttiJollaTarpeeksiRahaa, 1000);
        assertEquals(3000, this.korttiJollaTarpeeksiRahaa.saldo());
        assertEquals(101000, this.paate.kassassaRahaa());
        this.paate.lataaRahaaKortille(korttiJollaTarpeeksiRahaa, -50);
        assertEquals(3000, this.korttiJollaTarpeeksiRahaa.saldo());
        assertEquals(101000, this.paate.kassassaRahaa());
    }
}
