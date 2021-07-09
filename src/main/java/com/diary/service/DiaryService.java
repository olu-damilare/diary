package com.diary.service;

import com.diary.data.models.Diary;
import com.diary.data.models.Entry;

import java.util.List;

public interface DiaryService {

    Diary createDiary(Diary diary);
    List<Diary> getAllDiaries();
    Diary getDiaryById(String id);
    void deleteDiary(String id);
    void addEntryToADiary(String diaryId, Entry Entry);
    void updateEntryInDiary(String diaryId, Entry updatedEntry);
    List<Entry> getAllEntriesInDiary(String diaryId);
    Entry getAnEntryInDiaryByTitle(String diaryId, String entryTitle);
    void deleteEntryInDiary(String diaryId, String entryId);
}
