package kaddy.util;

import ch.qos.logback.classic.spi.Configurator;
import com.google.auto.service.AutoService;

@AutoService(Configurator.class)
public class JLoggingConfigurator extends LoggingConfigurator { }
