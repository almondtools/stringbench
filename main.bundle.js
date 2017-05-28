webpackJsonp([1,4],{

/***/ 164:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(72);


/***/ }),

/***/ 71:
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = 71;


/***/ }),

/***/ 72:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__(77);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__(80);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__(81);




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["enableProdMode"])();
}
__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 78:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(47);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_common__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_core__ = __webpack_require__(79);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Chart; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var Chart = (function () {
    function Chart(http, location) {
        var _this = this;
        this.colors = [
            ["#00ff00", "#00ff88", "#00aa00", "#008800", "#00aa44"],
            ["#0000ff", "#8800ff", "#0000aa", "#000088", "#4400aa"],
            ["#ff0000", "#ff8800", "#aa0000", "#880000", "#aa4400"],
            ["#00ffff", "#0088ff", "#00aaaa", "#008888", "#0044aa"],
            ["#ff00ff", "#ff0088", "#aa00aa", "#880088", "#aa0044"],
            ["#ffff00", "#88ff00", "#aaaa00", "#888800", "#44aa00"] //
        ];
        this.margin = {
            top: 20,
            right: 20,
            bottom: 30,
            left: 40
        };
        this.width = 960 - this.margin.left - this.margin.right;
        this.height = 500 - this.margin.top - this.margin.bottom;
        this.data = [];
        this.benchmarks = {};
        var path = location.path(true);
        var key = /.*#(.*)/.exec(path);
        var fileName = key && key[1] ? key[1] + ".json" : "latest.json";
        console.log(fileName);
        var path = location.prepareExternalUrl("/assets/" + fileName);
        http.request(path)
            .subscribe(function (response) { return _this.buildData(response.json()); });
    }
    Chart.prototype.update = function (selected) {
        this.selected = selected;
        this.data = this.benchmarks[selected];
    };
    Chart.prototype.mapByFamily = function (data) {
        var map = {};
        for (var j = 0; j < data.length; j++) {
            var entry = data[j];
            var key = entry.algorithm;
            var family = entry.family;
            var values = map[family];
            if (!values) {
                values = [key];
                map[family] = values;
            }
            else {
                values.push(key);
            }
        }
        return map;
    };
    Chart.prototype.computeColoring = function (map) {
        var dom = [];
        var rng = [];
        var c = 0;
        for (var fam in map) {
            var algs = map[fam];
            for (var i in algs) {
                dom.push(algs[i]);
                rng.push(this.colors[c][i]);
            }
            c += 1;
        }
        return d3.scale.ordinal().domain(dom).range(rng);
    };
    Chart.prototype.benchmarkName = function (number, type) {
        var n = Number(number);
        var unit = n <= 1 ? " string " : " strings ";
        return n + unit + type;
    };
    Chart.prototype.buildData = function (json) {
        for (var _i = 0, json_1 = json; _i < json_1.length; _i++) {
            var pivot = json_1[_i];
            var data = pivot.candidates;
            var map = this.mapByFamily(data);
            var col = this.computeColoring(map);
            this.benchmarks[this.benchmarkName(pivot.number, pivot.type)] = data.map(function (d) { return new __WEBPACK_IMPORTED_MODULE_3__app_core__["a" /* Sample */](d.algorithm, d.family, d.results, col); });
        }
        this.update(this.benchmarkName("1", "in files"));
    };
    Chart.prototype.allBenchmarks = function () {
        return Object.keys(this.benchmarks);
    };
    Chart.prototype.ngOnInit = function () {
        var self = this;
        this.options = {
            chart: {
                type: 'scatterChart',
                height: self.height,
                width: self.width,
                color: function (d, i) { return d.col(i, d); },
                scatter: {
                    onlyCircles: false
                },
                showDistX: false,
                showDistY: false,
                duration: 350,
                xScale: d3.scale.log().base(2).range([0, self.width]),
                forceX: [1, 2048],
                x: function (d) { return d.pattern; },
                xAxis: {
                    axisLabel: 'Pattern Size',
                    range: [0, 2048],
                    tickFormat: function (d) { return d.toFixed(0); },
                    tickPadding: 20,
                    axisLabelDistance: 10,
                    orient: 'bottom'
                },
                yScale: d3.scale.log().base(2).range([0, self.height]),
                forceY: [1, 2048],
                y: function (d) { return d.alphabet; },
                yAxis: {
                    axisLabel: 'Alphabet Size',
                    tickFormat: function (d) { return d.toFixed(0); },
                    tickPadding: 20,
                    axisLabelDistance: -5,
                    orient: 'left'
                },
                zoom: {
                    // NOTE: All attributes below are optional
                    enabled: true,
                    scaleExtent: [1, 10],
                    useFixedDomain: false,
                    useNiceScale: false,
                    horizontalOff: false,
                    verticalOff: false,
                    unzoomEventType: 'dblclick.zoom'
                },
                pointDomain: [0, 40],
                pointSize: 20
            }
        };
    };
    return Chart;
}());
Chart = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: "chart",
        providers: [__WEBPACK_IMPORTED_MODULE_2__angular_common__["d" /* Location */], { provide: __WEBPACK_IMPORTED_MODULE_2__angular_common__["e" /* LocationStrategy */], useClass: __WEBPACK_IMPORTED_MODULE_2__angular_common__["f" /* PathLocationStrategy */] }],
        template: "<div>\n        <h1>Benchmarks ({{selected}})</h1>\n        <div class=\"form-group\">\n            <label for=\"samples\">Sample</label>\n            <select class=\"form-control\" id=\"samples\" [ngModel]=\"selected\" (ngModelChange)=\"update($event)\" >\n                <option *ngFor=\"let benchmark of allBenchmarks()\" [value]=\"benchmark\">{{benchmark}}</option>\n            </select>\n        </div>\n        <app-nvd3 [options]=\"options\" [data]=\"data\"></app-nvd3>\n    </div>"
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["b" /* Http */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_common__["d" /* Location */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_common__["d" /* Location */]) === "function" && _b || Object])
], Chart);

var _a, _b;
//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 79:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Sample; });
var Sample = (function () {
    function Sample(key, family, values, col) {
        this.key = key;
        this.family = family;
        this.values = values;
        this.col = col;
    }
    return Sample;
}());

//# sourceMappingURL=app.core.js.map

/***/ }),

/***/ 80:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(21);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(76);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_common__ = __webpack_require__(20);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_http__ = __webpack_require__(47);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_angular2_nvd3__ = __webpack_require__(84);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_angular2_nvd3___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_angular2_nvd3__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__app_component__ = __webpack_require__(78);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};







var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["NgModule"])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_6__app_component__["a" /* Chart */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_3__angular_common__["a" /* CommonModule */],
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
            __WEBPACK_IMPORTED_MODULE_4__angular_http__["a" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_5_angular2_nvd3__["NvD3Module"]
        ],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_6__app_component__["a" /* Chart */]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 81:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
// The file contents for the current environment will overwrite these during build.
var environment = {
    production: false
};
//# sourceMappingURL=environment.js.map

/***/ })

},[164]);
//# sourceMappingURL=main.bundle.js.map