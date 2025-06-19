package com.transchile.devops;

import static org.junit.Assert.*;
import org.junit.Test;

public class AppTest {

    @Test
    public void testSumar() {
        int resultado = App.sumar(5, 3);
        assertEquals(8, resultado);
    }
}
