package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {
    //reader for emulator.properties
    public static final EmulatorConfig emulatorConfig= ConfigFactory.create(EmulatorConfig.class,System.getProperties());

    //reader for test.properties
    public static final TestConfig testConfig =ConfigFactory.create(TestConfig.class, System.getProperties());
}
