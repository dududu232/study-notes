"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
var __read = (this && this.__read) || function (o, n) {
    var m = typeof Symbol === "function" && o[Symbol.iterator];
    if (!m) return o;
    var i = m.call(o), r, ar = [], e;
    try {
        while ((n === void 0 || n-- > 0) && !(r = i.next()).done) ar.push(r.value);
    }
    catch (error) { e = { error: error }; }
    finally {
        try {
            if (r && !r.done && (m = i["return"])) m.call(i);
        }
        finally { if (e) throw e.error; }
    }
    return ar;
};
var __spread = (this && this.__spread) || function () {
    for (var ar = [], i = 0; i < arguments.length; i++) ar = ar.concat(__read(arguments[i]));
    return ar;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@logicflow/core");
var NodeResize_1 = require("../../NodeResize");
var defaultWidth = 500;
var defaultHeight = 300;
var GroupNodeModel = /** @class */ (function (_super) {
    __extends(GroupNodeModel, _super);
    function GroupNodeModel() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.isGroup = true;
        _this.unfoldedWidth = defaultWidth;
        _this.unfoldedHight = defaultHeight;
        return _this;
    }
    GroupNodeModel.prototype.initNodeData = function (data) {
        _super.prototype.initNodeData.call(this, data);
        var children = [];
        if (Array.isArray(data.children)) {
            children = data.children;
        }
        // 初始化组的子节点
        this.children = new Set(children);
        this.width = defaultWidth;
        this.height = defaultHeight;
        this.foldedWidth = 80;
        this.foldedHeight = 60;
        // todo: 参考bpmn.js, 分组和未加入分组的节点重合时，未加入分组的节点在分组之下。方便标识。
        this.zIndex = -1;
        this.radius = 0;
        this.text.editable = false;
        this.text.draggable = false;
        this.isRestrict = false;
        this.resizable = false;
        this.autoToFront = false;
        this.foldable = false;
        this.properties.isFolded = false;
        this.isFolded = this.properties.isFolded;
    };
    GroupNodeModel.prototype.getResizeOutlineStyle = function () {
        var style = _super.prototype.getResizeOutlineStyle.call(this);
        style.stroke = 'none';
        return style;
    };
    GroupNodeModel.prototype.foldGroup = function (isFolded) {
        var _this = this;
        this.setProperty('isFolded', isFolded);
        this.isFolded = isFolded;
        if (isFolded) {
            this.x = this.x - this.width / 2 + this.foldedWidth / 2;
            this.y = this.y - this.height / 2 + this.foldedHeight / 2;
            this.unfoldedWidth = this.width;
            this.unfoldedHight = this.height;
            this.width = this.foldedWidth;
            this.height = this.foldedHeight;
        }
        else {
            this.width = this.unfoldedWidth;
            this.height = this.unfoldedHight;
            this.x = this.x + this.width / 2 - this.foldedWidth / 2;
            this.y = this.y + this.height / 2 - this.foldedHeight / 2;
        }
        // 移动分组上的连线
        var inCommingEdges = this.graphModel.getNodeIncomingEdge(this.id);
        var outgoingEdges = this.graphModel.getNodeOutgoingEdge(this.id);
        inCommingEdges.concat(outgoingEdges).forEach(function (edgeModel) {
            _this.graphModel.deleteEdgeById(edgeModel.id);
            if (!edgeModel.isFoldedEdge) {
                var isCommingEdge = edgeModel.targetNodeId === _this.id;
                var data = edgeModel.getData();
                if (isCommingEdge) {
                    data.endPoint = undefined;
                }
                else {
                    data.startPoint = undefined;
                }
                data.pointsList = undefined;
                _this.graphModel.addEdge(data);
            }
        });
        this.children.forEach(function (elementId) {
            var nodeModel = _this.graphModel.getElement(elementId);
            nodeModel.visible = !isFolded;
            _this.foldEdge(elementId, isFolded);
        });
    };
    /**
     * 折叠分组的时候，处理分组内部子节点上的连线
     * 1. 为了保证校验规则不被打乱，所以只隐藏子节点上面的连线。
     * 2. 重新创建一个属性一样的边。
     * 3. 这个边拥有virtual=true的属性，表示不支持直接修改此边内容。
     */
    GroupNodeModel.prototype.foldEdge = function (nodeId, isFolded) {
        var _this = this;
        var inCommingEdges = this.graphModel.getNodeIncomingEdge(nodeId);
        var outgoingEdges = this.graphModel.getNodeOutgoingEdge(nodeId);
        inCommingEdges.concat(outgoingEdges).forEach(function (edgeModel, index) {
            var _a;
            edgeModel.visible = !isFolded;
            if (isFolded
                && (!_this.children.has(edgeModel.targetNodeId)
                    || !_this.children.has(edgeModel.sourceNodeId))) {
                var isCommingEdge = edgeModel.targetNodeId === nodeId;
                if (isFolded) {
                    var data = edgeModel.getData();
                    data.id = data.id + "__" + index;
                    if (isCommingEdge) {
                        data.endPoint = undefined;
                        data.targetNodeId = _this.id;
                    }
                    else {
                        data.startPoint = undefined;
                        data.sourceNodeId = _this.id;
                    }
                    data.text = (_a = data.text) === null || _a === void 0 ? void 0 : _a.value;
                    data.pointsList = undefined;
                    var model = _this.graphModel.addEdge(data);
                    model.virtual = true;
                    // 强制不保存group连线数据
                    model.getData = function () { return null; };
                    model.text.editable = false;
                    model.isFoldedEdge = true;
                }
            }
        });
    };
    GroupNodeModel.prototype.isInRange = function (_a) {
        var x1 = _a.x1, y1 = _a.y1, x2 = _a.x2, y2 = _a.y2;
        return x1 >= (this.x - this.width / 2)
            && x2 <= (this.x + this.width / 2)
            && y1 >= (this.y - this.height / 2)
            && y2 <= (this.y + this.height / 2);
    };
    GroupNodeModel.prototype.setAllowAppendChild = function (isAllow) {
        this.setProperty('groupAddable', isAllow);
    };
    /**
     * 添加分组子节点
     * @param id 节点id
     */
    GroupNodeModel.prototype.addChild = function (id) {
        this.children.add(id);
    };
    /**
     * 删除分组子节点
     * @param id 节点id
     */
    GroupNodeModel.prototype.removeChild = function (id) {
        this.children.delete(id);
    };
    GroupNodeModel.prototype.getAddableOutlineStyle = function () {
        return {
            stroke: '#FEB663',
            strokeWidth: 2,
            strokeDasharray: '4 4',
            fill: 'transparent',
        };
    };
    GroupNodeModel.prototype.getData = function () {
        var data = _super.prototype.getData.call(this);
        data.children = __spread(this.children);
        var properties = data.properties;
        delete properties.groupAddable;
        delete properties.isFolded;
        return data;
    };
    return GroupNodeModel;
}(NodeResize_1.RectResize.model));
var GroupNode = /** @class */ (function (_super) {
    __extends(GroupNode, _super);
    function GroupNode() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    GroupNode.prototype.getControlGroup = function () {
        var _a = this.props.model, resizable = _a.resizable, properties = _a.properties;
        return resizable && !properties.isFolded ? _super.prototype.getControlGroup.call(this) : null;
    };
    GroupNode.prototype.getAddedableShape = function () {
        var _a = this.props.model, width = _a.width, height = _a.height, x = _a.x, y = _a.y, radius = _a.radius, properties = _a.properties;
        if (!properties.groupAddable)
            return null;
        var strokeWidth = this.props.model.getNodeStyle().strokeWidth;
        var style = this.props.model.getAddableOutlineStyle();
        var newWidth = width + strokeWidth + 8;
        var newHeight = height + strokeWidth + 8;
        return core_1.h('rect', __assign(__assign({}, style), { width: newWidth, height: newHeight, x: x - newWidth / 2, y: y - newHeight / 2, rx: radius, ry: radius }));
    };
    GroupNode.prototype.getFoldIcon = function () {
        var model = this.props.model;
        var foldX = model.x - model.width / 2 + 5;
        var foldY = model.y - model.height / 2 + 5;
        if (!model.foldable)
            return null;
        var iconIcon = core_1.h('path', {
            fill: 'none',
            stroke: '#818281',
            strokeWidth: 2,
            'pointer-events': 'none',
            d: model.properties.isFolded
                ? "M " + (foldX + 3) + "," + (foldY + 6) + " " + (foldX + 11) + "," + (foldY + 6) + " M" + (foldX + 7) + "," + (foldY + 2) + " " + (foldX + 7) + "," + (foldY + 10)
                : "M " + (foldX + 3) + "," + (foldY + 6) + " " + (foldX + 11) + "," + (foldY + 6) + " ",
        });
        return core_1.h('g', {}, [
            core_1.h('rect', {
                height: 12,
                width: 14,
                rx: 2,
                ry: 2,
                strokeWidth: 1,
                fill: '#F4F5F6',
                stroke: '#CECECE',
                cursor: 'pointer',
                x: model.x - model.width / 2 + 5,
                y: model.y - model.height / 2 + 5,
                onClick: function () {
                    model.foldGroup(!model.properties.isFolded);
                },
            }),
            iconIcon,
        ]);
    };
    GroupNode.prototype.getResizeShape = function () {
        return core_1.h('g', {}, [
            this.getAddedableShape(),
            _super.prototype.getResizeShape.call(this),
            this.getFoldIcon(),
        ]);
    };
    return GroupNode;
}(NodeResize_1.RectResize.view));
exports.default = {
    type: 'group',
    view: GroupNode,
    model: GroupNodeModel,
};
