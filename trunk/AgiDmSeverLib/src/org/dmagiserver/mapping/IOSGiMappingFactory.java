package org.dmagiserver.mapping;

public interface IOSGiMappingFactory {
	IOSGiMappingStrategy getMappingStrategy();
	IOSGiMappingStrategy getMappingStrategyForDomain(String domain);
}
