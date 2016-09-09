function mac_imei_androidID() {
	this.getImei = function(returnCallback) {
		cordova.exec(returnCallback, function(){}, "mac_imei_androidID", "getImei", []);
	}
	this.getMac = function(returnCallback) {
		cordova.exec(returnCallback, function(){}, "mac_imei_androidID", "getMac", []);
	}
	this.getAndroidID = function(returnCallback) {
		cordova.exec(returnCallback, function(){}, "mac_imei_androidID", "getAndroidID", []);
	}
}

window.plugins = window.plugins || {};
window.plugins.mac_imei_androidID = new mac_imei_androidID();