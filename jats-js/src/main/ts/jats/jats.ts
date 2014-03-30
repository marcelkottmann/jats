///<reference path='../lib/jquery.d.ts' />
///<reference path='../lib/underscore.d.ts' />

class JATS {

    public static OBJECT_MAP: { [index: string]: any; } = {};

    public static init(objectMap: { [index: string]: any; }) {
        JATS.OBJECT_MAP = objectMap;
    }

    public static fromJson(obj: any, objectMap?: { [index: string]: any; }, view?: string): any {

        if (typeof objectMap === "undefined") {
            objectMap = JATS.OBJECT_MAP;
        }

        if (typeof obj === "undefined") {
            return;
        }
        else if (obj == null) {
            return null;
        }
        else {
            var type: string = obj["$type"];
            if (typeof type !== "undefined") {
                var constr = new Function("return new " + type + "();");
                obj = _.extend(constr(), obj);
            }

            var id = obj["id"];
            if (typeof id !== "undefined") {
                //obj has id property
                objectMap[JATS.createCacheKey(type, id, view)] = obj;
            }

            var ref: string = obj["$ref"];
            if (typeof ref !== "undefined") {
                //obj has #ref property
                //locate referenced id from object Map and return it
                return objectMap[JATS.createCacheKey(type, ref, view)];
            }

            for (var prop in obj) {
                if (typeof obj[prop] === "object") {
                    obj[prop] = this.fromJson(obj[prop], objectMap, view);
                }
            }
            return obj;
        }
    }

    private static createCacheKey(type: string, id: any, view: string): string
    {
        return type + "/" + id + (view == null ? "" : ("/" + view));
    }
    
    public static find(callback: any, type: string, id: any, view?: string) {
        var key = JATS.createCacheKey(type, id, view);
        var cached: any = JATS.OBJECT_MAP[key];
        if (cached != null) {
            setTimeout(() => {
                var result:any[] = [cached]; 
                callback(result);
            }, 0);
        }
        else {
            $.ajax("/jats/" + type + "/" + id + "/" + view, {
                dataType: "text",
                success: (data) => {
                    callback(this.fromJson(eval(data), JATS.OBJECT_MAP, view));
                }
            });
        }
    }

}