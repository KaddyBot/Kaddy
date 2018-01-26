package kaddy.sample.plugin;

import kaddy.plugin.JavaPlugin;

public class SamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Hello!");
    }
}
