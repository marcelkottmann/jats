interface JATS {
    init(objectMap: { });
    fromJson(obj, objectMap?: { });
    find(callback: any, type: string, id: any, view?: string);
}
declare var JATS: JATS;
