var Quantity = (function () {
    function Quantity() {
    }
    Quantity.prototype.getUom = function () {
        return this.uom;
    };
    Quantity.prototype.setUom = function (uom) {
        this.uom = uom;
    };

    Quantity.prototype.getQuantity = function () {
        return this.quantity;
    };
    Quantity.prototype.setQuantity = function (quantity) {
        this.quantity = quantity;
    };

    Quantity.prototype.getDisplayQuantity = function () {
        return this.displayQuantity;
    };
    return Quantity;
})();
