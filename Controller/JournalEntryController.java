package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Service.JournalEntryService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryService.saveEntry(myEntry);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<JournalEntry>> getAllEntries() {
        List<JournalEntry> allEntries = journalEntryService.getAllEntries();
        return new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    // Can update the DateTime of the object
    @PutMapping("/update")
    public ResponseEntity<JournalEntry> updateEntry(@RequestBody JournalEntry newEntry, @PathVariable Long id) {
        Optional<JournalEntry> entry = journalEntryService.getEntryById(id);
        JournalEntry old = entry.get();

        if(entry.isPresent()) {
            old.setTitle(!newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
            old.setContent(!newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
        }

        return new ResponseEntity<>(old, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable Long myId) {
        if(journalEntryRepository.existsById(myId)) {
            journalEntryService.deleteEntryById(myId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{myId}")
    public ResponseEntity<JournalEntry> getAllEntriesById(@PathVariable Long myId) {
        Optional<JournalEntry> entry = journalEntryService.getEntryById(myId);

        if (entry.isPresent()) {
            JournalEntry myEntry = entry.get();
            return new ResponseEntity<>(myEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
