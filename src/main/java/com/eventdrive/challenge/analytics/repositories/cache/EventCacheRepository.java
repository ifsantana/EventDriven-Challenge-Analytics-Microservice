package com.eventdrive.challenge.analytics.repositories.cache;

import com.eventdrive.challenge.analytics.domain.entities.Event;
import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.apache.ignite.springdata20.repository.config.RepositoryConfig;

@RepositoryConfig(cacheName = "EventCache")
public interface EventCacheRepository extends IgniteRepository<Event, Long> {
}
