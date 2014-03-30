[ {
	"$type" : "Component",
	"id" : 1,
	"label" : "Pizza Hawaii Groß",
	"modifiers" : [ {
		"$type" : "Modifier",
		"id" : 1001,
		"minPerModifier" : 0,
		"maxPerModifier" : 2147483647,
		"minPerComponent" : 0,
		"maxPerComponent" : 3,
		"defaultQuantity" : 0,
		"defaultIncluded" : true,
		"modifierEntries" : [ {
			"$type" : "ModifierEntry",
			"id" : 1002,
			"component" : {
				"$type" : "Component",
				"id" : 1003,
				"label" : "Peperoni",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		}, {
			"$type" : "ModifierEntry",
			"id" : 1004,
			"component" : {
				"$type" : "Component",
				"id" : 1005,
				"label" : "Champignons",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		}, {
			"$type" : "ModifierEntry",
			"id" : 1006,
			"component" : {
				"$type" : "Component",
				"id" : 1007,
				"label" : "Tomaten",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		}, {
			"$type" : "ModifierEntry",
			"id" : 1008,
			"component" : {
				"$type" : "Component",
				"id" : 1009,
				"label" : "Paprika",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		}, {
			"$type" : "ModifierEntry",
			"id" : 1010,
			"component" : {
				"$type" : "Component",
				"id" : 1011,
				"label" : "Käse",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		} ]
	}, {
		"$type" : "Modifier",
		"id" : 1012,
		"minPerModifier" : 0,
		"maxPerModifier" : 2147483647,
		"minPerComponent" : 0,
		"maxPerComponent" : 1,
		"defaultQuantity" : 0,
		"defaultIncluded" : true,
		"modifierEntries" : [ {
			"$type" : "ModifierEntry",
			"id" : 1013,
			"component" : {
				"$type" : "Component",
				"id" : 1014,
				"label" : "Scampi",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		}, {
			"$type" : "ModifierEntry",
			"id" : 1015,
			"component" : {
				"$type" : "Component",
				"id" : 1016,
				"label" : "Rinderstreifen",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		}, {
			"$type" : "ModifierEntry",
			"id" : 1017,
			"component" : {
				"$type" : "Component",
				"id" : 1018,
				"label" : "Thunfisch",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		} ]
	}, {
		"$type" : "Modifier",
		"id" : 1020,
		"minPerModifier" : 1,
		"maxPerModifier" : 1,
		"minPerComponent" : 0,
		"maxPerComponent" : 0,
		"defaultQuantity" : 1,
		"defaultIncluded" : true,
		"modifierEntries" : [ {
			"$type" : "ModifierEntry",
			"id" : 1021,
			"component" : {
				"$type" : "Component",
				"id" : 1019,
				"label" : "Teig",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		} ]
	}, {
		"$type" : "Modifier",
		"id" : 1022,
		"minPerModifier" : 0,
		"maxPerModifier" : 2147483647,
		"minPerComponent" : 0,
		"maxPerComponent" : 1,
		"defaultQuantity" : 1,
		"defaultIncluded" : true,
		"modifierEntries" : [ {
			"$type" : "ModifierEntry",
			"id" : 1023,
			"component" : {
				"$type" : "Component",
				"id" : 1024,
				"label" : "Schinken",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		}, {
			"$type" : "ModifierEntry",
			"id" : 1025,
			"component" : {
				"$type" : "Component",
				"id" : 1026,
				"label" : "Ananas",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		}, {
			"$type" : "ModifierEntry",
			"id" : 1027,
			"component" : {
				"$type" : "Component",
				"id" : 1028,
				"label" : "Oregano",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		}, {
			"$type" : "ModifierEntry",
			"id" : 1029,
			"component" : {
				"$type" : "Component",
				"id" : 1030,
				"label" : "Würzige Tomatensauce",
				"custom" : {
					"$type" : "Component"
				},
				"variantValueList" : []
			}
		} ]
	} ],
	"custom" : {},
	"variants" : [ {
		"$ref" : "1",
		"$type" : "Component"
	}, {
		"$type" : "Component",
		"id" : 2,
		"label" : "Pizza Hawaii Klein",
		"modifiers" : [ {
			"$ref" : "1001",
			"$type" : "Modifier"
		}, {
			"$ref" : "1012",
			"$type" : "Modifier"
		}, {
			"$ref" : "1020",
			"$type" : "Modifier"
		}, {
			"$ref" : "1022",
			"$type" : "Modifier"
		} ],
		"custom" : {
			"$type" : "Component"
		},
		"variants" : [ {
			"$ref" : "1",
			"$type" : "Component"
		}, {
			"$ref" : "2",
			"$type" : "Component"
		} ],
		"variantAttributes" : [ {
			"$type" : "VariantAttribute",
			"property" : "label",
			"label" : "Name"
		} ],
		"variantValueList" : [ {
			"$type" : "VariantValueList",
			"variantAttribute" : {
				"$type" : "VariantAttribute",
				"property" : "label",
				"label" : "Name"
			},
			"variantValues" : [ {
				"$type" : "VariantValue",
				"variants" : [ {
					"$ref" : "1",
					"$type" : "Component"
				} ],
				"value" : "Pizza Hawaii Groß"
			}, {
				"$type" : "VariantValue",
				"variants" : [ {
					"$ref" : "2",
					"$type" : "Component"
				} ],
				"value" : "Pizza Hawaii Klein"
			} ]
		} ]
	} ],
	"variantAttributes" : [ {
		"$type" : "VariantAttribute",
		"property" : "label",
		"label" : "Name"
	} ],
	"variantValueList" : [ {
		"$type" : "VariantValueList",
		"variantAttribute" : {
			"$type" : "VariantAttribute",
			"property" : "label",
			"label" : "Name"
		},
		"variantValues" : [ {
			"$type" : "VariantValue",
			"variants" : [ {
				"$ref" : "1",
				"$type" : "Component"
			} ],
			"value" : "Pizza Hawaii Groß"
		}, {
			"$type" : "VariantValue",
			"variants" : [ {
				"$ref" : "2",
				"$type" : "Component"
			} ],
			"value" : "Pizza Hawaii Klein"
		} ]
	} ]
} ]