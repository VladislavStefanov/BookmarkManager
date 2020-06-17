package bg.sofia.uni.fmi.mjt.bookmarks.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TagTest {

    private static final String REFINED_TAG = "stand";
    private static final String RAW_TAG = "????-standingly..!@";

    @Test
    public void test() {
        assertEquals(REFINED_TAG, Tag.newTag(RAW_TAG).toString());
    }

}
