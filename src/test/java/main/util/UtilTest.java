package main.util;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


@Test
public class UtilTest {

    public void given_nullList_when_getProduct_then_getEmptyCartesianProduct() {
        Assert.assertEquals(Util.getCartesianProduct(null).size(), 0);
    }

    public void given_emptyList_when_getProduct_then_getEmptyCartesianProduct() {
        Assert.assertEquals(Util.getCartesianProduct(new ArrayList<>()).size(), 0);
    }

    public void given_sizeOneList_when_getProduct_then_getCartesianProduct() {
        List<List<String>> inputList = new ArrayList<>();
        inputList.add(Arrays.asList("Yes"));
        Assert.assertEquals(Util.getCartesianProduct(inputList).size(), 1);
    }

    public void given_sizeTwoList_when_getProduct_then_getCartesianProduct() {
        List<List<String>> inputList = new ArrayList<>();
        inputList.add(Arrays.asList("Green", "Red"));
        inputList.add(Arrays.asList("Apple", "Mango"));
        List<String> expected = Arrays.asList("GreenApple", "GreenMango", "RedApple", "RedMango");
        List<String> product = Util.getCartesianProduct(inputList);
        MatcherAssert.assertThat(product, containsInAnyOrder(expected.toArray()));
    }

    public void given_sizeThreeList_when_getProduct_then_getCartesianProduct() {
        List<List<String>> inputList = new ArrayList<>();
        inputList.add(Arrays.asList("Green", "Red"));
        inputList.add(Arrays.asList("Apple", "Mango"));
        inputList.add(Arrays.asList("Sweet"));
        List<String> expected = Arrays.asList("GreenAppleSweet", "GreenMangoSweet", "RedAppleSweet", "RedMangoSweet");
        List<String> product = Util.getCartesianProduct(inputList);
        MatcherAssert.assertThat(product, containsInAnyOrder(expected.toArray()));
    }

    public void given_sizeThreeListWithHole_when_getProduct_then_getCartesianProduct() {
        List<List<String>> inputList = new ArrayList<>();
        inputList.add(Arrays.asList("Green", "Red"));
        inputList.add(Arrays.asList(""));
        inputList.add(Arrays.asList("Sweet"));
        List<String> expected = Arrays.asList("GreenSweet", "RedSweet");
        List<String> product = Util.getCartesianProduct(inputList);
        MatcherAssert.assertThat(product, containsInAnyOrder(expected.toArray()));
    }

    public void given_nullString_when_callIsBlank_getTrue() {
        Assert.assertTrue(Util.isBlank(null));
    }

    public void given_emptyString_when_callIsBlank_getTrue() {
        Assert.assertTrue(Util.isBlank(""));
    }

    public void given_blankString_when_callIsBlank_getTrue() {
        Assert.assertTrue(Util.isBlank("   "));
    }

    public void given_validString_when_callIsBlank_getFalse() {
        Assert.assertFalse(Util.isBlank("suggest"));
    }


    public void test_isEmptyAndIsNotEmpty_with_nullList() {
        Assert.assertTrue(Util.isEmpty(null));
        Assert.assertFalse(Util.isNotEmpty(null));
    }

    public void test_isEmptyAndIsNotEmpty_with_EmptyList() {
        List<String> emptyList = new ArrayList<>();
        Assert.assertTrue(Util.isEmpty(emptyList));
        Assert.assertFalse(Util.isNotEmpty(emptyList));
    }

    public void test_isEmptyAndIsNotEmpty_with_ValidList() {
        List<String> validList = Arrays.asList("suggest");
        Assert.assertFalse(Util.isEmpty(validList));
        Assert.assertTrue(Util.isNotEmpty(validList));
    }

    public void test_isListEmpty_with_nullList() {
        Assert.assertTrue(Util.isListEmpty(null));
    }

    public void test_isListEmpty_with_EmptyList() {
        List<List<String>> emptyList = new ArrayList<>();
        Assert.assertTrue(Util.isListEmpty(emptyList));
    }

    public void test_isListEmpty_with_ValidList() {
        List<List<String>> emptyList = new ArrayList<>();
        emptyList.add(Arrays.asList("yes"));
        Assert.assertFalse(Util.isListEmpty(emptyList));
    }
}