package de.jandrotek.android.aspectra.analyze;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jan on 02.09.15.
 */
public class AnalyzeViewControllerTest {

    private AnalyzeViewController controller;
    private int[] indexOld;
    private int[] indexNew;
    private int[] movement;
    private int actual;
    private int expected;


    @Before
    public void setUp() throws Exception {

        controller = new AnalyzeViewController(2);
        indexOld = new int[2];
    }

    @Test
    public void testCNP_FromZeroNoMove() throws Exception {
        indexOld[0] = 0;
        indexOld[1] = 0;
        controller.setStartIndexOld(indexOld);
        controller.calcNewPositions(0);
        indexNew = controller.getMovement();

        expected = 0;
        actual = indexNew[0];
        assertEquals("move 0 from 0 failed", expected, actual);
        actual = indexNew[1];
        assertEquals("move 0 from 0 failed", expected, actual);
    }


    @Test
    public void testCNP_FromZeroRight() throws Exception {
        indexOld[0] = 0;
        indexOld[1] = 0;
        int moveRight = 5;
        controller.setStartIndexOld(indexOld);
        controller.calcNewPositions(moveRight);
        movement = controller.getMovement();

        // edited
        expected = moveRight;
        actual = movement[0];
        assertEquals("move 5 from 0 failed", expected, actual);
        //reference
        expected = 0;
        actual = movement[1];
        assertEquals("move 5 from 0 failed", expected, actual);
    }

    @Test
    public void testCNP_FromZeroLeft() throws Exception {
        indexOld[0] = 0;
        indexOld[1] = 0;
        int moveRight = -5;
        controller.setStartIndexOld(indexOld);
        controller.calcNewPositions(moveRight);
        movement = controller.getMovement();

        // edited
        expected = 0;
        actual = movement[0];
        assertEquals("move -5 from 0 failed", expected, actual);
        //reference
        expected = -moveRight;
        actual = movement[1];
        assertEquals("move -5 from 0 failed", expected, actual);
    }

    @Test
    public void testCNP_FromRighToLeftFar() throws Exception {
        indexOld[0] = 3;
        indexOld[1] = 0;
        int moveRight = -5;
        controller.setStartIndexOld(indexOld);
        controller.calcNewPositions(moveRight);
        movement = controller.getMovement();

        // edited
        expected = -3;
        actual = movement[0];
        assertEquals("move -5 from 3 failed", expected, actual);
        //reference
        expected = 2;
        actual = movement[1];
        assertEquals("move -5 from 3 failed", expected, actual);
    }

    @Test
    public void testCNP_FromRighToLeft() throws Exception {
        indexOld[0] = 8;
        indexOld[1] = 0;
        int moveRight = -5;
        controller.setStartIndexOld(indexOld);
        controller.calcNewPositions(moveRight);
        movement = controller.getMovement();

        // edited
        expected = -5;
        actual = movement[0];
        assertEquals("move -5 from 3 failed", expected, actual);
        //reference
        expected = 0;
        actual = movement[1];
        assertEquals("move -5 from 3 failed", expected, actual);
    }

    @Test
    public void testCNP_FromLeftToRightFar() throws Exception {
        indexOld[0] = 0;
        indexOld[1] = 3;
        int moveRight = 5;
        controller.setStartIndexOld(indexOld);
        controller.calcNewPositions(moveRight);
        movement = controller.getMovement();

        // edited
        expected = 2;
        actual = movement[0];
        assertEquals("move -5 from 3 failed", expected, actual);
        //reference
        expected = -3;
        actual = movement[1];
        assertEquals("move -5 from 3 failed", expected, actual);
    }

    @Test
    public void testCNP_FromLeftToRight() throws Exception {
        indexOld[0] = 0;
        indexOld[1] = 5;
        int moveRight = 3;
        controller.setStartIndexOld(indexOld);
        controller.calcNewPositions(moveRight);
        movement = controller.getMovement();

        // edited
        expected = 0;
        actual = movement[0];
        assertEquals("move -5 from 3 failed", expected, actual);
        //reference
        expected = -3;
        actual = movement[1];
        assertEquals("move -5 from 3 failed", expected, actual);
    }

    @Test
    public void testCNP_FromLeftToLeft() throws Exception {
        indexOld[0] = 0;
        indexOld[1] = 5;
        int moveRight = -3;
        controller.setStartIndexOld(indexOld);
        controller.calcNewPositions(moveRight);
        movement = controller.getMovement();

        // edited
        expected = 0;
        actual = movement[0];
        assertEquals("move -5 from 3 failed", expected, actual);
        //reference
        expected = 3;
        actual = movement[1];
        assertEquals("move -5 from 3 failed", expected, actual);
    }

    @Test
    public void testCNP_FromRightToRight() throws Exception {
        indexOld[0] = 5;
        indexOld[1] = 0;
        int moveRight = 3;
        controller.setStartIndexOld(indexOld);
        controller.calcNewPositions(moveRight);
        movement = controller.getMovement();

        // edited
        expected = 3;
        actual = movement[0];
        assertEquals("move -5 from 3 failed", expected, actual);
        //reference
        expected = 0;
        actual = movement[1];
        assertEquals("move -5 from 3 failed", expected, actual);
    }
}