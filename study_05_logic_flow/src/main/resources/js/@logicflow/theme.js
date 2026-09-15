let theme = {
    //矩形
    rect: {
        width: 100,//宽度 默认 100
        height: 80,//高度 默认 80
        radius: 0,//圆角弧度 默认 0
        fill: '#FFFFFF',//填充颜色 默认 #FFFFFF
        fillOpacity: 1,//填充透明度 默认 1
        stroke: '#000000',//边框颜色 默认 #000000
        strokeWidth: 2,//边框宽度 默认 2
        strokeOpacity: 1,//边框透明度 默认 1
        opacity: 1,//整体透明度 默认 1
        outlineColor: '#000000',//外边框颜色 默认 #000000
        hoverOutlineColor: '#000000',//hover外边框颜色 默认 #000000
        outlineStrokeDashArray: '3,3',//控制用来描外边框的点划线的图案范式，设置为空时为实线 默认 '3,3'
        hoverOutlineStrokeDashArray: '3,3'//控制用来描hover外边框的点划线的图案范式，设置为空时为实线 '3,3'
    },
    //圆形
    circle: {
        r: 50,//半径 默认 50
        fill: '#FFFFFF',//填充颜色 默认 #FFFFFF
        fillOpacity: 1,//填充透明度 默认 1
        stroke: '#000000',//边框颜色 默认 #000000
        strokeWidth: 2,//边框宽度 默认 2
        strokeOpacity: 1,//边框透明度 默认 1
        opacity: 1,//整体透明度 默认 1
        outlineColor: '#000000',//外边框颜色 默认 #000000
        hoverOutlineColor: '#000000',//hover外边框颜色 默认 #000000
        outlineStrokeDashArray: '3,3',//控制用来描外边框的点划线的图案范式，设置为空时为实线 默认 3,3
        hoverOutlineStrokeDashArray: '3,3'//控制用来描hover外边框的点划线的图案范式，设置为空时为实线 默认 3,3
    },
    //椭圆
    ellipse: {
        rx: 50,//x轴尺寸 默认 55
        ry: 50,//y轴尺寸 默认 45
        fill: '#FFFFFF',//填充颜色 默认 #FFFFFF
        fillOpacity: 1,//填充透明度 默认 1
        stroke: '#000000',//边框颜色 默认 #000000
        strokeWidth: 2,//边框宽度 默认 2
        strokeOpacity: 1,//边框透明度 默认 1
        opacity: 1,//整体透明度 默认 1
        outlineColor: '#000000',//外边框颜色 默认 #000000
        hoverOutlineColor: '#000000',//hover外边框颜色 默认 #000000
        outlineStrokeDashArray: '3,3',//控制用来描外边框的点划线的图案范式，设置为空时为实线 默认 3,3
        hoverOutlineStrokeDashArray: '3,3'//控制用来描hover外边框的点划线的图案范式，设置为空时为实线 默认 3,3
    },
    //菱形
    diamond: {
        rx: 50,//x轴尺寸 默认 50
        ry: 50,//y轴尺寸 默认 50
        fill: '#FFFFFF',//填充颜色 默认 #FFFFFF
        fillOpacity: 1,//填充透明度 默认 1
        stroke: '#000000',//边框颜色 默认 #000000
        strokeWidth: 2,//边框宽度 默认 2
        strokeOpacity: 1,//边框透明度 默认 1
        opacity: 1,//整体透明度 默认 1
        outlineColor: '#000000',//外边框颜色 默认 #000000
        hoverOutlineColor: '#000000',//hover外边框颜色 默认 #000000
        outlineStrokeDashArray: '3,3',//控制用来描外边框的点划线的图案范式，设置为空时为实线 默认 '3,3'
        hoverOutlineStrokeDashArray: '3,3'//控制用来描hover外边框的点划线的图案范式，设置为空时为实线 默认 '3,3'
    },
    //多边形
    polygon: {
        r: 4,//半径 默认 4
        fill: '#FFFFFF',//填充颜色 默认 #FFFFFF
        fillOpacity: 1,//填充透明度 默认 1
        stroke: '#000000',//边框颜色 默认 #000000
        strokeWidth: 1,//边框宽度 默认 1
        strokeOpacity: 1,//边框透明度 默认 1
        opacity: 1,//整体透明度 默认 1
        outlineColor: '#000000',//外边框颜色 默认 #000000
        outlineStrokeDashArray: '3,3'//控制用来描外边框的点划线的图案范式，谁知为空时为实线 默认'3,3'
    },
    //锚点hover
    anchorHover: {
        r: 10,//半径 默认 10
        fill: '#1E90FF',//填充颜色 默认 #1E90FF
        fillOpacity: 0.5,//填充透明度 默认 0.5
        stroke: '#4169E1',//边框颜色 默认 #4169E1
        strokeWidth: 1,//边框宽度 默认 1
        strokeOpacity: 1,//边框透明度 默认 1
        opacity: 1//整体透明度 默认 1
    },
    //锚点连线
    anchorLine: {
        stroke: '#000000',//连线颜色 默认 #000000
        strokeWidth: 2,//连线宽度 默认 2
        strokeDasharray: '3,2'//图案范式 默认 3,2
    },
    //直线
    line: {
        stroke: '#000000',//连线颜色 默认 #000000
        strokeWidth: 2,//连线宽度 默认 2
        strokeDashArray: '1,0',//控制连线的点划线的图案范式，设置为空时为实线 默认 1,0
        hoverStroke: '#000000',//连线hover颜色 默认 #000000
        selectedStroke: '#000000',//连线选中颜色 默认 #000000
        outlineColor: '#000000',//外边框颜色 默认 #000000
        outlineStrokeDashArray: '3,3'//控制用来描外边框的电话线的图案范式，设置为空时为实线 默认 3,3
    },
    //折线
    polyline: {
        stroke: '#000000',//连线颜色 默认 #000000
        strokeWidth: 2,//连线宽度 默认 2
        strokeDashArray: '1,0',//控制连线的点划线的图案范式，设置为空时为实线 默认 1,0
        hoverStroke: '#000000',//连线hover颜色 默认 #000000
        selectedStroke: '#000000',//连线选中颜色 默认 #000000
        outlineColor: '#000000',//外边框颜色 默认 #000000
        offset: 30,//折线起终点距离节点的偏移 默认 30
        outlineStrokeDashArray: '3,3'//控制用来描外边框的电话线的图案范式，设置为空时为实线 默认 3,3
    },
    //连线文本
    edgeText: {
        color: '#000000',//字体颜色 默认 #000000
        fontSize: 12,//字体大小 默认 1
        fontWeight: 'normal',//字体粗细 默认 normal
        fontFamily: '',//字体名称 默认 ''
        background: {//文本背景(矩形)
            fill: 'transparent',//填充颜色 默认 transparent
            height: 20,//高度 默认 20
            stroke: 'transparent',//边框颜色 默认 transparent
            radius: 0//圆角弧度 默认 0
        },
        hoverBackground: {//选中时文本背景(矩形)
            fill: 'transparent',//填充颜色 默认 transparent
            heigth: 20,//高度 默认 20
            stroke: 'transparent',//边框颜色 默认 transparent
            radius: 0//圆角弧度 默认 0
        },
        dx: null,//水平偏移量 默认 None
        dy: null,//垂直偏移量 默认 None
        textAnchor: 'inherit',//对齐方式 默认 inherit
        rotate: 0,//文字旋转角度 默认 0
        textLength: null,//文本长度 默认 None
        lengthAdjust: 'spacing'//文本拉伸或压缩方式 默认 spacing
    },
    //文本
    text: {
        color: '#000000',//字体颜色 默认 #000000
        fontSize: 12,//字体大小 默认 1
        fontWeight: 'normal',//字体粗细 默认 normal
        fontFamily: ''//字体名称 默认 ''
    },
    //节点文本
    nodeText: {
        color: '#000000',//字体颜色 默认 #000000
        fontSize: 12,//字体大小 默认 1
        fontWeight: 'normal',//字体粗细 默认 normal 支持数值和字符
        fontFamily: '',//字体名称 默认 ""
        dx: null,//水平偏移量 默认 None
        dy: null,//垂直偏移量 默认 None
        textAnchor: 'inherit',//对齐方式 默认 'inherit'
        rotate: 0,//文字旋转角度 默认 0
        textLength: null,//文本长度 默认 None
        lengthAdjust: 'spacing'//文本伸缩方式 默认 'spacing'
    },
    //箭头
    arrow: {
        offset: 10,//箭头长度 默认 10
        verticalLength: 5//箭头垂直于连线的距离 默认 5
    },
    //对齐线
    snapline: {
        stroke: '#1E90FF',//对齐线颜色 默认 #1E90FF
        strokeWidth: 1//对齐线宽度 默认 1
    }
}