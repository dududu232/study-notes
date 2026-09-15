class CustomStartView extends StartEventView{

}
class CustomStartModel extends StartEventModel{
    constructor(data,graplModel){

        if (!data.text){
            data.text = "开始";
        }

        if (typeof data.text === "string"){
            data.text = {
                value: data.text,
                x:data.text,
                y:data.y + 40
            }
        }

        super(data,graplModel);
    }

    setAttributes(){
        this.r = 20;
    }

    initNodeData(data){
        super.initNodeData(data)
    }


    getTextStyle(){
        const style = super.getTextStyle;
        return style;
    }

    getNodeStyle(){
        var style = super.getNodeStyle()
        let flowStatus = this.propertis.flowStatus;
        if (flowStatus){
            if (flowStatus != '拟稿'){
                style.stroke = "#1ab394";
            }
        }
        return style;
    }

}

var customStartEvent = {
    type: "customStartEvent",
    view: CustomStartView,
    model: CustomStartModel
}
