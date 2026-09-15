package com.tiamo.dt.common.utils;



import com.tiamo.dt.common.pojo.tree.TreeNode;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 树工具类
 *
 * @author z-Roc
 * @since 2025-11-17 09:29
 **/
public class TreeUtil {

    /**
     * 将任意具有 id 和 parentId 的列表转换为树形结构
     *
     * @param list           实体列表
     * @param idGetter       获取实体 ID 的函数（如 CombatOrg::getId）
     * @param parentIdGetter 获取实体父 ID 的函数（如 CombatOrg::getParentId）
     * @param <T>            实体类型
     * @return 树形结构的 TreeNode 列表（根节点为 parentId == 0 或 null）
     */
    public static <T> List<TreeNode<Long>> convertToTree(
            List<T> list,
            Function<T, Long> idGetter,
            Function<T, Long> parentIdGetter,
            Function<T, String> labelGetter) {

        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Long, TreeNode<Long>> nodeMap = new HashMap<>();
        List<TreeNode<Long>> rootNodes = new ArrayList<>();

        // 第一次遍历：创建所有节点
        for (T item : list) {
            Long id = idGetter.apply(item);
            String label = labelGetter.apply(item);
            if (id == null) continue; // 跳过无效 ID
            TreeNode<Long> node = new TreeNode<>(id, label, new ArrayList<>(), item, null);
            nodeMap.put(id, node);
        }

        // 第二次遍历：建立父子关系
        for (T item : list) {
            Long id = idGetter.apply(item);
            if (id == null) continue;

            Long parentId = parentIdGetter.apply(item);
            TreeNode<Long> node = nodeMap.get(id);

            boolean isRoot = false;

            if (parentId == null || parentId == 0L) {
                isRoot = true;
            } else {
                TreeNode<Long> parent = nodeMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(node);
                } else {
                    isRoot = true; // 孤儿节点
                }
            }

            if (isRoot) {
                rootNodes.add(node);
            }
        }

        // 🔥 关键：递归对所有节点的 children 按 ID 排序
        sortTreeNodes(rootNodes);

        return rootNodes;
    }

    // 递归排序方法
    private static void sortTreeNodes(List<TreeNode<Long>> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }

        // 当前层按 ID 升序排序
        nodes.sort(Comparator.comparing(TreeNode::getId));

        // 递归处理每个子节点的 children
        for (TreeNode<Long> node : nodes) {
            sortTreeNodes(node.getChildren());
        }
    }




    /**
     * 通用树构建方法（支持任意 ID 类型）
     *
     * @param list           扁平化列表
     * @param idGetter       获取节点ID的函数
     * @param parentIdGetter 获取父节点ID的函数
     * @param childrenGetter 获取子节点列表的函数
     * @param childrenSetter 设置子节点列表的函数
     * @param <T>            对象类型
     * @param <ID>           ID 类型（如 Long, Integer, String）
     * @return 根节点列表
     */
    public static <T, ID> List<T> buildTree(
            List<T> list,
            Function<T, ID> idGetter,
            Function<T, ID> parentIdGetter,
            Function<T, List<T>> childrenGetter,
            BiConsumer<T, List<T>> childrenSetter) {

        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        Map<ID, T> nodeMap = new HashMap<>();
        for (T item : list) {
            ID id = idGetter.apply(item);
            if (id != null) {
                nodeMap.put(id, item);
            }
        }

        List<T> rootNodes = new ArrayList<>();
        for (T item : list) {
            ID parentId = parentIdGetter.apply(item);

            if (parentId == null || !nodeMap.containsKey(parentId)) {
                rootNodes.add(item);
            } else {
                T parent = nodeMap.get(parentId);
                List<T> children = childrenGetter.apply(parent);
                if (children == null) {
                    children = new ArrayList<>();
                    childrenSetter.accept(parent, children);
                }
                children.add(item);
            }
        }

        return rootNodes;
    }
}
