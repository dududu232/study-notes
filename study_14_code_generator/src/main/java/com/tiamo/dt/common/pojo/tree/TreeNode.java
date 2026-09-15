package com.tiamo.dt.common.pojo.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 树状结构节点封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T id;
    private String label;
    private List<TreeNode<T>> children;
    private Object data;
    private Integer order;
    private Object type;

    public TreeNode(T id, String label, List<TreeNode<T>> children, Object data) {
        this.id = id;
        this.label = label;
        this.children = children;
        this.data = data;
        this.order = 0;
    }

    public TreeNode(T id, String label, List<TreeNode<T>> children, Object data, Object type) {
        this.id = id;
        this.label = label;
        this.children = children;
        this.data = data;
        this.order = 0;
        this.type = type;
    }
}
