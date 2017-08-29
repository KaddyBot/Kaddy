package kaddy.util;

import ch.qos.logback.classic.spi.Configurator;
import com.google.auto.service.AutoService;
import dtmlibs.logging.logback.LogbackConfigurator;

@AutoService(Configurator.class)
public class JLoggingConfigurator extends LogbackConfigurator { }
