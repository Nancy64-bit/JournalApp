package net.engineeringdigest.journalApp.Service;
import jakarta.transaction.Transactional;
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
        Optional<JournalEntry> entry = journalEntryRepository.findById(Id);
        if (entry.isPresent()) return entry;
        else return null;
    }

    public ResponseEntity<?> deleteEntryById(Long Id) {
        journalEntryRepository.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED );
    }

    public ResponseEntity<?> updateEntry(JournalEntry entry) {
//        if (journalEntryRepository.existsById(entry.getId())) {
//            entry.setDate(LocalDateTime.now());
//            journalEntryRepository.save(entry);
//            return Response.SC_CREATED;
//        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
