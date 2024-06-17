package net.engineeringdigest.journalApp.Service;
import jakarta.transaction.Transactional;
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    private Logger logger;

    @Transactional
    public int saveEntry(@org.jetbrains.annotations.NotNull JournalEntry entry) {
        entry.setDate(LocalDateTime.now());
        journalEntryRepository.save(entry);
        return Response.SC_CREATED;
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getEntryById(Long Id) {
        return journalEntryRepository.findById(Id);
    }

    public int deleteEntryById(Long Id) {
        journalEntryRepository.deleteById(Id);
        return Response.SC_ACCEPTED;
    }

    public int updateEntry(JournalEntry entry) {
        if (journalEntryRepository.existsById(entry.getId())) {
            entry.setDate(LocalDateTime.now());
            journalEntryRepository.save(entry);
            return Response.SC_CREATED;
        }
        return Response.SC_NOT_FOUND;
    }
}
