package config;

import org.aeonbits.owner.Config;

//Reading keys from emulator.properties
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties", //reading env
        "file:src/test/resources/configs/emulator.properties", //reading from file  er

})
public interface EmulatorConfig extends Config {

    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("app")
    String app();

    @Key("remoteURL")
    String remoteURL();
}
