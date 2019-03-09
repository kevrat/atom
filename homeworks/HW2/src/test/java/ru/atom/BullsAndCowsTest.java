package ru.atom;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class BullsAndCowsTest {

    @Test
    public void getCows() throws Exception {
        assertThat(BullsAndCows.getCows("abcdef", "fedcba"), is(equalTo(6)));
    }

    @Test
    public void getBulls() throws Exception {
        assertThat(BullsAndCows.getBulls("abcdef", "a_c_e_"), is(equalTo(3)));
    }

    @Test
    public void play() throws Exception {
        assertThat(BullsAndCows.getSecret("testword.txt"), is(equalTo("abcdef")));
    }
}
