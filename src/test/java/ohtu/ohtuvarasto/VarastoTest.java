package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto nollaVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonTilavuusNolla() {
        nollaVarasto = new Varasto(0);
        assertEquals(0, nollaVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonTilavuusJaSaldoNollaKuorimettuKonstruktori() {
        nollaVarasto = new Varasto(0, 0);
        assertEquals(0, nollaVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, nollaVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonTilavuusJaSaldoKuorimettuKonstruktori() {
        nollaVarasto = new Varasto(5, 5);
        assertEquals(5, nollaVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, nollaVarasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void uudenVarastonTilavuusJaSaldoAlkuSaldoNegaKuorimettuKonstruktori() {
        nollaVarasto = new Varasto(5, -5);
        assertEquals(5, nollaVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, nollaVarasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void uudenVarastonTilavuusJaSaldoAlkuSaldoPienempikuinTilavuusKuorimettuKonstruktori() {
        nollaVarasto = new Varasto(5, 3);
        assertEquals(5, nollaVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(3, nollaVarasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void uudenVarastonTilavuusJaSaldoAlkuSaldoSuurempiKuinTilavuusKuorimettuKonstruktori() {
        nollaVarasto = new Varasto(5, 8);
        assertEquals(5, nollaVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, nollaVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negaTiivinenLisays() {
        varasto.lisaaVarastoon(-8);
        // saldon pitäisi olla edelleen nolla
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysYliTilavuuden() {
        varasto.lisaaVarastoon(12);
        // saldon pitäisi olla 10
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void otetaanEnemmanKuinOn() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(10);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivisesti() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-8);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(8);
        String mjono = varasto.toString();
        assertEquals("saldo = 8.0, vielä tilaa 2.0", mjono);
        
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);
        varasto.otaVarastosta(4);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}