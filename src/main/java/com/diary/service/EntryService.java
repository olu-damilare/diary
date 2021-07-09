package com.diary.service;

import com.diary.data.models.Entry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EntryService {

    Entry createEntry(Entry entry);
    void deleteEntry(String id);
    Entry updateEntry(String id, Entry entry);
    List<Entry> getAllEntries();
    Entry findById(String id);
}
