package com.eventdrive.challenge.analytics.services;

import com.eventdrive.challenge.analytics.domain.entities.Event;
import com.eventdrive.challenge.analytics.repositories.commands.EventRepository;
import com.eventdrive.challenge.analytics.repositories.queries.EventCacheRepository;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.ContinuousQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class EventService {
    private static final String TOPIC = "sync_event_occurrence_topic";
    private EventRepository repository;
    private EventCacheRepository cacheRepository;
    private KafkaTemplate<String, Object> kafkaTemplate;
    private Ignite ignite;

    @Autowired
    public EventService(EventRepository repository, EventCacheRepository cacheRepository, KafkaTemplate<String, Object> kafkaTemplate, Ignite ignite) {
        this.repository = repository;
        this.cacheRepository = cacheRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.ignite = ignite;
    }

    @PostConstruct
    public void registerCreateEventContinuousQuery() {
        ContinuousQuery<Long, Event> query = new ContinuousQuery<>();


        query.setLocalListener((events) -> {
            events.forEach((cacheEntryEvent -> {
                kafkaTemplate.send(TOPIC, cacheEntryEvent.toString());
            }));
        });

        IgniteCache<Long, Event> igniteCache = ignite.getOrCreateCache("EventCache");
        igniteCache.query(query);
    }
}
