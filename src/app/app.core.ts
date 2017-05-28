export class Sample {
    key: string;
    family: string;
    values: Array<any>;
    col;
    
    constructor(key:string, family:string, values:Array<any>, col) {
        this.key = key;
        this.family = family;
        this.values = values;
        this.col = col;
    }
}
