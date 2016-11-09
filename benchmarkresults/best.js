angular.module("bestAlgorithms", [ 'nvd3', 'ngResource' ]) //
.controller('DataController', function($scope, $resource) {

	var margin = {
		top : 20,
		right : 20,
		bottom : 30,
		left : 40
	};
	var width = 960 - margin.left - margin.right;
	var height = 500 - margin.top - margin.bottom;

	$scope.options = {
		chart : {
			type : 'scatterChart',
			height : height,
			width : width,
			color : function(d, i) {
			  	var scale = d3.scale.ordinal()
		  		.domain($scope.dom)
		  		.range($scope.rng);
			  	return scale(d.key);
			},
			scatter : {
				onlyCircles : false
			},
			showDistX : false,
			showDistY : false,
			duration : 350,
			xScale : d3.scale.log().base(2).range([ 0, width ]),
			forceX : [ 1, 2048 ],
			x : function(d) {
				return d.pattern;
			},
			xAxis : {
				axisLabel : 'Pattern Size',
				range : [ 0, 2048 ],
				tickFormat : function(d) {
					return d.toFixed(0);
				},
				tickPadding : 20,
				axisLabelDistance : 10,
				orient : 'bottom'
			},
			yScale : d3.scale.log().base(2).range([ 0, height ]),
			forceY : [ 1, 2048 ],
			y : function(d) {
				return d.alphabet;
			},
			yAxis : {
				axisLabel : 'Alphabet Size',
				tickFormat : function(d) {
					return d.toFixed(0);
				},
				tickPadding : 20,
				axisLabelDistance : -5,
				orient : 'left'
			},
			zoom : {
				// NOTE: All attributes below are optional
				enabled : true,
				scaleExtent : [ 1, 10 ],
				useFixedDomain : false,
				useNiceScale : false,
				horizontalOff : false,
				verticalOff : false,
				unzoomEventType : 'dblclick.zoom'
			},
			pointDomain : [ 0, 40 ],
			pointSize : 20
		}
	};
	
	var Data = $resource('./:data', {data:'@data'});
	$scope.data = Data.query({data:'best.json'}, function(data) {
	  	var colors = [
		  	 ["#00ff00", "#00ff88", "#00aa00", "#008800", "#00aa44"],
		  	 ["#0000ff", "#8800ff", "#0000aa", "#000088", "#4400aa"],
		  	 ["#ff0000", "#ff8800", "#aa0000", "#880000", "#aa4400"],
		  	 ["#00ffff", "#0088ff", "#00aaaa", "#008888", "#0044aa"],
		  	 ["#ff00ff", "#ff0088", "#aa00aa", "#880088", "#aa0044"],
		  	 ["#ffff00", "#88ff00", "#aaaa00", "#888800", "#44aa00"]
		  	];
		  	$scope.dom = [];
		  	$scope.rng = [];
		  	var map = {};
		  	for (var i = 0; i < $scope.data.length; i++) {
		  		var entry = $scope.data[i];
		  		var key = entry.key;
		  		var family = entry.family;
		  		var values = map[family];
		  		if (!values) {
		  			values = [key];
		  			map[family] = values;
		  		} else {
		  			values.push(key);
		  		}
		  	}
		  	
		  	var c = 0;
		  	for (var fam in map) {
		    	var algs = map[fam];
		  		for (var i in algs) {
		  			$scope.dom.push(algs[i]);
		  			$scope.rng.push(colors[c][i]);
		  		}
		  		c+=1;
		  	}
console.log($scope.dom);
console.log($scope.rng);
	});
});
