package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository     // Spring Boot on the fly can create a class that implements this interface -- pretty cool!
public interface EventRepository extends CrudRepository<Event, Integer> {
}
