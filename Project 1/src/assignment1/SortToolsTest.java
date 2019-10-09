package assignment1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;


public class SortToolsTest {

    @Test(timeout = 2000)
    public void testFindFoundFull(){
        int[] x = new int[]{-2, -1, 0, 1, 2, 3};
        assertEquals(3, SortTools.find(x, 6, 1));
    }

    @Test(timeout = 2000)
    public void testInsertGeneralPartialEnd(){
        int[] x = new int[]{10, 20, 30, 40, 50};
        int[] expected = new int[]{10, 20, 30, 35};
        assertArrayEquals(expected, SortTools.insertGeneral(x, 3, 35));
    }

    @Test(timeout = 2000)
    public void testInsertInPlacePartialEnd(){
        int[] x = new int[]{10, 20, 30, 40, 50};
        assertEquals(4, SortTools.insertInPlace(x, 3, 35));
    }

    //isSorted Tests
    @Test (timeout = 2000)
    public void testIsSortedTrue(){
        int[] x = new int[]{-2, -1, 0, 1, 2, 3};
        assertEquals(true, SortTools.isSorted(x, 6));
    }

    @Test (timeout = 2000)
    public void testIsSortedFalse(){
        int[] x = new int[]{-2, -1, 5, 1, 2, 3};
        assertEquals(false, SortTools.isSorted(x, 6));
    }

    @Test (timeout = 2000)
    public void testIsSortedTrue1(){
        int[] x = new int[]{-2, -1, 0};
        assertEquals(true, SortTools.isSorted(x, 3));
    }

    @Test (timeout = 2000)
    public void testIsSortedFalse1(){
        int[] x = new int[]{1, -1, 5};
        assertEquals(false, SortTools.isSorted(x, 3));
    }
    //end of isSorted Tests

    //find tests
    @Test(timeout = 2000)
    public void testFindNotFound(){
        int[] x = new int[]{-2, -1, 0, 1, 2, 3};
        assertEquals(-1, SortTools.find(x, 6, 5));
    }

    @Test(timeout = 2000)
    public void testFindFound(){
        int[] x = new int[]{-2, -1, 0, 1, 2, 3};
        assertEquals(2, SortTools.find(x, 6, 0));
    }

    @Test(timeout = 2000)
    public void testFindNotFound1(){
        int[] x = new int[]{10, 11, 12};
        assertEquals(-1, SortTools.find(x, 3, 13));
    }

    @Test(timeout = 2000)
    public void testFindFound1(){
        int[] x = new int[]{-2, -1, 0, 1, 7, 8, 8};
        assertEquals(4, SortTools.find(x, 6, 7));
    }
    //end of find tests

    @Test  (timeout = 2000)
    public void testInsertGeneralPartialEnd1(){
        int[] x = new int[]{-1, 0, 50, 90};
        int[] expected = new int[]{-1, 0, 50, 90, 100};
        assertArrayEquals(expected, SortTools.insertGeneral(x, 4, 100));
    }

    @Test  (timeout = 2000)
    public void testInsertGeneralPartialEnd2(){
        int[] x = new int[]{-2, -1, 50};
        int[] expected = new int[]{-2, -1, 0, 50};
        assertArrayEquals(expected, SortTools.insertGeneral(x, 3, 0));
    }

    @Test  (timeout = 2000)
    public void testInsertGeneralPartialEnd3(){
        int[] x = new int[]{-1, 0, 50, 90};
        int[] expected = new int[]{-2, -1, 0, 50, 90};
        assertArrayEquals(expected, SortTools.insertGeneral(x, 4, -2));
    }
    //end of general partial tests

    //insertInPlace tests
    @Test  (timeout = 2000)
    public void testInsertInPlaceOne(){
        int[] x = new int[]{10, 20, 30, 40};
        int[] expected = new int[]{10, 17, 20, 30};
        SortTools.insertInPlace(x, 3, 17);
        assertArrayEquals(expected, x);
    }

    @Test  (timeout = 2000)
    public void testInsertInPlaceTwo(){
        int[] x = new int[]{10, 20, 30, 40};
        int[] expected = new int[]{10, 20, 25, 30};
        SortTools.insertInPlace(x, 3, 25);
        assertArrayEquals(expected, x);
    }

    @Test  (timeout = 2000)
    public void testInsertInPlaceThree(){
        int[] x = new int[]{-50, 5, 10, 90, 100};
        int[] expected = new int[]{-50, 5, 10, 12, 90};
        SortTools.insertInPlace(x, 4, 12);
        assertArrayEquals(expected, x);
    }
    //end of InsertInPlace tests

    //inserSort tests
    @Test  (timeout = 2000)
    public void testInsertSort(){
        int[] x = new int[]{10, 20, 15, 40};
        int[] expected = new int[]{10, 15, 20, 40};
        SortTools.insertSort(x, 4);
        assertArrayEquals(expected,x);
    }

    @Test  (timeout = 2000)
    public void testInsertSort1(){
        int[] x = new int[]{10, 20, 15, 12};
        int[] expected = new int[]{10, 12, 15, 20};
        SortTools.insertSort(x, 4);
        assertArrayEquals(expected,x);
    }

    @Test  (timeout = 2000)
    public void testInsertSort2(){
        int[] x = new int[]{10, 20, 15, 40, -1000, -50};
        int[] expected = new int[]{-1000, -50, 10, 15, 20, 40};
        SortTools.insertSort(x, 6);
        assertArrayEquals(expected,x);
    }

    @Test  (timeout = 2000)
    public void testInsertSort3(){
        int[] x = new int[]{10, 9, 8, 40};
        int[] expected = new int[]{9, 10, 8, 40};
        SortTools.insertSort(x, 2);
        assertArrayEquals(expected,x);
    }
    //end of insertSort tests


}
