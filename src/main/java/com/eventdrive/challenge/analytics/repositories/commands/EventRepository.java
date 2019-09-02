package com.eventdrive.challenge.analytics.repositories.commands;

import com.eventdrive.challenge.analytics.domain.entities.Event;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CassandraRepository<Event, Long> {
}
