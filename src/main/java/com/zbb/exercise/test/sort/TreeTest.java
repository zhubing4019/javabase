package com.zbb.exercise.test.sort;

import org.junit.Test;

import java.util.TreeMap;

public class TreeTest {
    /**
     * 平衡二叉树
     *
     *
     *  二叉查找树（BST）
     * 1.左子树都小于根节点
     * 2.右子树都大于根节点
     * 3.左右子树都是二叉查找树
     *
     * 平衡二叉查找树(Balanced BST)  :AVL树和红黑树
     *
     * 红黑树 复杂度：O(log n)
     * 红黑树是一种近似平衡的二叉查找树，它能够确保任何一个节点的左右子树的高度差不会超过二者中较低那个的一陪
     * 有一个key、3个指针：parent、lchild、rchild以外，还多了一个属性：color
     * 1. 根节点是黑色的。
     * 2. 空节点是黑色的（红黑树中，根节点的parent以及所有叶节点lchild、rchild都不指向NULL，而是指向一个定义好的空节点）。
     * 3. 红色节点的父、左子、右子节点都是黑色。
     * 4. 在任何一棵子树中，每一条从根节点向下走到空节点的路径上包含的黑色节点数量都相同。
     * 定理：一棵含有n个节点的红黑树的高度至多为2log(n+1).
     *
     *
     */
    @Test
    public void test(){
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 1);
    }


}
