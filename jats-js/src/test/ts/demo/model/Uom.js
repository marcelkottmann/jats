var Uom = (function () {
    function Uom() {
    }
    Uom.prototype.getId = function () {
        return this.id;
    };
    Uom.prototype.setId = function (id) {
        this.id = id;
    };

    Uom.prototype.getCode = function () {
        return this.code;
    };
    Uom.prototype.setCode = function (code) {
        this.code = code;
    };
    return Uom;
})();
