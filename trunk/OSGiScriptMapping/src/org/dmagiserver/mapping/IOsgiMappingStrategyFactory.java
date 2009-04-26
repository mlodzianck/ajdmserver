package org.dmagiserver.mapping;

import org.asteriskjava.fastagi.MappingStrategy;

public interface IOsgiMappingStrategyFactory {
		public MappingStrategy getMappingStrategy(String serverDomain);
}
