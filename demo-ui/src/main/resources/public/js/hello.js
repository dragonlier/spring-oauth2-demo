angular.module('hello', [ 'ngRoute' ])
	.config(function($routeProvider, $httpProvider) {
		$routeProvider.when('/', {
			templateUrl : 'home.html',
			controller : 'home',
			controllerAs : 'controller'
		})
		/*
		.when('/login', {
			templateUrl : 'login.html',
			controller : 'navigation',
			controllerAs : 'controller'
		})
		*/
		.otherwise('/');

		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		//$httpProvider.defaults.headers.common["Access-Control-Allow-Origin"] = '*';

	})
	.controller('home', function($scope, $http) {
		var self = this;
		/*
		$scope.greeting = {
			id : 'xxx',
			content : 'Hello World!'
		};
		self.greeting = {
			id : 'lhj',
			content : 'This is self Hello World !!!'
		};
		*/
		// '/resource' 和 '/resource/' 的返回结果还不一样
		$http.get('/resource').then(function(response) {
			self.greeting = response.data;
		})
		/*
		$http.get('user').then(function(response) {
					if (response.data.details) {
						self.details = response.data.details;
						$http.get({
							method: 'GET', 
							url: 'http://localhost:8082/resource',
					        headers : {'Authorization' : self.details.tokenType + ' ' + self.details.tokenValue,
					        	'X-Requested-With' : 'XMLHttpRequest'
					        }
					    }).then(function(response) {
					    	self.greeting = response.data;
					    });
					}
				});
		*/
	})
	.controller('navigation', function($rootScope, $http, $location, $route) {
			var self = this
			/*
			var authenticate = function(credentials, callback) {

				var headers = credentials ? {
					authorization : "Basic "
							+ btoa(credentials.username + ":"
									+ credentials.password)
				} : {};
				
				$http.get('user', {
					headers : headers
				}).then(function(response) {
					if (response.data.name) {
						$rootScope.authenticated = true;
					} else {
						$rootScope.authenticated = false;
					}
					callback && callback();
				}, function() {
					$rootScope.authenticated = false;
					callback && callback();
				});

			}

			authenticate();
			*/
			$http.get('check').then(function(response) {
					if (response.data) {
						$rootScope.authenticated = true;
					} else {
						$rootScope.authenticated = false;
					}
				}, function() {
					$rootScope.authenticated = false;
				});
			self.credentials = {};
			self.login = function() {
				authenticate(self.credentials, function() {
					if ($rootScope.authenticated) {
						$location.path("/");
						self.error = false;
					} else {
						$location.path("/login");
						self.error = true;
					}
				});
			};
			self.logout = function() {
				$http.post('logout', {}).finally(function() {
					$rootScope.authenticated = false;
					$location.path("/");
				});
			};
		});