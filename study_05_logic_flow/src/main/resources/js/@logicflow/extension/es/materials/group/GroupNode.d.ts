import { h } from '@logicflow/core';
import { RectResize } from '../../NodeResize';
declare class GroupNodeModel extends RectResize.model {
    readonly isGroup = true;
    children: Set<string>;
    isRestrict: boolean;
    resizable: boolean;
    foldable: boolean;
    foldedWidth: number;
    foldedHeight: number;
    isFolded: boolean;
    unfoldedWidth: number;
    unfoldedHight: number;
    initNodeData(data: any): void;
    getResizeOutlineStyle(): {
        fill: string;
        stroke: string;
        strokeWidth: number;
        strokeDasharray: string;
    };
    foldGroup(isFolded: any): void;
    /**
     * 折叠分组的时候，处理分组内部子节点上的连线
     * 1. 为了保证校验规则不被打乱，所以只隐藏子节点上面的连线。
     * 2. 重新创建一个属性一样的边。
     * 3. 这个边拥有virtual=true的属性，表示不支持直接修改此边内容。
     */
    private foldEdge;
    isInRange({ x1, y1, x2, y2 }: {
        x1: any;
        y1: any;
        x2: any;
        y2: any;
    }): boolean;
    setAllowAppendChild(isAllow: any): void;
    /**
     * 添加分组子节点
     * @param id 节点id
     */
    addChild(id: any): void;
    /**
     * 删除分组子节点
     * @param id 节点id
     */
    removeChild(id: any): void;
    getAddableOutlineStyle(): {
        stroke: string;
        strokeWidth: number;
        strokeDasharray: string;
        fill: string;
    };
    getData(): import("@logicflow/core").NodeData;
}
declare class GroupNode extends RectResize.view {
    getControlGroup(): h.JSX.Element;
    getAddedableShape(): import("preact").VNode<any>;
    getFoldIcon(): import("preact").VNode<any>;
    getResizeShape(): import("preact").VNode<any>;
}
declare const _default: {
    type: string;
    view: typeof GroupNode;
    model: typeof GroupNodeModel;
};
export default _default;
