angular.module("bestAlgorithms", [ 'nvd3', 'ngResource' ]) //
.controller('DataController', function($scope, $resource) {

	var colors = [ //
	[ "#00ff00", "#00ff88", "#00aa00", "#008800", "#00aa44" ],// 
	[ "#0000ff", "#8800ff", "#0000aa", "#000088", "#4400aa" ],//
	[ "#ff0000", "#ff8800", "#aa0000", "#880000", "#aa4400" ],// 
	[ "#00ffff", "#0088ff", "#00aaaa", "#008888", "#0044aa" ],//
	[ "#ff00ff", "#ff0088", "#aa00aa", "#880088", "#aa0044" ], //
	[ "#ffff00", "#88ff00", "#aaaa00", "#888800", "#44aa00" ] //
	];

	var margin = {
		top : 20,
		right : 20,
		bottom : 30,
		left : 40
	};
	var width = 960 - margin.left - margin.right;
	var height = 500 - margin.top - margin.bottom;

	$scope.selectedData = [];
	$scope.options = {
		chart : {
			type : 'scatterChart',
			height : height,
			width : width,
			color : function(d, i) {
				var color = $scope.scale(d.key);
				return color;
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

	var Data = $resource('./:data', {
		data : '@data'
	});
	Data.query({
		data : 'best.json'
	}, function(data) {
		$scope.data = {};
		$scope.collections = [];
		var dom = [];
		var rng = [];

		var map = {};
		for (var i = 0; i < data.length; i++) {
			var element = data[i];
			var collection = element.number + " patterns " + element.type;
			var value = element.candidates;

			$scope.data[collection] = [];

			for (var j = 0; j < value.length; j++) {
				var entry = value[j];

				if ($scope.collections.indexOf(collection) < 0) {
					$scope.collections.push(collection);
				}
				$scope.data[collection].push({
					key : entry.algorithm,
					family : entry.family,
					values : entry.results
				})
			}
		}
		$scope.collections.sort(function(a,b) {
			var aparts = a.split(" patterns ");
			var bparts = b.split(" patterns ");
			if (parseInt(aparts[0],10) < parseInt(bparts[0],10)) {
				return -1;
			} else if (parseInt(aparts[0],10) > parseInt(bparts[0],10)) {
				return 1;
			} else {
				return aparts[1].localeCompare(bparts[1]);
			}
		});
		
		$scope.selectedKey = $scope.collections[0];
		$scope.select();
	});

	$scope.select = function() {
		var key = $scope.selectedKey;
		var data = $scope.data[key] || [];
		var dom = [];
		var rng = [];

		var map = {};
		for (var j = 0; j < data.length; j++) {
			var entry = data[j];

			var key = entry.key;
			var family = entry.family;
			var values = map[family];
			if (!values) {
				values = [ key ];
				map[family] = values;
			} else {
				values.push(key);
			}
		}

		var c = 0;
		for ( var fam in map) {
			var algs = map[fam];
			for ( var i in algs) {
				dom.push(algs[i]);
				rng.push(colors[c][i]);
			}
			c += 1;
		}

		$scope.scale = d3.scale.ordinal().domain(dom).range(rng);
		$scope.selectedData = data;
	}
	
});
