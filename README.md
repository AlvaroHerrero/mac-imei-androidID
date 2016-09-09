Get the Imei, Wifi MAC and AndroidID on Android Device

Installation:

   cordova plugin add https://github.com/AlvaroHerrero/mac-imei-androidID

Usage:

    window.plugins.mac_imei_androidID.getImei(function(imei){
        console.log("IMEI: " + imei);
    });

    window.plugins.mac_imei_androidID.getMac(function(mac){
        console.log("WIFI MAC: " + mac);
    });

    window.plugins.mac_imei_androidID.getAndroidID(function(androidID){
        console.log("Android_ID: " + androidID);
    });

