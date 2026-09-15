import LogicFlow from '@logicflow/core';
declare class SelectionSelect {
    __domContainer: HTMLElement;
    wrapper: HTMLElement;
    lf: LogicFlow;
    startPoint: {
        x: number;
        y: number;
    };
    endPoint: {
        x: number;
        y: number;
    };
    __disabled: boolean;
    isDefalutStopMoveGraph: boolean;
    static pluginName: string;
    constructor({ lf }: {
        lf: any;
    });
    render(lf: any, domContainer: any): void;
    /**
     * 开启选区
     */
    openSelectionSelect(): void;
    /**
     * 关闭选区
     */
    closeSelectionSelect(): void;
    __draw: (ev: any) => void;
    __drawOff: () => void;
    open(): void;
    close(): void;
}
export { SelectionSelect };
