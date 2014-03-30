interface JATS {
    init(objectMap: { }): void;
    fromJson(obj: any, objectMap?: { [index: string]: any; }, view?: string): any;
    find(callback: any, type: string, id: any, view?: string): void; 
}
declare var JATS: JATS;
