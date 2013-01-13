///<reference path='../project/project.ts' />
///<reference path='../lib/purest.d.ts' />
///<reference path='../lib/jats.d.ts' />
///<reference path='../logic/Composition.ts' />
///<reference path='../logic/CompositionManager.ts' />
///<reference path='../logic/CompositionConfigurator.ts' />
///<reference path='../productdetail/ProductDetailController.ts' />


class CacheManager {

    public static objectsMap = {};

    public static init() {
        JATS.init(objectsMap);        
    }

    public static load(success:()=>any) {
        JATS.find(function (objects:Product[]) { 
            for (var i = 0; i < objects.length; i++) {
                console.log("Accepting product with id " + objects[i].getId());
            }
            success();
        }, "products", 0, "product");
        
    }

    public static getObject(id: number) {
        console.log("Getting object with id " + id);
        var ret=objectsMap[id];
        console.log("Returning "+ret);
        return ret;
    }
}

function renderProduct(product : Product) {
	var directive = function(product : Product) {
		return {
			'#label' : product.getLabel(),
			'li' : {
				items : product.getVariants(),
				each: function (variant: Component) {
				    return {
                        ".variantLabel":variant.getLabel(),
                        "ul li": {
                            items : variant.getModifiers(),
                            each: (modifier: Modifier) =>
                            {
                                return {
                                    ".modifierLabel" : "Modifier ID: "+modifier.getId(),
                                    ".modifierLabel+" : "<!-- "+modifier.getMin() +"/" + modifier.getMax() +" -->",
                                    "ul li": {
                                        items : modifier.getModifierEntries(),
                                        each: (modifierEntry: ModifierEntry) =>
                                        {
                                            return modifierEntry.getComponent().getLabel();
                                        }
                                    }
                                }
                            }
                        }
				    }
				}
			}
		}
	}
	PUREST.render(".template", directive(product));
}
