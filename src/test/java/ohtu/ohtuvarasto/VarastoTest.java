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
    Varasto varasto2;
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
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
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
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiMahduLiikaaTavaraa() {
        varasto.lisaaVarastoon(42);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yritetaanOttaaLiikaa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(8);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void huonoVarasto() {
        varasto2 = new Varasto(-42);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void huonoLisaysJaOtto() {
        varasto.lisaaVarastoon(2);
        varasto.lisaaVarastoon(-42);
        varasto.otaVarastosta(-42);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void varastoJossaAlkuSaldo() {
        varasto2 = new Varasto(11, 5);
        assertEquals("saldo = 5.0, vielä tilaa 6.0", varasto2.toString());
    }
    
    @Test
    public void huonoVarastoJossaHuonoAlkuSaldo() {
        varasto2 = new Varasto(-666, -666);
        assertEquals("saldo = 0.0, vielä tilaa 0.0", varasto2.toString());
    }

    @Test
    public void varastoJossaLiikaaAlkuSaldoa() {
        varasto2 = new Varasto(10, 666);
        assertEquals("saldo = 10.0, vielä tilaa 0.0", varasto2.toString());
    }
}
