package com.test.topi;

import com.test.topi.adapter.adapter;
import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class Testes_Unidade {

    @Test
    public void check_getForksLabel_test() {
        String test = adapter.getForksLabel(1000);
        assertThat(test).isEqualTo("1k");
    }
    @Test
    public void check_getStarsCountLabel_test() {
        String test = adapter.getStarsCountLabel(2000);
        assertThat(test).isEqualTo("2k");
    }
}
