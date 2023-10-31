package test;

import com.example.sortingalgorithms.sorting.Node;
import com.example.sortingalgorithms.sorting.Tree;
import com.example.sortingalgorithms.sorting.TreeSort;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeSortTest {

    @Test
    public void testSort() {
        List<Integer> inputList = new ArrayList<>(List.of(2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> list = new ArrayList<>(List.of(6, 4, 5, 3, 2, 7, 8, 9));
        TreeSort<Integer> treeSort = new TreeSort<>();
        Assertions.assertEquals(inputList, treeSort.sort(list));

    }

    @Test
    public void testGetData() {
        Node<Integer> node = new Node<>(5);
        assertEquals(5, node.getData());
    }

    @Test
    public void testGetLeft() {
        Node<Integer> node = new Node<>(5);
        node.setLeft(new Node<Integer>(4));
        assertEquals(new Node<Integer>(4), node.getLeft());
    }

    @Test
    public void testToMakeInorder() {
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> integersForInorderWalk = new ArrayList<>(List.of(5, 4, 6, 7, 1, 2, 9, 8, 3, 10));
        Tree<Integer> tree = new Tree<>();
        tree = tree.toCreateTreeFromList(integersForInorderWalk);
        assertEquals(integers, tree.toMakeInorder());
    }
}