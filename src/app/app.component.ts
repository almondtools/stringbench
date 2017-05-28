import { Input, Component, OnInit, Inject } from '@angular/core';
import { Http } from '@angular/http';
import { Location, LocationStrategy, HashLocationStrategy } from '@angular/common';
import { Sample } from './app.core'
declare let d3: any;

@Component( {
    selector: "chart",
    providers: [Location, { provide: LocationStrategy, useClass: HashLocationStrategy }],
    template: `<div>
        <h1>Benchmarks ({{selected}})</h1>
        <div class="form-group">
            <label for="samples">Sample</label>
            <select class="form-control" id="samples" [ngModel]="selected" (ngModelChange)="update($event)" >
                <option *ngFor="let benchmark of allBenchmarks()" [value]="benchmark">{{benchmark}}</option>
            </select>
        </div>
        <app-nvd3 [options]="options" [data]="data"></app-nvd3>
    </div>`
} )
export class Chart implements OnInit {
    colors = [ //
        ["#00ff00", "#00ff88", "#00aa00", "#008800", "#00aa44"],// 
        ["#0000ff", "#8800ff", "#0000aa", "#000088", "#4400aa"],//
        ["#ff0000", "#ff8800", "#aa0000", "#880000", "#aa4400"],// 
        ["#00ffff", "#0088ff", "#00aaaa", "#008888", "#0044aa"],//
        ["#ff00ff", "#ff0088", "#aa00aa", "#880088", "#aa0044"], //
        ["#ffff00", "#88ff00", "#aaaa00", "#888800", "#44aa00"] //
    ];
    margin = {
        top: 20,
        right: 20,
        bottom: 30,
        left: 40
    };
    width = 960 - this.margin.left - this.margin.right;
    height = 500 - this.margin.top - this.margin.bottom;

    options;
    data = [];
    selected;
    benchmarks = {};

    constructor( http: Http, location: Location ) {
        var fileName = location.path() ? location.path(): "latest";
        http.request( "/assets/" + fileName + ".json" )
            .subscribe( response => this.buildData( response.json() ) );
    }
    update( selected ) {
        this.selected = selected;
        this.data = this.benchmarks[selected];
    }

    mapByFamily( data ) {
        var map = {};
        for ( var j = 0; j < data.length; j++ ) {
            var entry = data[j];

            var key = entry.algorithm;
            var family = entry.family;
            var values = map[family];
            if ( !values ) {
                values = [key];
                map[family] = values;
            } else {
                values.push( key );
            }
        }
        return map;

    }
    computeColoring( map ) {
        var dom = [];
        var rng = [];

        var c = 0;
        for ( var fam in map ) {
            var algs = map[fam];
            for ( var i in algs ) {
                dom.push( algs[i] );
                rng.push( this.colors[c][i] );
            }
            c += 1;
        }
        return d3.scale.ordinal().domain( dom ).range( rng );
    }

    benchmarkName(number:string, type:string) {
        var n = Number(number);
        var unit = n <= 1 ? " string " : " strings ";
        return  n + unit + type; 
    }
    
    buildData( json: any ) {
        for ( var pivot of json ) {
            var data = pivot.candidates;

            var map = this.mapByFamily( data );
            var col = this.computeColoring( map );
            this.benchmarks[this.benchmarkName(pivot.number, pivot.type)] = data.map( d => new Sample( d.algorithm, d.family, d.results, col ) );
        }
        this.update( this.benchmarkName("1", "in files") );
    }

    allBenchmarks() {
        return Object.keys( this.benchmarks );
    }

    ngOnInit() {
        var self = this;
        this.options = {
            chart: {
                type: 'scatterChart',
                height: self.height,
                width: self.width,
                color: ( d, i ) => d.col( i, d ),
                scatter: {
                    onlyCircles: false
                },
                showDistX: false,
                showDistY: false,
                duration: 350,
                xScale: d3.scale.log().base( 2 ).range( [0, self.width] ),
                forceX: [1, 2048],
                x: d => d.pattern,
                xAxis: {
                    axisLabel: 'Pattern Size',
                    range: [0, 2048],
                    tickFormat: d => d.toFixed( 0 ),
                    tickPadding: 20,
                    axisLabelDistance: 10,
                    orient: 'bottom'
                },
                yScale: d3.scale.log().base( 2 ).range( [0, self.height] ),
                forceY: [1, 2048],
                y: d => d.alphabet,
                yAxis: {
                    axisLabel: 'Alphabet Size',
                    tickFormat: d => d.toFixed( 0 ),
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
        }

    }

}