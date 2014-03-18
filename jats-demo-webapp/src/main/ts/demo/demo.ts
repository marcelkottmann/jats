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
        JATS.init(CacheManager.objectsMap);
    }

    public static load(success: () => any) {
        JATS.find(function(objects: Product[]) {
            for (var i = 0; i < objects.length; i++) {
                console.log("Accepting product with id " + objects[i].getId());
            }
            success();
        }, "products", 0, "product");

    }

    public static getObject(id: number) {
        console.log("Getting object with id " + id);
        var ret = CacheManager.objectsMap[id];
        console.log("Returning " + ret);
        return ret;
    }
}

function renderProduct(product: Product, composition: Composition) {
    var directive = function(product: Product) {
		return {
            '#label': product.getLabel(),
            'li': {
                items: product.getVariants(),
                each: function(variant: Component) {
				    return {
                        ".variantLabel": variant.getLabel(),
                        ".price": formatPrice(variant.getPrice()),
                        ".actions button:nth-child(1)@data-id": variant.getId(),
                        ".actions button:nth-child(2)@data-id": variant.getId(),
                        "ul#required li ul li": {
                            items: variant.getRequiredComponents(),
                            each: (component: Component) => {
                                return {
                                    "span": component.getLabel(),
                                    ".price": formatPrice(component.getPrice()),
                                    "button:nth-child(1)@data-id": component.getId(),
                                    "button:nth-child(2)@data-id": component.getId()
                                }
                        }
                        },
                        "ul#optional li ul li": {
                            items: variant.getOptionalComponents(),
                            each: (component: Component) => {
                                return {
                                    "span": component.getLabel(),
                                    ".price": formatPrice(component.getPrice()),
                                    "button:nth-child(1)@data-id": component.getId(),
                                    "button:nth-child(2)@data-id": component.getId(),
                                    "button:nth-child(2)@class": "js_add " + (ProductDetailController.compositionManager.getConfigurator().canreduce(composition, component.getId()) ? "" : " disabled")
                                }
                            }
                        },
                        "ul#modifiers li": {
                            items: variant.getModifiers(),
                            each: (modifier: Modifier) => {
                                return {
                                    ".modifierLabel": "Modifier (max="+ modifier.getMax() + ")",
                                    "ul li": {
                                        items: modifier.getModifierEntries(),
                                        each: (modifierEntry: ModifierEntry) => {
                                            return {
                                                "span": modifierEntry.getComponent().getLabel(),
                                                ".price": formatPrice(modifierEntry.getComponent().getPrice()),
                                                "button:nth-child(1)@data-id": modifierEntry.getComponent().getId(),
                                                "button:nth-child(1)@class": "js_add " + (ProductDetailController.compositionManager.getConfigurator().canadd(composition, modifierEntry.getComponent().getId()) ? "" : " disabled"),
                                                "button:nth-child(2)@data-id": modifierEntry.getComponent().getId(),
                                                "button:nth-child(2)@class": "js_add " + (ProductDetailController.compositionManager.getConfigurator().canreduce(composition, modifierEntry.getComponent().getId()) ? "" : " disabled"),
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
	}
PUREST.render(".template", directive(product));
}

function formatPrice(p: number) {
    return p > 0 ? p.toFixed(2) + " €" : "";
}

function renderComposition(composition: Composition) {
    var directive = function(composition: Composition) {
        return {
            '#cid': composition.getComponentId(),
            '#clabel': composition.getComponent().getLabel(),
            '#pprice': formatPrice(composition.getQuantity() * composition.getComponent().getPrice()),
            '#cquantity': composition.getQuantity(),
            '#cprice': formatPrice(composition.getPrice()),
            'li': {
                items: composition.getSubComponents(),
                each: (subComposition: Composition) => {
                    return {
                        ".quantity": subComposition.getQuantity(),
                        ".label": subComposition.getComponent().getLabel(),
                        ".price": formatPrice(composition.getQuantity() * subComposition.getPrice())
                    }
                }
            }
        }
    }
    PUREST.render(".ctemplate", directive(composition));
}
