"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.SelectionSelect = void 0;
var SelectionSelect = /** @class */ (function () {
    function SelectionSelect(_a) {
        var _this = this;
        var lf = _a.lf;
        this.__disabled = false;
        this.isDefalutStopMoveGraph = false;
        this.__draw = function (ev) {
            var _a = _this.lf.getPointByClient(ev.clientX, ev.clientY).domOverlayPosition, x1 = _a.x, y1 = _a.y;
            _this.endPoint = { x: x1, y: y1 };
            var _b = _this.startPoint, x = _b.x, y = _b.y;
            var style = _this.wrapper.style;
            var left = x;
            var top = y;
            var width = x1 - x;
            var height = y1 - y;
            if (x1 < x) {
                left = x1;
                width = x - x1;
            }
            if (y1 < y) {
                top = y1;
                height = y - y1;
            }
            style.left = left + "px";
            style.top = top + "px";
            style.width = width + "px";
            style.height = height + "px";
        };
        this.__drawOff = function () {
            document.removeEventListener('mousemove', _this.__draw);
            document.removeEventListener('mouseup', _this.__drawOff);
            _this.__domContainer.removeChild(_this.wrapper);
            var _a = _this.startPoint, x = _a.x, y = _a.y;
            var _b = _this.endPoint, x1 = _b.x, y1 = _b.y;
            var lt = [Math.min(x, x1), Math.min(y, y1)];
            var rt = [Math.max(x, x1), Math.max(y, y1)];
            var elements = _this.lf.getAreaElement(lt, rt);
            elements.forEach(function (element) {
                _this.lf.selectElementById(element.id, true);
            });
            _this.lf.emit('selection:selected', elements);
        };
        this.lf = lf;
        // 初始化isDefalutStopMoveGraph取值
        var stopMoveGraph = lf.getEditConfig().stopMoveGraph;
        this.isDefalutStopMoveGraph = stopMoveGraph;
        lf.openSelectionSelect = function () {
            _this.openSelectionSelect();
        };
        lf.closeSelectionSelect = function () {
            _this.closeSelectionSelect();
        };
    }
    SelectionSelect.prototype.render = function (lf, domContainer) {
        var _this = this;
        this.__domContainer = domContainer;
        lf.on('blank:mousedown', function (_a) {
            var e = _a.e;
            var config = lf.getEditConfig();
            // 鼠标控制滚动移动画布的时候，不能选区。
            if (!config.stopMoveGraph || _this.__disabled) {
                return;
            }
            var _b = lf.getPointByClient(e.clientX, e.clientY).domOverlayPosition, x = _b.x, y = _b.y;
            _this.startPoint = { x: x, y: y };
            _this.endPoint = { x: x, y: y };
            var wrapper = document.createElement('div');
            wrapper.className = 'lf-selection-select';
            wrapper.style.top = _this.startPoint.y + "px";
            wrapper.style.left = _this.startPoint.x + "px";
            domContainer.appendChild(wrapper);
            _this.wrapper = wrapper;
            document.addEventListener('mousemove', _this.__draw);
            document.addEventListener('mouseup', _this.__drawOff);
        });
    };
    /**
     * 开启选区
     */
    SelectionSelect.prototype.openSelectionSelect = function () {
        var stopMoveGraph = this.lf.getEditConfig().stopMoveGraph;
        if (!stopMoveGraph) {
            this.isDefalutStopMoveGraph = false;
            this.lf.updateEditConfig({
                stopMoveGraph: true,
            });
        }
        this.open();
    };
    /**
     * 关闭选区
     */
    SelectionSelect.prototype.closeSelectionSelect = function () {
        if (!this.isDefalutStopMoveGraph) {
            this.lf.updateEditConfig({
                stopMoveGraph: false,
            });
        }
        this.close();
    };
    SelectionSelect.prototype.open = function () {
        this.__disabled = false;
    };
    SelectionSelect.prototype.close = function () {
        this.__disabled = true;
    };
    SelectionSelect.pluginName = 'selectionSelect';
    return SelectionSelect;
}());
exports.SelectionSelect = SelectionSelect;
