package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Service.JournalEntryService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int createEntry(@RequestBody JournalEntry myEntry) {
        journalEntryService.saveEntry(myEntry);
        return Response.SC_OK;
    }

    @GetMapping("/get-all")
    public List<JournalEntry> getAllEntries() {
        return journalEntryService.getAllEntries();
    }

    // Can update the DateTime of the object
    @PutMapping("/update")
    public int updateEntry(@RequestBody JournalEntry entry) {
        return journalEntryService.updateEntry(entry);
    }

    @DeleteMapping("/delete/{myId}")
    public int deleteEntryById(@PathVariable Long myId) {
        if(journalEntryRepository.existsById(myId)) {
            journalEntryService.deleteEntryById(myId);
            return Response.SC_OK;
        }

        return Response.SC_NOT_FOUND;
    }

    @GetMapping("/get/{myId}")
    public Optional<JournalEntry> getAllEntriesById(@PathVariable Long myId) {
        return journalEntryService.getEntryById(myId);
    }

}
