package com.prisoner.test;

import com.prisoner.test.exceptions.DnaExcepcion;
import com.prisoner.test.exceptions.InvalidDnaSequence;
import com.prisoner.test.exceptions.InvalidMatrixSize;
import com.prisoner.test.services.PrisionerService;
import com.prisoner.test.services.ProsionerServiceImpl;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@AutoConfigureTestEntityManager
public class ProsionerServiceImplTest {
    PrisionerService prisionerService = new ProsionerServiceImpl();

    @Test
    public void checkPrisionerDnaFourByFourMatrixHorizontal() throws DnaExcepcion {
        String[] dna = { "|SP v<", "|SP v<", "||||||", "|SP v<", "||||||", "|SP v<" };
        assertTrue(prisionerService.isPrisioner(dna));
    }

    @Test
    public void checkPrisionerDnaFiveByFiveMatrixHorizontal() throws DnaExcepcion {
        String[] dna = { "|SP v<", "|SP v<", "||||||", "|SP v<", "||||||", "|SP v<" };
        assertTrue(prisionerService.isPrisioner(dna));
    }
}
