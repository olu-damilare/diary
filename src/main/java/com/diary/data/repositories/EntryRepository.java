package com.diary.data.repositories;

import com.diary.data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EntryRepository extends MongoRepository<Entry, String> {

    Optional<Entry> findByTitle(String title);
}
