package com.diary.service;

import com.diary.data.models.Diary;
import com.diary.data.models.Entry;
import com.diary.data.repositories.DiaryRepository;
import com.diary.exceptions.DiaryException;
import com.diary.exceptions.EntryException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryService{

    DiaryRepository diaryRepository;
    EntryService entryService;

    @Override
    public Diary createDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Override
    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    @Override
    public Diary getDiaryById(String id) {
        return diaryRepository.findById(id)
                .orElseThrow(()-> new DiaryException("No diary with such id"));
    }

    @Override
    public void deleteDiary(String id) {
        Diary diaryToDelete = getDiaryByID(id);

        diaryRepository.delete(diaryToDelete);

    }

    @Override
    public void addEntryToADiary(String diaryId, Entry entry) {
        Diary diary = getDiaryByID(diaryId);
        Entry newEntry = entryService.createEntry(entry);
        diary.getEntries().add(newEntry);

        diaryRepository.save(diary);
    }

    @Override
    public void updateEntryInDiary(String diaryId, Entry updatedEntry) {
        Diary diary = getDiaryByID(diaryId);
        if (diary.getEntries().contains(updatedEntry)) {
            String entryId = updatedEntry.getId();
            entryService.updateEntry(entryId, updatedEntry);
        }else throw new EntryException("No such entry id in diary id " + diaryId);
    }

    @Override
    public List<Entry> getAllEntriesInDiary(String diaryId) {
        Diary diary = getDiaryByID(diaryId);
        return diary.getEntries();
    }

    @Override
    public Entry getAnEntryInDiaryByTitle(String diaryId, String entryTitle) {
        Diary diary = getDiaryByID(diaryId);

        return diary.getEntries().stream()
                .filter(entry -> entry.getTitle().equalsIgnoreCase(entryTitle)).findFirst()
                .orElseThrow(()-> new DiaryException("No entry with such title in the diary"));
    }

    private Diary getDiaryByID(String diaryId) {
        return diaryRepository.findById(diaryId)
                .orElseThrow(() -> new DiaryException("No diary with such id"));
    }

    @Override
    public void deleteEntryInDiary(String diaryId, String entryId) {
        Diary diary = getDiaryByID(diaryId);

        Entry entryToDelete = entryService.findById(entryId);
        if(diary.getEntries().contains(entryToDelete)){
            diary.getEntries().remove(entryToDelete);
            entryService.deleteEntry(entryId);
        }else throw new DiaryException("Diary does not contain entry with the provided id");

        diaryRepository.save(diary);
    }


}
