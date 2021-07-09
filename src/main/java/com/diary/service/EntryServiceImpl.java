package com.diary.service;

import com.diary.data.models.Entry;
import com.diary.data.repositories.EntryRepository;
import com.diary.exceptions.EntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService{


    @Autowired
    EntryRepository entryRepository;

    @Override
    public Entry createEntry(Entry entry) {
       return entryRepository.save(entry);
    }

    @Override
    public void deleteEntry(String id) {
        Optional<Entry> entryOptional = entryRepository.findById(id);
        if(entryOptional.isPresent()){
            entryRepository.delete(entryOptional.get());
        }else{
            throw new EntryException("Invalid entry id");
        }
    }

    @Override
    public Entry updateEntry(String id, Entry newEntry) {
        Entry entry = entryRepository.findById(id).orElseThrow(
                () -> new EntryException("Invalid ID"));
        if(!newEntry.getTitle().equals(entry.getTitle())){
            entry.setTitle(newEntry.getTitle());
        }
        if(!newEntry.getBody().equals(entry.getBody())){
            entry.setBody(newEntry.getBody());
        }
        return entryRepository.save(entry);

    }

    @Override
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    @Override
    public Entry findById(String id) {
        return entryRepository.findById(id).orElseThrow(
                () -> new EntryException("Invalid ID"));
    }
}
