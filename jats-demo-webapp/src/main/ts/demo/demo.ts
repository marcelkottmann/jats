///<reference path='../project/project.ts' />
///<reference path='../lib/purest.d.ts' />
///<reference path='../lib/jats.d.ts' />
///<reference path='../logic/Composition.ts' />
///<reference path='../logic/CompositionManager.ts' />
///<reference path='../logic/CompositionConfigurator.ts' />
///<reference path='../logic/PriceManager.ts' />
///<reference path='../productdetail/ProductDetailController.ts' />


function renderProduct(product: Component, composition: Composition) {

    var directive = function(product: Component) {
		return {
            '@style': '',
            '#label': product.getId(),
            'p#variants option': {
                items: product.getVariants(),
                each: function(variant: Component) {
                    return {
                        "": variant.getLabel() + " (" + variant.getVariantValues().join(", ") + ")",
                        "@value": variant.getId(),
                        "@selected": { add: product.getId() == variant.getId(), "value": "selected" }
                    }
                }
            },
            "ul#variantvalues li": {
                items: product.getVariantValueList(),
                each: function(variantValueList: VariantValueList, status: { index: number; length: number }) {
                    return {
                        "span": variantValueList.getVariantAttribute().getLabel(),
                        "option": {
                            items: variantValueList.getVariantValues(),
                            each: function(variantValue: VariantValue) {
                                return {
                                    "": variantValue.getValue(),
                                    "@value": variantValue.getValue(),
                                    "@selected": {
                                        add: variantValue.getValue() == product.getVariantValues()[status.index], "value": "selected"
                                    }
                                }
                            }
                        }
                    }
                }
            },
            ".variantLabel": product.getLabel(),
            ".price": formatPrice(PriceManager.findPrice(product, composition.getUom())),
            ".actions button:nth-child(1)@data-id": product.getId(),
            ".actions button:nth-child(1)@data-type": "Component",
            ".actions button:nth-child(2)@data-id": product.getId(),
            ".actions button:nth-child(2)@data-type": "Component",
            "ul#modifiers li": {
                items: product.getModifiers(),
                each: (modifier: Modifier) => {
                                return {
                        ".modifierLabel": modifier.getId(),
                        "ul li": {
                            items: modifier.getModifierEntries(),
                            each: (modifierEntry: ModifierEntry) => {
                                            return {
                                    "span": modifierEntry.getComponent().getLabel(),
                                    ".price": formatPrice(PriceManager.findPrice(modifierEntry.getComponent(), composition.getUom())),
                                    "button:nth-child(1)@data-id": modifierEntry.getComponent().getId(),
                                    "button:nth-child(1)@data-type": "Component",
                                    "button:nth-child(1)@class": "js_add " + (ProductDetailController.compositionConfigurator.canadd(composition, modifierEntry.getComponent().getId()) ? "" : " disabled"),
                                    "button:nth-child(2)@data-id": modifierEntry.getComponent().getId(),
                                    "button:nth-child(2)@data-type": "Component",
                                    "button:nth-child(2)@class": "js_add " + (ProductDetailController.compositionConfigurator.canreduce(composition, modifierEntry.getComponent().getId()) ? "" : " disabled"),
                                }
                                }
                        }
                    }
                }
            }
        }
	}
PUREST.render(".template", directive(product), ".configurator");
}

function formatPrice(p: number) {
    return p > 0 ? p.toFixed(2) + " €" : "";
}

function renderComposition(composition: Composition) {
    var directive = function(composition: Composition) {
        return {
            '@style': '',
            '#cid': composition.getComponentId(),
            '#clabel': composition.getComponent().getLabel(),
            '#pprice': formatPrice(composition.getQuantity() * PriceManager.findPrice(composition.getComponent(), composition.getUom())),
            '#cquantity': composition.getQuantity(),
            '#cprice': formatPrice(composition.getPrice()),
            'li': {
                items: composition.getSubComponents(),
                each: (subComposition: Composition) => {
                    return {
                        ".quantity": subComposition.getQuantity(),
                        ".label": subComposition.getComponent().getLabel(),
                        ".price": formatPrice(composition.getQuantity() * //
                            subComposition.getPrice(
                                Math.max(0,
                                    subComposition.getQuantity() -
                                    CompositionConfigurator.getIncludedQuantity(composition.getComponent(), subComposition.getComponentId())
                                    )
                                )
                            )
                    }
                }
            }
        }
    }
    PUREST.render(".ctemplate", directive(composition), ".summary");
}

function renderNavi(category: Category) {
    var directive = function(category: Category) {
        return {
            'a': {
                items: category.getProducts(),
                each: (product: Component) => {
                    return {
                        "@data-productid": product.getId(),
                        "": product.getLabel()
                    }
                }
            }
        }
    }
    PUREST.render(".navi", directive(category));
}
